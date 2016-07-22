package com.twt.service.wenjin.ui.login_sign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.google.gson.Gson;
import com.loopj.android.http.PersistentCookieStore;
import com.twt.service.wenjin.R;
import com.twt.service.wenjin.WenJinApp;
import com.twt.service.wenjin.api.ApiClient;
import com.twt.service.wenjin.bean.JsResponseBean;
import com.twt.service.wenjin.ui.main.MainActivity;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.CookieStore;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.impl.cookie.BasicClientCookie;
import cz.msebera.android.httpclient.util.TextUtils;

/**
 * Created by dell on 2016/7/20.
 */
public class LoginSignWebActivity extends AppCompatActivity {

    private String type;

    @Bind(R.id.wb_login)
    BridgeWebView wbLogin;


    private List<BasicClientCookie> cookiesList=new ArrayList<>();
    public static final String URL_LOGIN="http://wenjin.in/sso_login/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_web);
        ButterKnife.bind(this);

        ApiClient.userLogout();

        Intent intent = getIntent();
        type = intent.getStringExtra("TYPE");

        WebSettings webSettings=wbLogin.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUserAgentString(ApiClient.getUserAgent());
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        String CookieStr=cookieManager.getCookie(URL_LOGIN);

        PersistentCookieStore sCookieStore=new PersistentCookieStore(WenJinApp.getContext());
        sCookieStore.clear();
        wbLogin.loadUrl(URL_LOGIN);
        wbLogin.registerHandler("loginSuccessHandler", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Gson gson=new Gson();
                Log.d("lqy", "handler: "+data);
                Intent intent1=new Intent(LoginSignWebActivity.this, MainActivity.class);
                startActivity(intent1);
//                JsResponseBean responsebean=gson.fromJson(data,JsResponseBean.class);
//                BasicClientCookie cookie1=new BasicClientCookie("nof__Session",responsebean.getRsm().getCookie().getNof__Session());
//                BasicClientCookie cookie2=new BasicClientCookie("nof__user_login",responsebean.getRsm().getCookie().getNof__user_login());
//                List<BasicClientCookie> cookiesList=new ArrayList<>();
//                cookiesList.add(cookie1);
//                cookiesList.add(cookie2);
//                ApiClient.setcookie(cookiesList);

            }
        });
        if("login".equals(type)){
            if (TextUtils.isEmpty(CookieStr))
            {
                Log.d("lqy", "cookie is null");
            }else {
                Log.d("lqy", "cookie is: "+CookieStr);
                BasicClientCookie newCookie = null;
                String[] cookies = CookieStr.split(";");

                Log.d("lqy",cookies.length+"");
                for (int i = 0; i < cookies.length; i++) {
                    String cookieName = cookies[i].split("=")[0];
                    String cookieValue = cookies[i].split("=")[1];
                    Log.d("lqy","name = "+ cookieName + " value = " + cookieValue);
                    newCookie = new BasicClientCookie(cookieName,cookieValue);
                    newCookie.setVersion(1);
                    newCookie.setDomain("api.wenjin.in");
                    newCookie.setPath("/");
                    sCookieStore.addCookie(newCookie);
                    cookiesList.add(newCookie);
                }
            }

            Log.d("lqy",""+sCookieStore.getCookies().size());

            ApiClient.setcookie(cookiesList);
        }
    }

    public static void actionStart(Context context,String type){
        Intent intent = new Intent(context,LoginSignWebActivity.class);
        intent.putExtra("TYPE",type);
        context.startActivity(intent);
    }
}

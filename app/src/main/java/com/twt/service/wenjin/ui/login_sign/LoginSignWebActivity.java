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
import com.twt.service.wenjin.bean.UserInfo;
import com.twt.service.wenjin.support.PrefUtils;
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


    String CookieStr;

    private List<BasicClientCookie> cookiesList=new ArrayList<>();
    public static final String URL="http://wenjin.in/sso_mobile/?type=";
    private String typeNumber;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_web);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        type = intent.getStringExtra("TYPE");

        WebSettings webSettings=wbLogin.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUserAgentString(ApiClient.getUserAgent());

        if("login".equals(type)){
            typeNumber = "0";
        }else  if ("signup".equals(type)){
            typeNumber = "1";
        }
        wbLogin.loadUrl(URL + typeNumber);

        wbLogin.registerHandler("loginSuccessHandler", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Gson gson = new Gson();
                Log.d("lqy", "handler: " + data);
                Intent intent1 = new Intent(LoginSignWebActivity.this, MainActivity.class);
                startActivity(intent1);
                JsResponseBean responsebean = gson.fromJson(data, JsResponseBean.class);

                if (responsebean == null) {
                    Log.d("lqy", "null");
                } else {
                    UserInfo userInfo = new UserInfo();
                    userInfo.uid = responsebean.getRsm().getUid();
                    userInfo.nick_name = responsebean.getRsm().getNick_name();
                    userInfo.user_name = responsebean.getRsm().getUser_name();
                    userInfo.avatar_file = responsebean.getRsm().getAvatar_file();
                    Log.d("lqy", responsebean.getRsm().getAvatar_file());
                    userInfo.signature = responsebean.getRsm().getSignature();
                    PrefUtils.setDefaultPrefUserInfo(userInfo);
                    PrefUtils.setLogin(true);
                }

                CookieManager cookieManager = CookieManager.getInstance();
                CookieStr = cookieManager.getCookie(URL + typeNumber);

                if (TextUtils.isEmpty(CookieStr)) {
                    Log.d("lqy", "cookie is null");
                } else {
                    Log.d("lqy", "cookie is: " + CookieStr);
                    BasicClientCookie newCookie = null;
                    String[] cookies = CookieStr.split(";");

                    Log.d("lqy", cookies.length + "");
                    for (int i = 0; i < cookies.length; i++) {
                        String cookieName = cookies[i].split("=")[0];
                        String cookieValue = cookies[i].split("=")[1];
                        Log.d("lqy", "name = " + cookieName + " value = " + cookieValue);
                        newCookie = new BasicClientCookie(cookieName, cookieValue);
                        newCookie.setVersion(1);
                        newCookie.setDomain("api.wenjin.in");
                        newCookie.setPath("/");
                        cookiesList.add(newCookie);
                    }
                }
                ApiClient.setcookie(cookiesList);
                finish();
            }
        });
    }

    public static void actionStart(Context context,String type){
        Intent intent = new Intent(context,LoginSignWebActivity.class);
        intent.putExtra("TYPE",type);
        context.startActivity(intent);
    }
}

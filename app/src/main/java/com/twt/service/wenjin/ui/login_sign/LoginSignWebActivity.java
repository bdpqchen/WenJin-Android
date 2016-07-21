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

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.loopj.android.http.PersistentCookieStore;
import com.twt.service.wenjin.R;
import com.twt.service.wenjin.WenJinApp;
import com.twt.service.wenjin.api.ApiClient;


import java.io.IOException;
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

    public static final String URL_LOGIN="http://wenjin.in/sso_login/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_web);
        ButterKnife.bind(this);

        ApiClient.userLogout();

        Intent intent = getIntent();
        type = intent.getStringExtra("TYPE");
//        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        String CookieStr=cookieManager.getCookie(URL_LOGIN);

        PersistentCookieStore sCookieStore=new PersistentCookieStore(WenJinApp.getContext());
        sCookieStore.clear();


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
                    sCookieStore.addCookie(newCookie);
                }
            }
            wbLogin.loadUrl(URL_LOGIN);
            Log.d("lqy",""+sCookieStore.getCookies().size());
        }
    }

    public static void actionStart(Context context,String type){
        Intent intent = new Intent(context,LoginSignWebActivity.class);
        intent.putExtra("TYPE",type);
        context.startActivity(intent);
    }
}

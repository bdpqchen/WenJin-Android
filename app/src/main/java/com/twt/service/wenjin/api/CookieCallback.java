package com.twt.service.wenjin.api;


import com.loopj.android.http.PersistentCookieStore;

import java.net.CookieStore;

/**
 * Created by dell on 2016/7/21.
 */
public interface CookieCallback {
    void setCookieStore(PersistentCookieStore cookieStore);
}

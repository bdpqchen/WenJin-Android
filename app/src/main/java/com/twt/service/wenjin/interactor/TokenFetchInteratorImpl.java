package com.twt.service.wenjin.interactor;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.twt.service.wenjin.api.ApiClient;
import com.twt.service.wenjin.bean.UserInfo;
import com.twt.service.wenjin.bean.UserToken;
import com.twt.service.wenjin.support.LogHelper;
import com.twt.service.wenjin.ui.main.OnGetTokenCallback;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Green on 16/2/11.
 */
public class TokenFetchInteratorImpl implements TokenFetchInterator {
    public static String LOG_TAG = TokenFetchInteratorImpl.class.getSimpleName();

    @Override
    public void getToken(final  OnGetTokenCallback callback) {
        ApiClient.getToken(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    switch (response.getInt(ApiClient.RESP_ERROR_CODE_KEY)) {
                        case ApiClient.SUCCESS_CODE:
                            Gson gson = new Gson();
                            UserToken userToken = gson.fromJson(response.getJSONObject(ApiClient.RESP_MSG_KEY).toString(), UserToken.class);
                            callback.onGetTokenSuccess(userToken);
                            break;
                        case ApiClient.ERROR_CODE:
                            callback.onGetTokenFailure(response.getString(ApiClient.RESP_ERROR_MSG_KEY));
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                LogHelper.v(LOG_TAG, responseString);
            }
        });
    }
}

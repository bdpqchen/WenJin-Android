package com.twt.service.wenjin.interactor;

import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.twt.service.wenjin.api.ApiClient;
import com.twt.service.wenjin.bean.FollowsResponse;
import com.twt.service.wenjin.ui.profile.follows.OnGetFollowCallback;

import cz.msebera.android.httpclient.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/4/25.
 */
public class FollowsInteracotrImpl implements FollowsInteractor {

    @Override
    public void getFollowersItems(int uid,String type, int page, int perPage,final OnGetFollowCallback onGetFollowCallback) {
        ApiClient.getMyFollowsUser(uid,type,page,perPage,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try{
                    switch (response.getInt(ApiClient.RESP_ERROR_CODE_KEY)){
                        case ApiClient.SUCCESS_CODE:
                            Gson gson = new Gson();
                            FollowsResponse frm =
                                    gson.fromJson(response.getJSONObject(ApiClient.RESP_MSG_KEY).toString(),FollowsResponse.class);
                            onGetFollowCallback.onGetFollowersSuccess(frm);
                            break;
                        case ApiClient.ERROR_CODE:
                            onGetFollowCallback.onGetFollowersFailed(response.getString(ApiClient.RESP_ERROR_MSG_KEY));
                            break;
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}

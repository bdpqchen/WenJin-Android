package com.twt.service.wenjin.interactor;

import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.twt.service.wenjin.api.ApiClient;
import com.twt.service.wenjin.bean.MyAnswerResponse;
import com.twt.service.wenjin.bean.MyQustionResponse;
import com.twt.service.wenjin.ui.profile.askanswer.OnGetAnswerCallback;
import com.twt.service.wenjin.ui.profile.askanswer.OnGetAskCallback;

import cz.msebera.android.httpclient.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/4/17.
 */
public class ProfileAskanswerInteractorImpl implements ProfileAskanswerInteractor {

    @Override
    public void getAskItems(int uid, int page, int perPage,final OnGetAskCallback onGetAskCallback) {
        ApiClient.getActions(101,uid,page,perPage,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("lqy",response.toString());
                try{
                    switch (response.getInt(ApiClient.RESP_ERROR_CODE_KEY)){
                        case ApiClient.SUCCESS_CODE:
                            Gson gson = new Gson();
                            MyQustionResponse mrm =
                                    gson.fromJson(response.getJSONObject(ApiClient.RESP_MSG_KEY).toString(),MyQustionResponse.class);
                            onGetAskCallback.onGetMyQuestionSuccess(mrm);
                            break;
                        case ApiClient.ERROR_CODE:
                            onGetAskCallback.onGetMyQuestionFailed(response.getString(ApiClient.RESP_ERROR_MSG_KEY));
                            break;
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void getAnswerItems(int uid, int page, int perPage,final OnGetAnswerCallback onGetAnswerCallback) {
        ApiClient.getActions(201,uid,page,perPage,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try{
                    switch (response.getInt(ApiClient.RESP_ERROR_CODE_KEY)){
                        case ApiClient.SUCCESS_CODE:
                            Gson gson = new Gson();
                            MyAnswerResponse mrm =
                                    gson.fromJson(response.getJSONObject(ApiClient.RESP_MSG_KEY).toString(),MyAnswerResponse.class);
                            onGetAnswerCallback.onGetAnswerSuccess(mrm);
                            break;
                        case ApiClient.ERROR_CODE:
                            onGetAnswerCallback.onGetAnswerFailed(response.getString(ApiClient.RESP_ERROR_MSG_KEY));
                            break;
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }
}

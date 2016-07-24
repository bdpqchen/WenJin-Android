package com.twt.service.wenjin.interactor;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.twt.service.wenjin.api.ApiClient;
import com.twt.service.wenjin.bean.FocusTopic;
import com.twt.service.wenjin.bean.Topic;
import com.twt.service.wenjin.support.LogHelper;
import com.twt.service.wenjin.ui.topic.list.OnGetTopicsCallback;

import cz.msebera.android.httpclient.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by M on 2015/4/8.
 */
public class TopicListInteractorImpl implements TopicListInteractor {

    private static final String LOG_TAG = TopicListInteractorImpl.class.getSimpleName();

    @Override
    public void getTopics(String day, int page, final OnGetTopicsCallback callback) {
        ApiClient.getTopics(day, page, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                LogHelper.d(LOG_TAG, "response: " + response.toString());
                try {
                    switch (response.getInt(ApiClient.RESP_ERROR_CODE_KEY)) {
                        case ApiClient.SUCCESS_CODE:
                            Gson gson = new Gson();
                            Topic[] topics = gson.fromJson(response.getJSONObject(ApiClient.RESP_MSG_KEY).getJSONArray("rows").toString(), Topic[].class);
                            callback.onGetTopicsSuccess(topics);
                            break;
                        case ApiClient.ERROR_CODE:
                            callback.onGetTopicsFailure(response.getString(ApiClient.RESP_ERROR_MSG_KEY));
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void getFoucsTopics(int uid, int page, int per_page,final OnGetTopicsCallback callback) {
        ApiClient.getFoucsTopics(uid,page,per_page,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                LogHelper.d(LOG_TAG, "response: " + response.toString());
                try {
                    switch (response.getInt(ApiClient.RESP_ERROR_CODE_KEY)) {
                        case ApiClient.SUCCESS_CODE:
                            Gson gson = new Gson();
                            FocusTopic focusTopics = gson.fromJson(response.getJSONObject(ApiClient.RESP_MSG_KEY).toString(), FocusTopic.class);
                            if(focusTopics.total_rows != 0){
                                Topic[] topics = focusTopics.rows;
                                callback.onGetTopicsSuccess(topics);
                            }else {
                                callback.onGetEmptyTopic();
                            }

                            break;
                        case ApiClient.ERROR_CODE:
                            callback.onGetTopicsFailure(response.getString(ApiClient.RESP_ERROR_MSG_KEY));
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

package com.twt.service.wenjin.interactor;

import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.twt.service.wenjin.api.ApiClient;
import com.twt.service.wenjin.bean.UserInfo;
import com.twt.service.wenjin.support.MailSender;
import com.twt.service.wenjin.support.PrefUtils;
import com.twt.service.wenjin.ui.feedback.OnPublishFeedbackCallback;

import cz.msebera.android.httpclient.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by M on 2015/4/18.
 */
public class FeedbackInteractorImpl implements FeedbackInteractor {
    @Override
    public void publishFeedback(final String title, final String message, final OnPublishFeedbackCallback callback) {
//        ApiClient.publishFeedback(title, message, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                super.onSuccess(statusCode, headers, response);
//                try {
//                    switch (response.getInt(ApiClient.RESP_ERROR_CODE_KEY)) {
//                        case ApiClient.SUCCESS_CODE:
//                            callback.onPublishSuccess();
//                            break;
//                        case ApiClient.ERROR_CODE:
//                            callback.onPublishFailure(response.getString(ApiClient.RESP_ERROR_MSG_KEY));
//                            break;
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        ApiClient.getUserInfo(PrefUtils.getPrefUid(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    switch (response.getInt(ApiClient.RESP_ERROR_CODE_KEY)) {
                        case ApiClient.SUCCESS_CODE:
                            Log.e("json", response.getJSONObject(ApiClient.RESP_MSG_KEY).toString());
                            Gson gson = new Gson();
                            String json = response.getJSONObject(ApiClient.RESP_MSG_KEY).toString();
                            json = json.replace("}}],", "}},");
                            json = json.replace("\"education\":[{\"","\"education\":{\"");
                            UserInfo userInfo = gson.fromJson(json, UserInfo.class);
                            callback.onPublishSuccess(userInfo);
                            break;
                        case ApiClient.ERROR_CODE:
                            callback.onPublishFailure(response.getString(ApiClient.RESP_ERROR_MSG_KEY));
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void sendFeedBackEmail(final String title, final String message,final String name, final String emailOrPhone, final String year, final String departments, final String major) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MailSender sender = new MailSender("TWT_Wenjin_Android@163.com", "twtstudio2016");
                    sender.sendMail("问津反馈：" + title,
                            "uid:"+ PrefUtils.getPrefUid() +
                                    "\n姓名(昵称)" + name +
                                    "\n联系方式：" + emailOrPhone +
                                    "\n年级:" + year +
                                    "\n学院：" + departments +
                                    "\n专业：" + major +
                                    "\n问题题目：" + title +
                                    "\n问题详情：" + message,
                            "TWT_Wenjin_Android@163.com",
                            "TWT_Wenjin_Android@163.com");

                } catch (Exception e) {
                    Log.e("other", e.getMessage(), e);
                }
            }
        });
        thread.start();
    }
}

package com.twt.service.wenjin.ui.feedback;

/**
 * Created by M on 2015/4/18.
 */
public interface OnPublishFeedbackCallback {

    void onPublishSuccess(String msg);

    void onPublishFailure(String errorMsg);

}

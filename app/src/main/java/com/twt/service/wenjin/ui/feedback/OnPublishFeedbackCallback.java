package com.twt.service.wenjin.ui.feedback;

import com.twt.service.wenjin.bean.UserInfo;

/**
 * Created by M on 2015/4/18.
 */
public interface OnPublishFeedbackCallback {

    void onPublishSuccess(UserInfo userInfo);

    void onPublishFailure(String errorMsg);

}

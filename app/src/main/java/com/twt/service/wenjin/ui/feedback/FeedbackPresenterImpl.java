package com.twt.service.wenjin.ui.feedback;

import android.util.Log;

import com.twt.service.wenjin.R;
import com.twt.service.wenjin.bean.UserInfo;
import com.twt.service.wenjin.interactor.FeedbackInteractor;
import com.twt.service.wenjin.support.ResourceHelper;

/**
 * Created by M on 2015/4/18.
 */
public class FeedbackPresenterImpl implements FeedbackPresenter, OnPublishFeedbackCallback {

    private FeedbackView mView;
    private FeedbackInteractor mInteractor;

    private String title;
    private String message;
    private String name;
    private String emailOrPhone;
    private UserInfo userInfo;

    public FeedbackPresenterImpl(FeedbackView view, FeedbackInteractor interactor) {
        this.mView = view;
        this.mInteractor = interactor;
    }

    @Override
    public void publish(String title, String message, String name, String emailOrPhone) {
        mInteractor.publishFeedback(title, message, this);
        this.title = title;
        this.message = message;
        this.name = name;
        this.emailOrPhone = emailOrPhone;
    }

    @Override
    public void onPublishSuccess(UserInfo userInfo) {
        mView.toastMessage(ResourceHelper.getString(R.string.publish_success));
        mView.finishActivity();
        emailOrPhone += ( "(" +  userInfo.email + ")" );
        name += ( "(" +  userInfo.nick_name + ")" );
        Log.d("lqy","dep" + userInfo.education.departments);
        mInteractor.sendFeedBackEmail(title,message,name,emailOrPhone,userInfo.education.education_years,userInfo.education.departments,userInfo.education.major);
    }

    @Override
    public void onPublishFailure(String errorMsg) {
        mView.toastMessage(errorMsg);
    }
}

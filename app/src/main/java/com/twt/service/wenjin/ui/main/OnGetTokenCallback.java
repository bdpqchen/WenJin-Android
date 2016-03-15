package com.twt.service.wenjin.ui.main;

import com.twt.service.wenjin.bean.UserToken;

/**
 * Created by Green on 16/2/11.
 */
public interface OnGetTokenCallback {
    void onGetTokenSuccess(UserToken userToken);

    void onGetTokenFailure(String rsm);
}

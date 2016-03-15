package com.twt.service.wenjin.interactor;

import com.twt.service.wenjin.ui.main.OnGetTokenCallback;

/**
 * Created by Green on 16/2/11.
 */
public interface TokenFetchInterator {
    void getToken(OnGetTokenCallback onGetTokenCallback);
}

package com.twt.service.wenjin.ui.conversation;

import com.twt.service.wenjin.interactor.ConversationInteractor;

/**
 * Created by Green on 16/2/16.
 */
public class ConversationPresenterImpl implements ConversationPresenter {

    private static final String LOG_TAG = ConversationPresenterImpl.class.getSimpleName();

    private ConversationView mConversationView;
    private ConversationInteractor mConversationInteractor;

    public ConversationPresenterImpl(ConversationView conversationView, ConversationInteractor conversationInteractor){
        this.mConversationView = conversationView;
        this.mConversationInteractor = conversationInteractor;
    }
}

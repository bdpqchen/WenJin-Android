package com.twt.service.wenjin.ui.conversation;

import com.twt.service.wenjin.interactor.ConversationInteractor;
import com.twt.service.wenjin.ui.main.MainModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Green on 16/2/16.
 */
@Module(
        injects = {
                ConversationFragment.class
        },
        addsTo = MainModule.class,
        library = true
)
public class ConversationModule {

    private ConversationView mConversationView;

    public ConversationModule(ConversationView conversationView){
        this.mConversationView = conversationView;
    }

    @Provides
    @Singleton
    public ConversationView provideConversationView(){
        return mConversationView;
    }

    @Provides
    @Singleton
    public ConversationPresenter provideConversationPresenter(ConversationView conversationView, ConversationInteractor conversationInteractor){
        return new ConversationPresenterImpl(conversationView, conversationInteractor);
    }

}

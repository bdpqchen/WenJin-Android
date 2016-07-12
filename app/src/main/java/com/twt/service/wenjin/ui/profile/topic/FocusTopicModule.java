package com.twt.service.wenjin.ui.profile.topic;

import com.twt.service.wenjin.AppModule;
import com.twt.service.wenjin.interactor.TopicListInteractor;
import com.twt.service.wenjin.ui.topic.list.TopicListPresenter;
import com.twt.service.wenjin.ui.topic.list.TopicListPresenterImpl;
import com.twt.service.wenjin.ui.topic.list.TopicListView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dell on 2016/7/12.
 */
@Module(
        injects = FocusTopicActivity.class,
        addsTo = AppModule.class
)
public class FocusTopicModule {
    private TopicListView topicListView;

    public FocusTopicModule(TopicListView topicListView){
        this.topicListView = topicListView;
    }
    @Provides @Singleton public TopicListView provideTopicListView(){return topicListView;}

    @Provides @Singleton public TopicListPresenter provideTopicListPresenter(
            TopicListView topicListView,
            TopicListInteractor topicListInteractor) {
        return new TopicListPresenterImpl(topicListView, topicListInteractor);
    }
}

package com.twt.service.wenjin.ui.topic.list;

/**
 * Created by M on 2015/4/8.
 */
public interface TopicListPresenter {

    void loadMoreTopics(int uid, int type);

    void refreshTopics(int uid, int type);

}

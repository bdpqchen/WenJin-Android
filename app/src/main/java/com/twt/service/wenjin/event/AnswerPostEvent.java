package com.twt.service.wenjin.event;

/**
 * Created by jcy on 2016/7/25.
 */

public class AnswerPostEvent {
    private boolean PostStatus;

    public AnswerPostEvent(boolean postStatus) {
        PostStatus = postStatus;
    }

    public boolean isPostStatus() {
        return PostStatus;
    }

    public void setPostStatus(boolean postStatus) {
        PostStatus = postStatus;
    }
}

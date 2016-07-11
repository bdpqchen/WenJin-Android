package com.twt.service.wenjin.interactor;

import com.twt.service.wenjin.ui.profile.follows.OnGetFollowCallback;

/**
 * Created by Administrator on 2015/4/25.
 */
public interface FollowsInteractor {

    void getFollowersItems(int uid,String type, int page,int perPage,OnGetFollowCallback onGetFollowCallback);
}

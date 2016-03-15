package com.twt.service.wenjin.ui.conversation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twt.service.wenjin.R;
import com.twt.service.wenjin.WenJinApp;
import com.twt.service.wenjin.support.PrefUtils;
import com.twt.service.wenjin.ui.BaseFragment;
import com.twt.service.wenjin.ui.home.HomeModule;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Green on 16/2/16.
 */
public class ConversationFragment extends BaseFragment implements ConversationView{


    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new ConversationModule(this));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_conversation, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }





}

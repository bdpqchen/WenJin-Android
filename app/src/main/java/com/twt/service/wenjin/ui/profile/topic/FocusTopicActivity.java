package com.twt.service.wenjin.ui.profile.topic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.twt.service.wenjin.R;
import com.twt.service.wenjin.bean.Topic;
import com.twt.service.wenjin.support.LogHelper;
import com.twt.service.wenjin.ui.BaseActivity;
import com.twt.service.wenjin.ui.common.OnItemClickListener;
import com.twt.service.wenjin.ui.topic.detail.TopicDetailActivity;
import com.twt.service.wenjin.ui.topic.list.TopicListAdapter;
import com.twt.service.wenjin.ui.topic.list.TopicListModule;
import com.twt.service.wenjin.ui.topic.list.TopicListPresenter;
import com.twt.service.wenjin.ui.topic.list.TopicListView;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/7/12.
 */

public class FocusTopicActivity extends BaseActivity implements OnItemClickListener, TopicListView {

    private static final String USER_ID = "uid";
    private static final String USER_NAME = "username";
    private static final int TOPIC_TYPE_FOCUS = 1;

    @Inject
    TopicListPresenter presenter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.profile_topic_recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.tv_empty_topic)
    TextView textView;

    private TopicListAdapter topicListAdapter;
    private RecyclerView.OnScrollListener onScrollListener;
    private boolean isScrollListenerLoadingItems = false;

    int uid;
    String userName;

    public static void actionStart(Context context, int uid, String userName) {
        Intent intent = new Intent(context, FocusTopicActivity.class);
        intent.putExtra(USER_ID, uid);
        intent.putExtra(USER_NAME,userName);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_topic);
        ButterKnife.bind(this);

        uid = getIntent().getIntExtra(USER_ID,0);
        userName = getIntent().getStringExtra(USER_NAME);

        Log.d("lqy",userName);
        toolbar.setTitle(userName+getString(R.string.title_activity_profile_topic));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        topicListAdapter = new TopicListAdapter(this,this);
        recyclerView.setAdapter(topicListAdapter);
        onScrollListener = new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if(lastPosition == linearLayoutManager.getItemCount() -1 && dy > 0 && !isScrollListenerLoadingItems){
                    isScrollListenerLoadingItems = true;
                    presenter.loadMoreTopics(uid,TOPIC_TYPE_FOCUS);
                }
            }
        };
        recyclerView.addOnScrollListener(onScrollListener);
        presenter.refreshTopics(uid,TOPIC_TYPE_FOCUS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void updateTopics(Topic[] topics) {
        topicListAdapter.updateTopics(topics);
    }

    @Override
    public void addTopics(Topic[] topics) {
        topicListAdapter.addTopics(topics);
    }

    @Override
    public void startRefresh() {

    }
    @Override
    public void onItemClicked(View view, int position) {
        startTopicDetailActivity(position);
    }

    @Override
    public void stopRefresh() {

    }

    @Override
    public void showFooter() {
        topicListAdapter.setFooter(true);
    }

    @Override
    public void hideFooter() {
        topicListAdapter.setFooter(false);
    }

    @Override
    public void toastMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startTopicDetailActivity(int position) {
        Topic topic = topicListAdapter.getItem(position);
        TopicDetailActivity.actionStart(this, topic.topic_id, topic.topic_title);
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new FocusTopicModule(this));
    }

    @Override
    public void setEmptyText(String msg) {
        recyclerView.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.VISIBLE);
        textView.setText(msg);
    }
}

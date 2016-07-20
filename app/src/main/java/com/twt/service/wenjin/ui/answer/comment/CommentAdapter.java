package com.twt.service.wenjin.ui.answer.comment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.twt.service.wenjin.R;
import com.twt.service.wenjin.bean.Comment;
import com.twt.service.wenjin.support.FormatHelper;
import com.twt.service.wenjin.ui.common.OnItemClickListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by M on 2015/4/6.
 */
public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContect;
    private ArrayList<Comment> mDataSet = new ArrayList<>();
    private OnItemClickListener listener;

    public CommentAdapter(Context contect, OnItemClickListener listener) {
        this.mContect = contect;
        this.listener = listener;
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {

//        @InjectView(R.id.iv_comment_item_avatar)
//        ImageView ivAvatar;
        @Bind(R.id.tv_comment_item_username)
        TextView tvUsername;
        @Bind(R.id.tv_comment_item_content)
        TextView tvContent;
        @Bind(R.id.tv_comment_item_add_time)
        TextView tvAddTime;

        View rootView;

        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            rootView = itemView;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContect).inflate(R.layout.comment_list_item, viewGroup, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        Comment item = mDataSet.get(i);
        ItemHolder itemHolder = (ItemHolder) viewHolder;
        if (item.anonymous == 0) {

            itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(v, i);
                }
            });

            itemHolder.tvUsername.setText(item.user_info.nick_name);
        }
        if (item.at_user != null) {
            itemHolder.tvContent.setText(FormatHelper.formatCommentReply(item.message));
        } else {
            itemHolder.tvContent.setText(item.message);
        }
        itemHolder.tvAddTime.setText(FormatHelper.formatAddDateWithoutAddinString(item.time));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void updateData(Comment[] comments) {
        mDataSet.clear();
        for (Comment c : comments) {
            mDataSet.add(c);
        }
        notifyDataSetChanged();
    }

    public String getUsername(int position) {
        return mDataSet.get(position).user_info.nick_name;
    }
}

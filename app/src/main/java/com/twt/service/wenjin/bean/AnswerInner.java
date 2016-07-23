package com.twt.service.wenjin.bean;

/**
 * Created by Green on 16/3/23.
 */
public class AnswerInner {
    public int answer_id;

    public int question_id;

    public String answer_content;

    public long add_time;

    public int agree_count;

    public int uid;

    public int comment_count;

    public String user_name;

    public String nick_name;

    public String avatar_file;

    public String signature;

    public int user_vote_status;

    public int user_thanks_status;

    public int has_attach;

    public Attach[] attachs;

    public String[] attachs_ids;

    public UserInfo user_info;
}

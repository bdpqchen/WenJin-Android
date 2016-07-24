package com.twt.service.wenjin.bean;

import java.util.List;

/**
 * Created by RexSun on 15/7/17.
 */
public class Article {

    public Info article_info;
    public List<ArticleTopic> article_topics;


    public static class Info {
        public int id;
        public int uid;
        public String title;
        public String message;
        public int comments;
        public int has_attach;
        public int views;
        public int votes;
        public UserInfo user_info;
        public String signature;
        public VoteInfo vote_info;
        public Attach[] attachs;
        public String[] attachs_ids;
    }

    public static class ArticleTopic {
        public int topic_id;
        public String topic_title;
    }

    public static class VoteInfo{
        public int rating;
    }
}

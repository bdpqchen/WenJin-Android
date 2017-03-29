package com.twt.service.wenjin.bean;

/**
 * Created by M on 2015/3/24.
 */
public class UserInfo {

    public String avatar_file;

    public String uid;

    public String user_name;

    public String nick_name;

    public String email;

    public String fans_count;

    public String friend_count;

    public String question_count;

    public String answer_count;

    public String topic_focus_count;

    public String agree_count;

    public String thanks_count;

    public String answer_favorite_count;

    public int has_focus;

    public String signature;

    public String token;

    public String im_password;

    public Education education;

    public static class Education{
        public String education_years;
        public String school_name;
        public String departments;
        public String major;
    }
}

package com.twt.service.wenjin.bean;

/**
 * Created by jcy on 2016/7/21.
 */

public class JsResponseBean {

    /**
     * uid : 4795
     * user_name : miss976885345
     * nick_name : 如果，当时
     * avatar_file : http://wenjin.in/uploads/avatar/000/00/47/95_avatar_min.jpg
     * valid_email : 0
     * is_first_login : 0
     * signature : ................
     * cookie : {"nof__user_login":"rsqaxJFfaWdsbYOhXdeomaOWpcPSncKNXdGaqdRumW9xmWuZaGlWa2Cow9mj2aSmlZWUjbmArYNfrYRjtoinhX60iQ..","nof__Session":"d4ic5juo5f8ice1dj869kssk13"}
     */

    private RsmBean rsm;
    /**
     * rsm : {"uid":4795,"user_name":"miss976885345","nick_name":"如果，当时","avatar_file":"http://wenjin.in/uploads/avatar/000/00/47/95_avatar_min.jpg","valid_email":0,"is_first_login":0,"signature":"................","cookie":{"nof__user_login":"rsqaxJFfaWdsbYOhXdeomaOWpcPSncKNXdGaqdRumW9xmWuZaGlWa2Cow9mj2aSmlZWUjbmArYNfrYRjtoinhX60iQ..","nof__Session":"d4ic5juo5f8ice1dj869kssk13"}}
     * errno : 1
     * err : null
     */

    private int errno;
    private Object err;

    public RsmBean getRsm() {
        return rsm;
    }

    public void setRsm(RsmBean rsm) {
        this.rsm = rsm;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public Object getErr() {
        return err;
    }

    public void setErr(Object err) {
        this.err = err;
    }

    public static class RsmBean {
        private int uid;
        private String user_name;
        private String nick_name;
        private String avatar_file;
        private int valid_email;
        private int is_first_login;
        private String signature;
        /**
         * nof__user_login : rsqaxJFfaWdsbYOhXdeomaOWpcPSncKNXdGaqdRumW9xmWuZaGlWa2Cow9mj2aSmlZWUjbmArYNfrYRjtoinhX60iQ..
         * nof__Session : d4ic5juo5f8ice1dj869kssk13
         */

        private CookieBean cookie;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getAvatar_file() {
            return avatar_file;
        }

        public void setAvatar_file(String avatar_file) {
            this.avatar_file = avatar_file;
        }

        public int getValid_email() {
            return valid_email;
        }

        public void setValid_email(int valid_email) {
            this.valid_email = valid_email;
        }

        public int getIs_first_login() {
            return is_first_login;
        }

        public void setIs_first_login(int is_first_login) {
            this.is_first_login = is_first_login;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public CookieBean getCookie() {
            return cookie;
        }

        public void setCookie(CookieBean cookie) {
            this.cookie = cookie;
        }

        public static class CookieBean {
            private String nof__user_login;
            private String nof__Session;

            public String getNof__user_login() {
                return nof__user_login;
            }

            public void setNof__user_login(String nof__user_login) {
                this.nof__user_login = nof__user_login;
            }

            public String getNof__Session() {
                return nof__Session;
            }

            public void setNof__Session(String nof__Session) {
                this.nof__Session = nof__Session;
            }
        }
    }
}

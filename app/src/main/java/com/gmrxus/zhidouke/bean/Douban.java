package com.gmrxus.zhidouke.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mac on 2017/5/2.
 */

public class Douban {


    private int count;
    private int offset;
    private String date;
    private int total;
    private List<PostsBean> posts;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<PostsBean> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsBean> posts) {
        this.posts = posts;
    }

    public static class PostsBean {


        private int display_style;
        private boolean is_editor_choice;
        private String published_time;
        private String original_url;
        private String url;
        private String short_url;
        private boolean is_liked;
        private AuthorBean author;
        private String column;
        private int app_css;
        @SerializedName("abstract")
        private String abstractX;
        private String date;
        private int like_count;
        private int comments_count;
        private String created_time;
        private String title;
        private String share_pic_url;
        private String type;
        private int id;
        private List<?> thumbs;

        public int getDisplay_style() {
            return display_style;
        }

        public void setDisplay_style(int display_style) {
            this.display_style = display_style;
        }

        public boolean isIs_editor_choice() {
            return is_editor_choice;
        }

        public void setIs_editor_choice(boolean is_editor_choice) {
            this.is_editor_choice = is_editor_choice;
        }

        public String getPublished_time() {
            return published_time;
        }

        public void setPublished_time(String published_time) {
            this.published_time = published_time;
        }

        public String getOriginal_url() {
            return original_url;
        }

        public void setOriginal_url(String original_url) {
            this.original_url = original_url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getShort_url() {
            return short_url;
        }

        public void setShort_url(String short_url) {
            this.short_url = short_url;
        }

        public boolean isIs_liked() {
            return is_liked;
        }

        public void setIs_liked(boolean is_liked) {
            this.is_liked = is_liked;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public int getApp_css() {
            return app_css;
        }

        public void setApp_css(int app_css) {
            this.app_css = app_css;
        }

        public String getAbstractX() {
            return abstractX;
        }

        public void setAbstractX(String abstractX) {
            this.abstractX = abstractX;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getLike_count() {
            return like_count;
        }

        public void setLike_count(int like_count) {
            this.like_count = like_count;
        }

        public int getComments_count() {
            return comments_count;
        }

        public void setComments_count(int comments_count) {
            this.comments_count = comments_count;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShare_pic_url() {
            return share_pic_url;
        }

        public void setShare_pic_url(String share_pic_url) {
            this.share_pic_url = share_pic_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<?> getThumbs() {
            return thumbs;
        }

        public void setThumbs(List<?> thumbs) {
            this.thumbs = thumbs;
        }

        public static class AuthorBean {
            private boolean is_followed;
            private String uid;
            private String url;
            private String avatar;
            private String name;
            private boolean is_special_user;
            private int n_posts;
            private String alt;
            private String large_avatar;
            private String id;
            private boolean is_auth_author;

            public boolean isIs_followed() {
                return is_followed;
            }

            public void setIs_followed(boolean is_followed) {
                this.is_followed = is_followed;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isIs_special_user() {
                return is_special_user;
            }

            public void setIs_special_user(boolean is_special_user) {
                this.is_special_user = is_special_user;
            }

            public int getN_posts() {
                return n_posts;
            }

            public void setN_posts(int n_posts) {
                this.n_posts = n_posts;
            }

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public String getLarge_avatar() {
                return large_avatar;
            }

            public void setLarge_avatar(String large_avatar) {
                this.large_avatar = large_avatar;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public boolean isIs_auth_author() {
                return is_auth_author;
            }

            public void setIs_auth_author(boolean is_auth_author) {
                this.is_auth_author = is_auth_author;
            }
        }
    }
}

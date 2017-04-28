package com.gmrxus.zhidouke.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mac on 2017/4/24.
 */

public class ZhihuNews implements Serializable {


    private String date;
    private List<StoriesBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public static class StoriesBean implements Serializable {
        /**
         * images : ["http://pic2.zhimg.com/7ee215a4fb0b4c0b399a5c7f68749625.jpg"]
         * type : 0
         * id : 9165425
         * ga_prefix : 012122
         * title : 小事 · 被家教性骚扰
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        @Override
        public String toString() {
            return "StoriesBean{" +
                    "type=" + type +
                    ", id=" + id +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", title='" + title + '\'' +
                    ", multipic=" + multipic +
                    ", images=" + images +
                    '}';
        }
    }
}

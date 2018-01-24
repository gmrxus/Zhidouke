package com.gmrxus.zhidouke.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Gmrxus on 2018/1/11.
 */

public class DoubanStory {


  private int display_style;
  private String short_url;
  @SerializedName("abstract")
  private String abstractX;
  private int app_css;
  private int like_count;
  private String created_time;
  private int id;
  private boolean is_editor_choice;
  private String original_url;
  private String content;
  private String share_pic_url;
  private String type;
  private boolean is_liked;
  private String published_time;
  private String url;
  private String column;
  private int comments_count;
  private String title;
  private List<ThumbsBean> thumbs;
  private List<PhotosBean> photos;

  public int getDisplay_style() {
    return display_style;
  }

  public void setDisplay_style(int display_style) {
    this.display_style = display_style;
  }

  public String getShort_url() {
    return short_url;
  }

  public void setShort_url(String short_url) {
    this.short_url = short_url;
  }

  public String getAbstractX() {
    return abstractX;
  }

  public void setAbstractX(String abstractX) {
    this.abstractX = abstractX;
  }

  public int getApp_css() {
    return app_css;
  }

  public void setApp_css(int app_css) {
    this.app_css = app_css;
  }

  public int getLike_count() {
    return like_count;
  }

  public void setLike_count(int like_count) {
    this.like_count = like_count;
  }

  public String getCreated_time() {
    return created_time;
  }

  public void setCreated_time(String created_time) {
    this.created_time = created_time;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isIs_editor_choice() {
    return is_editor_choice;
  }

  public void setIs_editor_choice(boolean is_editor_choice) {
    this.is_editor_choice = is_editor_choice;
  }

  public String getOriginal_url() {
    return original_url;
  }

  public void setOriginal_url(String original_url) {
    this.original_url = original_url;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
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

  public boolean isIs_liked() {
    return is_liked;
  }

  public void setIs_liked(boolean is_liked) {
    this.is_liked = is_liked;
  }

  public String getPublished_time() {
    return published_time;
  }

  public void setPublished_time(String published_time) {
    this.published_time = published_time;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getColumn() {
    return column;
  }

  public void setColumn(String column) {
    this.column = column;
  }

  public int getComments_count() {
    return comments_count;
  }

  public void setComments_count(int comments_count) {
    this.comments_count = comments_count;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<ThumbsBean> getThumbs() {
    return thumbs;
  }

  public void setThumbs(List<ThumbsBean> thumbs) {
    this.thumbs = thumbs;
  }

  public List<PhotosBean> getPhotos() {
    return photos;
  }

  public void setPhotos(List<PhotosBean> photos) {
    this.photos = photos;
  }

  public static class ThumbsBean {
    /**
     * medium : {"url":"https://img1.doubanio.com/view/presto/medium/public/t126448.jpg","width":460,"height":613}
     * description :
     * large : {"url":"https://img1.doubanio.com/view/presto/large/public/t126448.jpg","width":460,"height":613}
     * tag_name : img_1
     * small : {"url":"https://img1.doubanio.com/view/presto/small/public/t126448.jpg","width":320,"height":426}
     * id : 126448
     */

    private MediumBean medium;
    private String description;
    private LargeBean large;
    private String tag_name;
    private SmallBean small;
    private int id;

    public MediumBean getMedium() {
      return medium;
    }

    public void setMedium(MediumBean medium) {
      this.medium = medium;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public LargeBean getLarge() {
      return large;
    }

    public void setLarge(LargeBean large) {
      this.large = large;
    }

    public String getTag_name() {
      return tag_name;
    }

    public void setTag_name(String tag_name) {
      this.tag_name = tag_name;
    }

    public SmallBean getSmall() {
      return small;
    }

    public void setSmall(SmallBean small) {
      this.small = small;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public static class MediumBean {
      /**
       * url : https://img1.doubanio.com/view/presto/medium/public/t126448.jpg
       * width : 460
       * height : 613
       */

      private String url;
      private int width;
      private int height;

      public String getUrl() {
        return url;
      }

      public void setUrl(String url) {
        this.url = url;
      }

      public int getWidth() {
        return width;
      }

      public void setWidth(int width) {
        this.width = width;
      }

      public int getHeight() {
        return height;
      }

      public void setHeight(int height) {
        this.height = height;
      }
    }

    public static class LargeBean {
      /**
       * url : https://img1.doubanio.com/view/presto/large/public/t126448.jpg
       * width : 460
       * height : 613
       */

      private String url;
      private int width;
      private int height;

      public String getUrl() {
        return url;
      }

      public void setUrl(String url) {
        this.url = url;
      }

      public int getWidth() {
        return width;
      }

      public void setWidth(int width) {
        this.width = width;
      }

      public int getHeight() {
        return height;
      }

      public void setHeight(int height) {
        this.height = height;
      }
    }

    public static class SmallBean {
      /**
       * url : https://img1.doubanio.com/view/presto/small/public/t126448.jpg
       * width : 320
       * height : 426
       */

      private String url;
      private int width;
      private int height;

      public String getUrl() {
        return url;
      }

      public void setUrl(String url) {
        this.url = url;
      }

      public int getWidth() {
        return width;
      }

      public void setWidth(int width) {
        this.width = width;
      }

      public int getHeight() {
        return height;
      }

      public void setHeight(int height) {
        this.height = height;
      }
    }
  }

  public static class PhotosBean {
    /**
     * medium : {"url":"https://img1.doubanio.com/view/presto/medium/public/625759.jpg","width":460,"height":613}
     * description :
     * large : {"url":"https://img1.doubanio.com/view/presto/large/public/625759.jpg","width":460,"height":613}
     * tag_name : img_1
     * small : {"url":"https://img1.doubanio.com/view/presto/small/public/625759.jpg","width":320,"height":426}
     * id : 625759
     */

    private MediumBeanX medium;
    private String description;
    private LargeBeanX large;
    private String tag_name;
    private SmallBeanX small;
    private int id;

    public MediumBeanX getMedium() {
      return medium;
    }

    public void setMedium(MediumBeanX medium) {
      this.medium = medium;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public LargeBeanX getLarge() {
      return large;
    }

    public void setLarge(LargeBeanX large) {
      this.large = large;
    }

    public String getTag_name() {
      return tag_name;
    }

    public void setTag_name(String tag_name) {
      this.tag_name = tag_name;
    }

    public SmallBeanX getSmall() {
      return small;
    }

    public void setSmall(SmallBeanX small) {
      this.small = small;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public static class MediumBeanX {
      /**
       * url : https://img1.doubanio.com/view/presto/medium/public/625759.jpg
       * width : 460
       * height : 613
       */

      private String url;
      private int width;
      private int height;

      public String getUrl() {
        return url;
      }

      public void setUrl(String url) {
        this.url = url;
      }

      public int getWidth() {
        return width;
      }

      public void setWidth(int width) {
        this.width = width;
      }

      public int getHeight() {
        return height;
      }

      public void setHeight(int height) {
        this.height = height;
      }
    }

    public static class LargeBeanX {
      /**
       * url : https://img1.doubanio.com/view/presto/large/public/625759.jpg
       * width : 460
       * height : 613
       */

      private String url;
      private int width;
      private int height;

      public String getUrl() {
        return url;
      }

      public void setUrl(String url) {
        this.url = url;
      }

      public int getWidth() {
        return width;
      }

      public void setWidth(int width) {
        this.width = width;
      }

      public int getHeight() {
        return height;
      }

      public void setHeight(int height) {
        this.height = height;
      }
    }

    public static class SmallBeanX {
      /**
       * url : https://img1.doubanio.com/view/presto/small/public/625759.jpg
       * width : 320
       * height : 426
       */

      private String url;
      private int width;
      private int height;

      public String getUrl() {
        return url;
      }

      public void setUrl(String url) {
        this.url = url;
      }

      public int getWidth() {
        return width;
      }

      public void setWidth(int width) {
        this.width = width;
      }

      public int getHeight() {
        return height;
      }

      public void setHeight(int height) {
        this.height = height;
      }
    }
  }
}

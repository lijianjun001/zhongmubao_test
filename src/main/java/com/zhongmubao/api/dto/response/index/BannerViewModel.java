package com.zhongmubao.api.dto.response.index;

public class BannerViewModel {

    public BannerViewModel() {

    }

    public BannerViewModel(String id,String title,String link,String imgUrl) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.imgUrl = imgUrl;
    }
    private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    private String link;
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    private String imgUrl;
    public String getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

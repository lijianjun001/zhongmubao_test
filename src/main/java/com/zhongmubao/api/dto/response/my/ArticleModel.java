package com.zhongmubao.api.dto.response.my;

/**
 * 文章视图实体
 *
 * @author 米立林
 */
public class ArticleModel {
    public ArticleModel() {
    }

    public ArticleModel(String id, String title, String content, String url, String createdTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.url = url;
        this.createdTime = createdTime;
    }

    private String id;
    private String title;
    private String content;
    private String url;
    private String createdTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}

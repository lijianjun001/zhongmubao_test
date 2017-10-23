package com.zhongmubao.api.dto.response.notice;

public class PageNoticeViewModel {
    public PageNoticeViewModel() {
    }

    public PageNoticeViewModel(int id, String icon, String title, String content, String time) {
        this.id = id;
        this.icon = icon;
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private int id;
    private String icon;
    private String title;
    private String content;
    private String time;
}

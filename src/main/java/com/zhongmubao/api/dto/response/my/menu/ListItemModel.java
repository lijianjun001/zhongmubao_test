package com.zhongmubao.api.dto.response.my.menu;

/**
 * 个人中心 列表单项
 * @author xy
 */
public class ListItemModel {

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

    public String getJumpType() {
        return jumpType;
    }

    public void setJumpType(String jumpType) {
        this.jumpType = jumpType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 图标 文件名 最好不要 加路径
     */
    private String icon;
    /**
     * 名称标题
     */
    private String title;
    /**
     * 00 默认 01 URL 跳转
     */
    private String jumpType;
    /**
     * 跳转链接
     */
    private String url;
    /**
     *  标识
     */
    private String action;
}

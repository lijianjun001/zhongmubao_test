package com.zhongmubao.api.dto.response.message;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 消息中心实体
 *
 * @author 米立林
 */
public class CustomerMessageModel {
    /**
     * 主键
     */
    private String id;

    /**
     * 消息类型，eg:开标、礼物、银行、活动、年报等
     */
    private String messageTypeName;

    /**
     * 消息类型图标
     */
    private String messageTypeIcon;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 消息中心分类，eg:开标计划、系统消息、个人消息
     */
    private String type;

    /**
     * 是否内部跳转
     */
    private boolean isInside;

    /**
     * 标签
     */
    private String tips;

    /**
     * 背景颜色
     */
    private String backgroundColor;

    /**
     * 创建时间
     */
    private String created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageTypeName() {
        return messageTypeName;
    }

    public void setMessageTypeName(String messageTypeName) {
        this.messageTypeName = messageTypeName;
    }

    public String getMessageTypeIcon() {
        return messageTypeIcon;
    }

    public void setMessageTypeIcon(String messageTypeIcon) {
        this.messageTypeIcon = messageTypeIcon;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty(value = "isInside")
    public boolean getInside() {
        return isInside;
    }

    public void setInside(boolean inside) {
        isInside = inside;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}

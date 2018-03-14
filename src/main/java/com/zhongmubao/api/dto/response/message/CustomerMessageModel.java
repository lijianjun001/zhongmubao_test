package com.zhongmubao.api.dto.response.message;

/**
 * 消息中心实体
 *
 * @author 米立林
 */
public class CustomerMessageModel {

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
     * 是否已读
     */
    private boolean isRead;

    /**
     * 是否置顶
     */
    private boolean isTop;

    /**
     * 是否新消息
     */
    private boolean isNew;

    /**
     * 标签
     */
    private String tips;

    /**
     * 背景颜色
     */
    private String backColor;

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

    public boolean getIsRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean getIsTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }

    public boolean getIsNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getBackColor() {
        return backColor;
    }

    public void setBackColor(String backColor) {
        this.backColor = backColor;
    }
}

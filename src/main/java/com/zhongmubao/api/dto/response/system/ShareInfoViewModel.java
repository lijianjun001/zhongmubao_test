package com.zhongmubao.api.dto.response.system;

/**
 * 分享响应实体
 * @author 米立林
 */
public class ShareInfoViewModel {
    /**
     * 图片形式 00链接 01图片
     */
    private String type;
    /**
     * 00朋友 01朋友圈 02 好友+朋友圈 03 微博 04 朋友+微博 05 朋友圈+微博   其他全部（空字符串全部）
     */
    private String shareTo;
    /**
     * 分享成功连接
     */
    private String shareSuccessLink;
    /**
     * 00文字提示 01链接跳转 02自由处理
     */
    private String shareSuccessType;
    /**
     * 分享成功消息
     */
    private String shareSuccessMessage;

    private String shareTitle;
    private String shareContent;
    private String shareIcon;
    private String shareUrl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShareTo() {
        return shareTo;
    }

    public void setShareTo(String shareTo) {
        this.shareTo = shareTo;
    }

    public String getShareSuccessLink() {
        return shareSuccessLink;
    }

    public void setShareSuccessLink(String shareSuccessLink) {
        this.shareSuccessLink = shareSuccessLink;
    }

    public String getShareSuccessType() {
        return shareSuccessType;
    }

    public void setShareSuccessType(String shareSuccessType) {
        this.shareSuccessType = shareSuccessType;
    }

    public String getShareSuccessMessage() {
        return shareSuccessMessage;
    }

    public void setShareSuccessMessage(String shareSuccessMessage) {
        this.shareSuccessMessage = shareSuccessMessage;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }

    public String getShareIcon() {
        return shareIcon;
    }

    public void setShareIcon(String shareIcon) {
        this.shareIcon = shareIcon;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }
}

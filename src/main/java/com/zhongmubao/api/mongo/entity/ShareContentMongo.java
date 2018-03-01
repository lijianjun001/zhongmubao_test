package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * 分享内容
 * @author 米立林
 */
@Document(collection = "ShareContentMongo")
public class ShareContentMongo extends BaseModel {
    @Field("Name")
    private String name;
    @Field("Remark")
    private String remark;
    @Field("Type")
    private String type;
    @Field("Title")
    private String title;
    @Field("Content")
    private String content;
    @Field("Url")
    private String url;
    @Field("Icon")
    private String icon;
    @Field("Img")
    private String img;
    @Field("ShareTo")
    private String shareTo;
    @Field("MustLogin")
    private Boolean mustLogin;
    @Field("ShareSuccessType")
    private String shareSuccessType;
    @Field("ShareSuccessMessage")
    private String shareSuccessMessage;
    @Field("ShareSuccessLink")
    private String shareSuccessLink;
    @Field("Created")
    private Date created;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getShareTo() {
        return shareTo;
    }

    public void setShareTo(String shareTo) {
        this.shareTo = shareTo;
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

    public String getShareSuccessLink() {
        return shareSuccessLink;
    }

    public void setShareSuccessLink(String shareSuccessLink) {
        this.shareSuccessLink = shareSuccessLink;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getMustLogin() {
        return mustLogin;
    }

    public void setMustLogin(boolean mustLogin) {
        this.mustLogin = mustLogin;
    }
}

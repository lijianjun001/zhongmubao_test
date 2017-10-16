package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "ExtBannerMongo")
public class ExtBannerMongo extends BaseModel {

    @Field("SqlId")
    private int sqlId;
    @Field("Title")
    private String title;
    @Field("Link")
    private String link;
    @Field("ImgUrl")
    private String imgUrl;
    @Field("Sort")
    private int sort;
    @Field("Deleted")
    private boolean deleted;
    @Field("Created")
    private Date created;
    @Field("AsyncType")
    private int asyncType;

    public int getSqlId() {
        return sqlId;
    }

    public void setSqlId(int sqlId) {
        this.sqlId = sqlId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getAsyncType() {
        return asyncType;
    }

    public void setAsyncType(int asyncType) {
        this.asyncType = asyncType;
    }
}

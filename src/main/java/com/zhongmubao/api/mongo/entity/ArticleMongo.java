package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * 小程序文章
 *
 * @author 米立林
 */
@Document(collection = "ArticleMongo")
public class ArticleMongo extends BaseModel {
    @Field("Title")
    private String title;
    @Field("Content")
    private String content;
    @Field("Url")
    private String url;
    @Field("Deleted")
    private boolean deleted;
    @Field("Created")
    private Date created;

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

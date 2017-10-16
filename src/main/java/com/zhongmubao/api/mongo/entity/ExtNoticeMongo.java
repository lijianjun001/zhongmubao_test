package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "ExtNoticeMongo")
public class ExtNoticeMongo extends BaseModel {

    @Field("Title")
    private String title;
    @Field("Content")
    private String content;
    @Field("Pic")
    private String pic;
    @Field("Link")
    private int link;
    @Field("Type")
    private Date type;
    @Field("Created")
    private Date created;
    @Field("Delete")
    private boolean delete;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String title) {
        this.content = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String type) {
        this.pic = type;
    }

    public int getLink() {
        return link;
    }

    public void setLink(int count) {
        this.link = count;
    }

    public Date getType() {
        return type;
    }

    public void setType(Date exceed) {
        this.type = exceed;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}

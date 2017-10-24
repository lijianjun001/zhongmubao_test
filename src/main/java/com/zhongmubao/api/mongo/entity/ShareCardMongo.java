package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * 分享卡
 *
 * @author 孙阿龙
 */
@Document(collection = "ShareCardMongo")
public class ShareCardMongo extends BaseModel {

    @Field("CustomerId")
    private int customerId;
    @Field("Title")
    private String title;
    @Field("Type")
    private String type;
    @Field("Count")
    private int count;
    @Field("Created")
    private Date created;
    @Field("Exceed")
    private Date exceed;
    @Field("Status")
    private String status;
    @Field("Delete")
    private boolean delete;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getExceed() {
        return exceed;
    }

    public void setExceed(Date exceed) {
        this.exceed = exceed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}

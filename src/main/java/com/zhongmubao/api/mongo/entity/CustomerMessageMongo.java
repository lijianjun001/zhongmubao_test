package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;


/**
 * 客户消息
 *
 * @author 米立林
 */
@Document(collection = "CustomerMessageMongo")
public class CustomerMessageMongo extends BaseModel {

    /**
     * 客户ID
     */
    @Field("CustomerId")
    private int customerId;

    /**
     * 标题
     */
    @Field("Title")
    private String title;

    /**
     * 内容
     */
    @Field("Content")
    private String content;

    /**
     * 类型（开标计划、系统消息、个人消息）
     */
    @Field("Type")
    private String type;

    /**
     * 分类消息类型（个人消息、礼物、开标、银行、活动、年报等）
     */
    @Field("CustomerMessageTypeId")
    private String customerMessageTypeId;

    /**
     * 消息标记（标记提示、背景颜色等）
     */
    @Field("TipsIdentification")
    private int tipsIdentification;

    /**
     *
     */
    @Field("IsRead")
    private boolean isRead;

    /**
     * 逻辑删除
     */
    @Field("Deleted")
    private boolean deleted;

    /**
     * 创建时间
     */
    @Field("Created")
    private Date created;


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerMessageTypeId() {
        return customerMessageTypeId;
    }

    public void setCustomerMessageTypeId(String customerMessageTypeId) {
        this.customerMessageTypeId = customerMessageTypeId;
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

    public int getTipsIdentification() {
        return tipsIdentification;
    }

    public void setTipsIdentification(int tipsIdentification) {
        this.tipsIdentification = tipsIdentification;
    }

    public boolean getRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
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
}

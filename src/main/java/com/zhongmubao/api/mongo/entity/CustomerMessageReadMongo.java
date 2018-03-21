package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * 用户系统消息已读管理
 *
 * @author 米立林
 */
@Document(collection = "CustomerMessageReadMongo")
public class CustomerMessageReadMongo extends BaseModel {
    /**
     * 客户ID
     */
    @Field("CustomerId")
    private int customerId;

    /**
     * 消息ID
     */
    @Field("MessageId")
    private String messageId;

    /**
     * 已读时间
     */
    @Field("Created")
    private Date created;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

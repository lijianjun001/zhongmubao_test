package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * 汇付首页弹框
 * @author xy
 */
@Document(collection = "CustomerHFIndexMongo")
public class CustomerHFIndexMongo extends BaseModel {
    @Field("CustomerId")
    private int customerId;
    @Field("Created")
    private Date created;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}

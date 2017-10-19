package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "CustomerOrderLogMongo")
public class CustomerOrderLogMongo extends BaseModel {
    public int getSheepOrderId() {
        return sheepOrderId;
    }

    public void setSheepOrderId(int sheepOrderId) {
        this.sheepOrderId = sheepOrderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Field("SheepOrderId")
    private int sheepOrderId;
    @Field("CustomerId")
    private int customerId;
}

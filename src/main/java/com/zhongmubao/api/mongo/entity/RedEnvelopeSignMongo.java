package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * RedEnvelopeSignMongo
 *
 * @author 孙阿龙
 */
@Document(collection = "RedEnvelopeSignMongo")
public class RedEnvelopeSignMongo extends BaseModel {
    @Field("CustomerId")
    private int customerId;
    @Field("Sign")
    private String sign;

    @Field("Deleted")
    private boolean deleted;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}

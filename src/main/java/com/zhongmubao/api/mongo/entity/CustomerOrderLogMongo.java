package com.zhongmubao.api.mongo.entity;


import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 用户买羊日志
 * @author 米立林
 */
@Document(collection = "CustomerOrderLogMongo")
public class CustomerOrderLogMongo extends BaseModel {
    @Field("CustomerId")
    private int customerId;

    @Field("SheepOrderId")
    private int sheepOrderId;
}

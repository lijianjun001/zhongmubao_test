package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "SystemTokenMongo")
public class SystemTokenMongo extends BaseModel {

    @Field("SqlId")
    public int sqlId;

    @Field("Platform")
    public String platform;

    @Field("CustomerId")
    public int customerId;

    @Field("Token")
    public String token;

    @Field("Expired")
    public Date expired;

    @Field("Created")
    public Date created;

    @Field("Modified")
    public Date modified;
}

package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * SystemServerActionMongo
 *
 * @author 孙阿龙
 */
@Document(collection = "SystemServerActionMongo")
public class SystemServerActionMongo extends BaseModel {
    @Field("Name")
    private String name;

    @Field("ParentObjectId")
    private String parentObjectId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentObjectId() {
        return parentObjectId;
    }

    public void setParentObjectId(String parentObjectId) {
        this.parentObjectId = parentObjectId;
    }
}

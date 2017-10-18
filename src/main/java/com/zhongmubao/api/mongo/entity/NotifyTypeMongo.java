package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 提醒通知类型
 * @author 米立林
 * @date 2017-10-18
 */
@Document(collection = "NotifyTypeMongo")
public class NotifyTypeMongo extends BaseModel {

    @Field("Type")
    private String type;

    @Field("TypeStr")
    private String typeStr;

    @Field("TypeInfo")
    private String typeInfo;

    @Field("TypeDays")
    private String typeDays;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getTypeInfo() {
        return typeInfo;
    }

    public void setTypeInfo(String typeInfo) {
        this.typeInfo = typeInfo;
    }

    public String getTypeDays() {
        return typeDays;
    }

    public void setTypeDays(String typeDays) {
        this.typeDays = typeDays;
    }
}

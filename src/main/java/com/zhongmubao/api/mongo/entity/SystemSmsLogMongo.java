package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * 系统验证码
 *
 * @author 米立林
 */
@Document(collection = "SystemSmsLogMongo")
public class SystemSmsLogMongo extends BaseModel {
    @Field("SqlId")
    private int sqlId;
    @Field("Type")
    private String type;
    @Field("Phone")
    private String phone;
    @Field("Code")
    private String code;
    @Field("Message")
    private String message;
    @Field("Expired")
    private Date expired;
    @Field("Created")
    private Date created;
    @Field("AsyncType")
    private int asyncType;

    public int getSqlId() {
        return sqlId;
    }

    public void setSqlId(int sqlId) {
        this.sqlId = sqlId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getAsyncType() {
        return asyncType;
    }

    public void setAsyncType(int asyncType) {
        this.asyncType = asyncType;
    }
}

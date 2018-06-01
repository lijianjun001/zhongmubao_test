package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * 登录ip黑名单
 *
 * @author 米立林
 */
@Document(collection = "LoginIpBlackListMongo")
public class LoginIpBlackListMongo extends BaseModel {
    @Field("Ip")
    private String ip;
    @Field("Created")
    private Date created;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

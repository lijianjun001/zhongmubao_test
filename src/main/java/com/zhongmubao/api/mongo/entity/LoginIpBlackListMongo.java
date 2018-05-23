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
    private int ip;
    @Field("Created")
    private Date created;

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

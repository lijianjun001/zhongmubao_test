package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * 登录请求列表
 *
 * @author 米立林
 */
@Document(collection = "LoginIpRequestListMongo")
public class LoginIpRequestListMongo extends BaseModel {
    @Field("Ip")
    private int ip;
    @Field("Account")
    private int account;
    @Field("Created")
    private Date created;

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

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
    public LoginIpRequestListMongo() {
    }

    public LoginIpRequestListMongo(String ip, String account, Date created) {
        this.ip = ip;
        this.account = account;
        this.created = created;
    }

    @Field("Ip")
    private String ip;
    @Field("Account")
    private String account;
    @Field("Created")
    private Date created;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

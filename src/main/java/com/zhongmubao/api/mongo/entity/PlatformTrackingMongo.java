package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * PlatformTrackingMongo实体
 *
 * @author 孙阿龙
 */
@Document(collection = "PlatformTrackingMongo")
public class PlatformTrackingMongo extends BaseModel {
    @Field("CustomerId")
    private int customerId;

    @Field("Platform")
    private String platform;

    @Field("IMIE")
    private String imie;

    @Field("MAC")
    private String mac;

    @Field("CreateTime")
    private Date createTime;

    @Field("Version")
    private String version;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;


/**
 * 养殖进度
 * @author 米立林
 */
@Document(collection = "SheepProgressMongo")
public class SheepProgressMongo extends BaseModel {
    @Field("Name")
    private String name;

    @Field("Day")
    private int day;

    @Field("Icon")
    private String icon;

    @Field("Remark")
    private String remark;

    @Field("Deleted")
    private int deleted;

    @Field("Period")
    private int period;

    /**
     * 养殖进度
     */
    @Field("Type")
    private String type;

    @Field("Created")
    private Date created;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

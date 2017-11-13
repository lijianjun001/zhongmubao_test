package com.zhongmubao.api.mongo.entity;


import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * 养殖流程
 * @author 米立林
 */
@Document(collection = "SheepStageMongo")
public class SheepStageMongo  extends BaseModel {

    public SheepStageMongo() {
    }

    public SheepStageMongo(String name, int day, String icon, String selectIcon, String remark, int delete, int period, String type, Date created, Date modified) {
        this.name = name;
        this.day = day;
        this.icon = icon;
        this.selectIcon = selectIcon;
        this.remark = remark;
        this.delete = delete;
        this.period = period;
        this.created = created;
        this.modified = modified;
        this.type = type;
    }

    @Field("Name")
    public String name;

    @Field("Day")
    public int day;

    @Field("Icon")
    public String icon;

    @Field("SelectIcon")
    public String selectIcon;

    @Field("Remark")
    public String remark;

    @Field("Delete")
    public int delete;

    @Field("Period")
    public int period;

    @Field("Type")
    public String type;

    @Field("Created")
    public Date created;

    @Field("Modified")
    public Date modified;

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

    public String getSelectIcon() {
        return selectIcon;
    }

    public void setSelectIcon(String selectIcon) {
        this.selectIcon = selectIcon;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
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

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

}

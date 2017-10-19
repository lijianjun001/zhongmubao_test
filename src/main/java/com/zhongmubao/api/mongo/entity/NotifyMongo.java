package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * 购羊提醒
 * @author 米立林
 */
@Document(collection = "NotifyMongo")
public class NotifyMongo extends BaseModel {

    @Field("CustomerId")
    private int customerId;

    @Field("SelectDate")
    private Date selectDate;

    @Field("Title")
    private String title;

    @Field("Type")
    private String type;

    @Field("Cyc")
    private String cyc;

    @Field("Time")
    private int time;

    @Field("Status")
    private String status;

    @Field("Delete")
    private boolean delete;

    @Field("Created")
    private Date created;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getSelectDate() {
        return selectDate;
    }

    public void setSelectDate(Date selectDate) {
        this.selectDate = selectDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCyc() {
        return cyc;
    }

    public void setCyc(String cyc) {
        this.cyc = cyc;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}

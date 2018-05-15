package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * 拆红包详情信息
 *
 * @author Yekai
 */
@Document(collection = "RedEnvelopeInfoMongo")
public class RedEnvelopeInfoMongo extends BaseModel {
    /**
     * 发起红包主键
     */
    @Field("RedEnvelopeId")
    private String redEnvelopeId;
    /**
     * 参与人主键
     */
    @Field("JoinCustomerId")
    private int joinCustomerId;
    /**
     * 抢到红包金额
     */
    @Field("Price")
    private String price;
    /**
     * 红包排列序号
     */
    @Field("JoinNo")
    private int joinNo;
    /**
     * 是否拆开红包
     */
    @Field("Opened")
    private boolean opened;
    /**
     * 参与时间
     */
    @Field("Created")
    private Date created;

    public String getRedEnvelopeId() {
        return redEnvelopeId;
    }

    public void setRedEnvelopeId(String redEnvelopeId) {
        this.redEnvelopeId = redEnvelopeId;
    }

    public int getJoinCustomerId() {
        return joinCustomerId;
    }

    public void setJoinCustomerId(int joinCustomerId) {
        this.joinCustomerId = joinCustomerId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getJoinNo() {
        return joinNo;
    }

    public void setJoinNo(int joinNo) {
        this.joinNo = joinNo;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

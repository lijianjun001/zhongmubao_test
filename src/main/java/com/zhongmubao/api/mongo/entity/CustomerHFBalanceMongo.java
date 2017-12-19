package com.zhongmubao.api.mongo.entity;

import com.zhongmubao.api.mongo.entity.base.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * 汇付交易余额改变记录表
 *
 * @author 米立林
 */
@Document(collection = "CustomerHFBalanceMongo")
public class CustomerHFBalanceMongo extends BaseModel {

    @Field("CustomerId")
    public int customerId;

    @Field("OrgBalance")
    public double orgBalance;

    @Field("AddBalance")
    public double addBalance;

    @Field("NowBalance")
    public double nowBalance;

    /**
     * 交易号
     */
    @Field("TransactionNum")
    public String transactionNum;

    /**
     * 00 充值 01 赎回 02 提现 03 投资
     */
    @Field("Type")
    public String type;

    /**
     * 00未处理 01 成功
     */
    @Field("Status")
    public String status;

    @Field("Remark")
    public String remark;

    @Field("CreateTime")
    public Date createTime;

    @Field("TransactionDetail")
    public String transactionDetail;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getOrgBalance() {
        return orgBalance;
    }

    public void setOrgBalance(double orgBalance) {
        this.orgBalance = orgBalance;
    }

    public double getAddBalance() {
        return addBalance;
    }

    public void setAddBalance(double addBalance) {
        this.addBalance = addBalance;
    }

    public double getNowBalance() {
        return nowBalance;
    }

    public void setNowBalance(double nowBalance) {
        this.nowBalance = nowBalance;
    }

    public String getTransactionNum() {
        return transactionNum;
    }

    public void setTransactionNum(String transactionNum) {
        this.transactionNum = transactionNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTransactionDetail() {
        return transactionDetail;
    }

    public void setTransactionDetail(String transactionDetail) {
        this.transactionDetail = transactionDetail;
    }
}

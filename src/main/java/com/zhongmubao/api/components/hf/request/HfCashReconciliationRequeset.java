package com.zhongmubao.api.components.hf.request;

import java.util.Date;

/**
 * 取现对账
 *
 * @author 米立林
 */
public class HfCashReconciliationRequeset extends HfBaseRequest {
    /**
     * 目前固定为 20，如版本升级，能向前兼容
     */
    private String version;
    /**
     * 每 一 种 消 息 类 型 代 表 一 种 交 易 ， 此 处 为CashReconciliation
     */
    private String cmdId;
    /**
     * 由汇付生成，商户的唯一性标识
     */
    private String merCustId;
    /**
     * YYYYMMDD 格式 BeginDate 和 EndDate 日期跨度不能大于 90 天
     */
    private Date beginDate;
    /**
     * YYYYMMDD 格式 BeginDate 和 EndDate 日期跨度不能大于 90 天
     */
    private Date endDate;
    /**
     * 查询数据的所在页号，>0 的整数
     */
    private String pageNum;
    /**
     * 查询数据的所在页号，>0 且<=1000 的整数
     */
    private String pageSize;
    /**
     * 加签验签的时候，商户需告知汇付其系统编码，汇付在验签的时候进行相应的编码转换验签
     * Version +CmdId + MerCustId+ BeginDate+ EndDate+ PageNum+ PageSize
     */
    private String chkvalue;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCmdId() {
        return cmdId;
    }

    public void setCmdId(String cmdId) {
        this.cmdId = cmdId;
    }

    public String getMerCustId() {
        return merCustId;
    }

    public void setMerCustId(String merCustId) {
        this.merCustId = merCustId;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getChkvalue() {
        return chkvalue;
    }

    public void setChkvalue(String chkvalue) {
        this.chkvalue = chkvalue;
    }
}

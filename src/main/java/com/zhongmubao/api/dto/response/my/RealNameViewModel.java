package com.zhongmubao.api.dto.response.my;

/**
 * 是否实名
 * @author xy
 */
public class RealNameViewModel {

    /**
     * 是否实名
     */
    private String realName;
    /**
     * XL 新浪 HF 汇付
     */
    private String realNameType;
    /**
     * 图标
     */
    private String realNameImg;
    /**
     * S 已开户(已实名) F 未开户(未实名) B 未激活
     */
    private String realNameSatus;

    /**
     * 汇付个人中心实名
     */
    private boolean centerShowHFRealName;
    /**
     * 汇付首页实名
     */
    private boolean indexShowHFRealName;
    /**
     * 新浪个人中心实名
     */
    private boolean centerShowSinaRealName;
    /**
     * 新浪首页实名
     */
    private boolean indexShowSinaRealName;
    /**
     * 用户类型（新老用户判断） L 老 X 新
     */
    private String customerType;
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealNameType() {
        return realNameType;
    }

    public void setRealNameType(String realNameType) {
        this.realNameType = realNameType;
    }

    public String getRealNameImg() {
        return realNameImg;
    }

    public void setRealNameImg(String realNameImg) {
        this.realNameImg = realNameImg;
    }

    public String getRealNameSatus() {
        return realNameSatus;
    }

    public void setRealNameSatus(String realNameSatus) {
        this.realNameSatus = realNameSatus;
    }

    public boolean getCenterShowHFRealName() {
        return centerShowHFRealName;
    }

    public void setCenterShowHFRealName(boolean centerShowHFRealName) {
        this.centerShowHFRealName = centerShowHFRealName;
    }

    public boolean getIndexShowHFRealName() {
        return indexShowHFRealName;
    }

    public void setIndexShowHFRealName(boolean indexShowHFRealName) {
        this.indexShowHFRealName = indexShowHFRealName;
    }

    public boolean getCenterShowSinaRealName() {
        return centerShowSinaRealName;
    }

    public void setCenterShowSinaRealName(boolean centerShowSinaRealName) {
        this.centerShowSinaRealName = centerShowSinaRealName;
    }

    public boolean getIndexShowSinaRealName() {
        return indexShowSinaRealName;
    }

    public void setIndexShowSinaRealName(boolean indexShowSinaRealName) {
        this.indexShowSinaRealName = indexShowSinaRealName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }


}

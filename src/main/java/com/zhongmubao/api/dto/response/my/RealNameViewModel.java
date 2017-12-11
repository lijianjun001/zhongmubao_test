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
     * 汇付首页显示
     */
    private boolean showHFIndex;
    /**
     * 汇付个人中心显示
     */
    private boolean showHFCenter;

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

    public boolean isShowHFIndex() {
        return showHFIndex;
    }

    public void setShowHFIndex(boolean showHFIndex) {
        this.showHFIndex = showHFIndex;
    }

    public boolean isShowHFCenter() {
        return showHFCenter;
    }

    public void setShowHFCenter(boolean showHFCenter) {
        this.showHFCenter = showHFCenter;
    }


}

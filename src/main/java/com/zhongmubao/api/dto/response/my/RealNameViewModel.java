package com.zhongmubao.api.dto.response.my;

/**
 * 是否实名
 * @author xy
 */
public class RealNameViewModel {

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

    public boolean getShowIndex() {
        return showIndex;
    }

    public void setShowIndex(boolean showIndex) {
        this.showIndex = showIndex;
    }

    public boolean getShowCenter() {
        return showCenter;
    }

    public void setShowCenter(boolean showCenter) {
        this.showCenter = showCenter;
    }

    private String realName;//是否实名
    private String realNameType;//XL 新浪 HF 汇付
    private String realNameImg;//图标
    private String realNameSatus;//S 已开户(已实名) F 未开户(未实名) B 未激活
    private boolean showIndex;//首页显示
    private boolean showCenter;//个人中心显示
}

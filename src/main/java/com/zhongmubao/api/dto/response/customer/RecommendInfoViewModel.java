package com.zhongmubao.api.dto.response.customer;

/**
 * 客户信息
 * @author 米立林
 */
public class RecommendInfoViewModel {
    private String nickName;
    private String phone;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

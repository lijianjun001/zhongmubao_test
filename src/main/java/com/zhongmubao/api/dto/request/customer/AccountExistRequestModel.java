package com.zhongmubao.api.dto.request.customer;

import com.zhongmubao.api.dto.request.BaseRequest;

/**
 * 账号请求实体
 *
 * @author 米立林
 */
public class AccountExistRequestModel extends BaseRequest {
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}

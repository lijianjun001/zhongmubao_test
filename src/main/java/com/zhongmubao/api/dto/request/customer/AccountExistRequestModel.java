package com.zhongmubao.api.dto.request.customer;

import com.zhongmubao.api.dto.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 账号请求实体
 *
 * @author 米立林
 */
@ApiModel(value = "AccountExistRequestModel", description = "用户对象")
public class AccountExistRequestModel extends BaseRequest {
    @ApiModelProperty(value = "手机号")
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}

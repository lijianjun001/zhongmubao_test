package com.zhongmubao.api.dto.request.sign;

import com.zhongmubao.api.dto.request.BaseRequest;

import java.util.List;

/**
 * MegreCardRequestModel
 *
 * @author 孙阿龙
 */
public class MegreCardRequestModel extends BaseRequest {
    public MegreCardRequestModel() {
    }

    private List<Integer> packageIds;

    public List<Integer> getPackageIds() {
        return packageIds;
    }

    public void setPackageIds(List<Integer> packageIds) {
        this.packageIds = packageIds;
    }
}

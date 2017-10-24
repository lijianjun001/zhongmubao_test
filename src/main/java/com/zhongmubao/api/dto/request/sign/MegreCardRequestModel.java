package com.zhongmubao.api.dto.request.sign;

import java.util.List;

/**
 * MegreCardRequestModel
 *
 * @author 孙阿龙
 */
public class MegreCardRequestModel {
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

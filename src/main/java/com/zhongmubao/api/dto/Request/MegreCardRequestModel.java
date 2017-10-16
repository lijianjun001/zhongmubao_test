package com.zhongmubao.api.dto.Request;

import java.util.List;

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

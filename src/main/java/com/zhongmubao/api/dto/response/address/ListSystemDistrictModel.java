package com.zhongmubao.api.dto.response.address;

import java.util.List;

public class ListSystemDistrictModel {

    public ListSystemDistrictModel(List<SystemDistrictViewModel> list){
        this.list=list;
    }

    private List<SystemDistrictViewModel> list;

    public List<SystemDistrictViewModel> getList() {
        return list;
    }

    public void setList(List<SystemDistrictViewModel> list) {
        this.list = list;
    }
}

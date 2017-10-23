package com.zhongmubao.api.dto.response.address;

import java.util.List;

public class CustomerAddressResponseModel {

    public CustomerAddressResponseModel(List<CustomerAddressViewModel> list) {
        this.list = list;
    }

    private List<CustomerAddressViewModel> list;

    public List<CustomerAddressViewModel> getList() {
        return list;
    }
    public void setList(List<CustomerAddressViewModel> list) {
        this.list = list;
    }
}

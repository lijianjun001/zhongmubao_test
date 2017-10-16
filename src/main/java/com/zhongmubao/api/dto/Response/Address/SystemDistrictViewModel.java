package com.zhongmubao.api.dto.Response.Address;

public class SystemDistrictViewModel {

    public SystemDistrictViewModel(String code, String parentCode, String name) {
        this.code = code;
        this.parentCode = parentCode;
        this.name = name;
    }

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String parentCode;

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

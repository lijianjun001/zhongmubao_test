package com.zhongmubao.api.components.hf;

/**
 * @author 孙阿龙
 */
public class BaseModel {

    public BaseModel() {
    }

    public BaseModel(int sort, String key, String value) {
        this.sort = sort;
        this.key = key;
        this.value = value;
    }

    private int sort;
    private String key;
    private String value;

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

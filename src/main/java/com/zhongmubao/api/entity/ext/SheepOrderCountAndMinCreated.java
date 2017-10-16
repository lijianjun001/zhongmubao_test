package com.zhongmubao.api.entity.ext;

public class SheepOrderCountAndMinCreated {
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    private int count;
    private String created;
}

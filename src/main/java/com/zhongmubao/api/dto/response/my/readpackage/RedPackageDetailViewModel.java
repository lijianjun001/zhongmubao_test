package com.zhongmubao.api.dto.response.my.readpackage;

import java.util.ArrayList;

/**
 * 红包详情
 *
 * @author 米立林
 */
public class RedPackageDetailViewModel {
    /**
     * 红包名称
     */
    private String name;

    /**
     * 红包面额
     */
    private String price;

    /**
     * 使用时间
     */
    private String useTime;

    /**
     * 使用规则
     */
    private ArrayList<String> useRule;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public ArrayList<String> getUseRule() {
        return useRule;
    }

    public void setUseRule(ArrayList<String> useRule) {
        this.useRule = useRule;
    }
}

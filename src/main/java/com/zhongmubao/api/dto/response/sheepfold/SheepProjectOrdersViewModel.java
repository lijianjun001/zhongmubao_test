package com.zhongmubao.api.dto.response.sheepfold;

/**
 * 羊标订单列表
 * @author 米立林
 */
public class SheepProjectOrdersViewModel {
    private int id;
    private String code;
    private int sheepCount;

    public SheepProjectOrdersViewModel(int id, String code, int sheepCount) {
        this.id = id;
        this.code = code;
        this.sheepCount = sheepCount;
    }

    public SheepProjectOrdersViewModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSheepCount() {
        return sheepCount;
    }

    public void setSheepCount(int sheepCount) {
        this.sheepCount = sheepCount;
    }
}

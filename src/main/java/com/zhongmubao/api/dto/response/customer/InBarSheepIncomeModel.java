package com.zhongmubao.api.dto.response.customer;

import java.util.List;

/**
 * 在栏羊只收益
 * @author 米立林
 */
public class InBarSheepIncomeModel {
    /**
     * 本金
     */
    private String principal;

    /**
     * 预计收益
     */
    private String enIncome;

    /**
     * 页数
     */
    private int pageCount;

    /**
     * 在栏羊只订单收益
     */
    private List<InBarSheepIncomeViewModel> list;

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getEnIncome() {
        return enIncome;
    }

    public void setEnIncome(String enIncome) {
        this.enIncome = enIncome;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<InBarSheepIncomeViewModel> getList() {
        return list;
    }

    public void setList(List<InBarSheepIncomeViewModel> list) {
        this.list = list;
    }
}

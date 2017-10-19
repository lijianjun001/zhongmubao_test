package com.zhongmubao.api.dto.Request.customer.farmIncome;

import java.util.List;

/**
 * 在栏羊只收益
 * @author 米立林
 */
public class InBarSheepIncomeModel {
    /**
     * 本金
     */
    private double principal;

    /**
     * 预计收益
     */
    private double enIncome;

    /**
     * 页数
     */
    private int pageCount;

    /**
     * 在栏羊只订单收益
     */
    private List<InBarSheepIncomeViewModel> list;

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public double getEnIncome() {
        return enIncome;
    }

    public void setEnIncome(double enIncome) {
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

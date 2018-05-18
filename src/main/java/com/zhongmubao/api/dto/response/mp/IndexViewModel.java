package com.zhongmubao.api.dto.response.mp;

import com.zhongmubao.api.dto.response.my.ArticleModel;

import java.util.List;

/**
 * 我的首页
 *
 * @author 米立林
 */
public class IndexViewModel {
    private String name;
    private String photo;
    private String totalAsset;
    private String totalIncome;
    private String monthIncome;
    private int totalPage;
    private List<ArticleModel> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTotalAsset() {
        return totalAsset;
    }

    public void setTotalAsset(String totalAsset) {
        this.totalAsset = totalAsset;
    }

    public String getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getMonthIncome() {
        return monthIncome;
    }

    public void setMonthIncome(String monthIncome) {
        this.monthIncome = monthIncome;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<ArticleModel> getList() {
        return list;
    }

    public void setList(List<ArticleModel> list) {
        this.list = list;
    }
}

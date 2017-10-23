package com.zhongmubao.api.dto.response.sheepfold;

import java.util.List;

/**
 * 羊标订单列表
 *
 * @author 米立林
 */
public class SheepProjectOrdersModel {
    private int sheepTotalCount;
    private String projectTitle;
    private List<SheepProjectOrdersViewModel> list;

    public int getSheepTotalCount() {
        return sheepTotalCount;
    }

    public void setSheepTotalCount(int sheepTotalCount) {
        this.sheepTotalCount = sheepTotalCount;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public List<SheepProjectOrdersViewModel> getList() {
        return list;
    }

    public void setList(List<SheepProjectOrdersViewModel> list) {
        this.list = list;
    }
}

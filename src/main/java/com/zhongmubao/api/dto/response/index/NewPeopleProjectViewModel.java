package com.zhongmubao.api.dto.response.index;

import java.util.List;

public class NewPeopleProjectViewModel {

    private boolean exped120;
    private boolean exped7;
    private boolean buyed120;
    private boolean buyed7;
    private boolean canReceiveXiaoEn;
    private List<ProjectViewModel> projectList;
    private boolean canBuy120;
    private boolean canBuy7;
    private int surplusDay7;

    public NewPeopleProjectViewModel() {
    }

    public NewPeopleProjectViewModel(boolean exped120, boolean exped7, boolean buyed120, boolean buyed7, boolean canBuy120, boolean canBuy7, boolean canReceiveXiaoEn, int surplusDay7) {
        this.exped120 = exped120;
        this.exped7 = exped7;
        this.buyed120 = buyed120;
        this.buyed7 = buyed7;
        this.canReceiveXiaoEn = canReceiveXiaoEn;
        this.canBuy120 = canBuy120;
        this.canBuy7 = canBuy7;
        this.surplusDay7 = surplusDay7;
    }

    public boolean isExped120() {
        return exped120;
    }

    public void setExped120(boolean exped120) {
        this.exped120 = exped120;
    }

    public boolean isExped7() {
        return exped7;
    }

    public void setExped7(boolean exped7) {
        this.exped7 = exped7;
    }

    public boolean isBuyed120() {
        return buyed120;
    }

    public void setBuyed120(boolean buyed120) {
        this.buyed120 = buyed120;
    }

    public boolean isBuyed7() {
        return buyed7;
    }

    public void setBuyed7(boolean buyed7) {
        this.buyed7 = buyed7;
    }

    public boolean isCanReceiveXiaoEn() {
        return canReceiveXiaoEn;
    }

    public void setCanReceiveXiaoEn(boolean canReceiveXiaoEn) {
        this.canReceiveXiaoEn = canReceiveXiaoEn;
    }

    public List<ProjectViewModel> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectViewModel> projectList) {
        this.projectList = projectList;
    }

    public boolean isCanBuy120() {
        return canBuy120;
    }

    public void setCanBuy120(boolean canBuy120) {
        this.canBuy120 = canBuy120;
    }

    public boolean isCanBuy7() {
        return canBuy7;
    }

    public void setCanBuy7(boolean canBuy7) {
        this.canBuy7 = canBuy7;
    }

    public int getSurplusDay7() {
        return surplusDay7;
    }

    public void setSurplusDay7(int surplusDay7) {
        this.surplusDay7 = surplusDay7;
    }
}

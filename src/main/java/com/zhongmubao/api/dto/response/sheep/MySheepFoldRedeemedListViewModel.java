package com.zhongmubao.api.dto.response.sheep;

import java.util.List;

public class MySheepFoldRedeemedListViewModel {
    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    private int totalPage;


    public List<MySheepFoldRedeemedViewModel> getList() {
        return list;
    }

    public void setList(List<MySheepFoldRedeemedViewModel> list) {
        this.list = list;
    }

    private List<MySheepFoldRedeemedViewModel> list;

}

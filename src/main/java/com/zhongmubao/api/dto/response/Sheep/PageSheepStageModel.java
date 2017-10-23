package com.zhongmubao.api.dto.response.Sheep;

import java.util.List;

public class PageSheepStageModel {

    private int pageCount;
    private List<SheepStageViewModel> list;

    public PageSheepStageModel() {
    }

    public PageSheepStageModel(int pageCount, List<SheepStageViewModel> list) {
        this.pageCount = pageCount;
        this.list = list;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<SheepStageViewModel> getList() {
        return list;
    }

    public void setList(List<SheepStageViewModel> list) {
        this.list = list;
    }
}

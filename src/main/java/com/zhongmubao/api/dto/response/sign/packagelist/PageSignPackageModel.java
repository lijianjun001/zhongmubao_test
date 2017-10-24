package com.zhongmubao.api.dto.response.sign.packagelist;

import java.util.List;

/**
 * @author 孙阿龙
 */
public class PageSignPackageModel {
    public PageSignPackageModel() {
    }

    public PageSignPackageModel(int pageCount,int cardTotalCount, List<SignPackageViewModel> list) {
        this.pageCount = pageCount;
        this.list = list;
        this.cardTotalCount = cardTotalCount;
    }


    private int pageCount;

    public int getCardTotalCount() {
        return cardTotalCount;
    }

    public void setCardTotalCount(int cardTotalCount) {
        this.cardTotalCount = cardTotalCount;
    }

    private int cardTotalCount;

    private List<SignPackageViewModel> list;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<SignPackageViewModel> getList() {
        return list;
    }

    public void setList(List<SignPackageViewModel> list) {
        this.list = list;
    }

}

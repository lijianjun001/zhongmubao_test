package com.zhongmubao.api.dto.Response.Sign.SignList;

import com.zhongmubao.api.dto.Response.Sign.SignGiftAddressViewModel;

import java.util.List;

public class PageSignGiftModel {

    public PageSignGiftModel() {
    }

    public PageSignGiftModel(int pageCount, List<PageSignGiftViewModel> list, SignGiftAddressViewModel signGiftAddress) {
        this.pageCount = pageCount;
        this.list = list;
        this.signGiftAddress = signGiftAddress;
    }

    private int pageCount;
    private List<PageSignGiftViewModel> list;
    private SignGiftAddressViewModel signGiftAddress;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<PageSignGiftViewModel> getList() {
        return list;
    }

    public void setList(List<PageSignGiftViewModel> list) {
        this.list = list;
    }

    public SignGiftAddressViewModel getSignGiftAddress() {
        return signGiftAddress;
    }

    public void setSignGiftAddress(SignGiftAddressViewModel signGiftAddress) {
        this.signGiftAddress = signGiftAddress;
    }

}

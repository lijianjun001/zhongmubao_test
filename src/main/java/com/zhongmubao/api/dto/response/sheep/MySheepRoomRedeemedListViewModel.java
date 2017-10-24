package com.zhongmubao.api.dto.response.sheep;

import java.util.List;

public class MySheepRoomRedeemedListViewModel {

    public MySheepRoomHeadViewModel getHead() {
        return head;
    }

    public void setHead(MySheepRoomHeadViewModel head) {
        this.head = head;
    }

    private MySheepRoomHeadViewModel head;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    private int totalPage;

    public List<MySheepRoomRedeemedViewModel> getList() {
        return list;
    }

    public void setList(List<MySheepRoomRedeemedViewModel> list) {
        this.list = list;
    }

    private List<MySheepRoomRedeemedViewModel> list;

}

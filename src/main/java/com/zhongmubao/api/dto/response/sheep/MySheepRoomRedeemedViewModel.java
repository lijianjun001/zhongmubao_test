package com.zhongmubao.api.dto.response.sheep;

import java.util.List;

public class MySheepRoomRedeemedViewModel {

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

    public List<MySheepRoomProjectViewModel> getList() {
        return list;
    }

    public void setList(List<MySheepRoomProjectViewModel> list) {
        this.list = list;
    }

    private List<MySheepRoomProjectViewModel> list;

}

package com.zhongmubao.api.dto.response.my.menu;

import java.util.List;

/**
 * 个人中心 列表
 * @author xy
 */
public class ListViewModel {

    public List<ListItemModel> getList() {
        return list;
    }

    public void setList(List<ListItemModel> list) {
        this.list = list;
    }

    private List<ListItemModel> list;
}

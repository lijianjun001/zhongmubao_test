package com.zhongmubao.api.dto.response.my.menu;

import java.util.List;

/**
 * 个人中心 列表
 * @author xy
 */
public class ListViewModel {

    public List<MenuItemModel> getList() {
        return list;
    }

    public void setList(List<MenuItemModel> list) {
        this.list = list;
    }

    private List<MenuItemModel> list;
}

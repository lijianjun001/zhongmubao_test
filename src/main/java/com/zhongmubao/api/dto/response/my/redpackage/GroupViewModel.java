package com.zhongmubao.api.dto.response.my.redpackage;

import java.util.ArrayList;

/**
 * 我的红包分组列表
 *
 * @author 孙阿龙
 */
public class GroupViewModel {

    /**
     * 红包分组集合
     */
    private ArrayList<GroupModel> list;

    public ArrayList<GroupModel> getList() {
        return list;
    }

    public void setList(ArrayList<GroupModel> list) {
        this.list = list;
    }
}

package com.zhongmubao.api.dto.response.my.redpackage;

import java.util.ArrayList;

/**
 * 我的红包分组列表
 *
 * @author 孙阿龙
 */
public class RedPackageGroupViewModel {

    /**
     * 红包分组集合
     */
    private ArrayList<RedPackageGroupModel> list;

    public ArrayList<RedPackageGroupModel> getList() {
        return list;
    }

    public void setList(ArrayList<RedPackageGroupModel> list) {
        this.list = list;
    }
}

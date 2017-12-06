package com.zhongmubao.api.dto.response.my.readpackage;

import java.util.ArrayList;

/**
 * 我的红包分组列表
 *
 * @author 孙阿龙
 */
public class ReadPackageGroupViewModel {

    /**
     * 红包分组集合
     */
    private ArrayList<ReadPackageGroupModel> list;

    public ArrayList<ReadPackageGroupModel> getList() {
        return list;
    }

    public void setList(ArrayList<ReadPackageGroupModel> list) {
        this.list = list;
    }
}

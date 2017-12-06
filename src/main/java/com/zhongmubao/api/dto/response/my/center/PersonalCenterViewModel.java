package com.zhongmubao.api.dto.response.my.center;

import java.util.List;

/**
 * 个人中心 列表
 * @author xy
 */
public class PersonalCenterViewModel {

    public List<PersonalCenterItemModel> getList() {
        return list;
    }

    public void setList(List<PersonalCenterItemModel> list) {
        this.list = list;
    }

    private List<PersonalCenterItemModel> list;
}

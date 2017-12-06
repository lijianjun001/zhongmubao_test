package com.zhongmubao.api.dto.response.customer.center;

import com.zhongmubao.api.dto.response.ReponseModel;
import com.zhongmubao.api.dto.response.customer.center.list.PersonalCenterItemModel;

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

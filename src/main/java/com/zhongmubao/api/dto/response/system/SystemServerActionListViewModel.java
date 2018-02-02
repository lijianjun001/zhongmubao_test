package com.zhongmubao.api.dto.response.system;

import java.util.ArrayList;
import java.util.List;

/**
 * SystemServerActionListViewModel
 *
 * @author 孙阿龙
 */
public class SystemServerActionListViewModel {
    public List<SystemServerActionModel> getList() {
        return list;
    }

    public void setList(List<SystemServerActionModel> list) {
        this.list = list;
    }

    private List<SystemServerActionModel> list;
}

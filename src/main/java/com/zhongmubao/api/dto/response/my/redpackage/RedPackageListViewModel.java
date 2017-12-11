package com.zhongmubao.api.dto.response.my.redpackage;

import java.util.ArrayList;

/**
 * 红包列表分页
 *
 * @author 孙阿龙
 */
public class RedPackageListViewModel {

    public RedPackageListViewModel() {
    }

    public RedPackageListViewModel(int totalPage, ArrayList<RedPackageModel> list) {
        this.totalPage = totalPage;
        this.list = list;
    }

    /**
     * 总页码
     */
    private int totalPage;

    /**
     * 红包集合
     */
    private ArrayList<RedPackageModel> list;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    public ArrayList<RedPackageModel> getList() {
        return list;
    }

    public void setList(ArrayList<RedPackageModel> list) {
        this.list = list;
    }
}

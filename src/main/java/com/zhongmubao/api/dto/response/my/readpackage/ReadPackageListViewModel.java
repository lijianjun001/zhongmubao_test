package com.zhongmubao.api.dto.response.my.readpackage;

import java.util.ArrayList;

/**
 * 红包列表分页
 *
 * @author 孙阿龙
 */
public class ReadPackageListViewModel {

    public ReadPackageListViewModel() {
    }

    public ReadPackageListViewModel(int totalPage, ArrayList<ReadPackageModel> list) {
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
    private ArrayList<ReadPackageModel> list;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    public ArrayList<ReadPackageModel> getList() {
        return list;
    }

    public void setList(ArrayList<ReadPackageModel> list) {
        this.list = list;
    }
}

package com.zhongmubao.api.mongo.entity.base;

import com.zhongmubao.api.config.Constants;

import java.util.List;

/**
 * 分页模型
 *
 * @param <T> 对象
 * @author 孙阿龙
 */
public class PageModel<T> {
    private List<T> datas;
    private int rowCount;
    private int pageSize = Constants.PAGE_SIZE;
    private int pageNo = 1;
    private int skip = 0;

    /**
     * 总页数
     * @return 总页码
     */
    public int getTotalPages() {
        return (rowCount + pageSize - 1) / pageSize;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSkip() {
        skip = (pageNo - 1) * pageSize;
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}

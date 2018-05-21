package com.zhongmubao.api.dto.response.mp.share;

import com.zhongmubao.api.dto.response.my.ArticleModel;

import java.util.List;

/**
 * 文章列表
 *
 * @author 米立林
 */
public class ListArticleViewModel {
    private int totalPage;
    private List<ArticleModel> list;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<ArticleModel> getList() {
        return list;
    }

    public void setList(List<ArticleModel> list) {
        this.list = list;
    }
}

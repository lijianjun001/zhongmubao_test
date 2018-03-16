package com.zhongmubao.api.dto.response.message;

import java.util.List;

/**
 * 开标详情列表
 *
 * @author 米立林
 */
public class ProjectMessageListViewModel {
    public ProjectMessageListViewModel() {
    }

    public ProjectMessageListViewModel(String title, String remark, List<ProjectWeekInfoModel> list) {
        this.title = title;
        this.remark = remark;
        this.list = list;
    }

    /**
     * 开标标题
     */
    private String title;
    /**
     * 开标信息
     */
    private String remark;

    /**
     * 周期
     */
    private List<ProjectWeekInfoModel> list;

    public List<ProjectWeekInfoModel> getList() {
        return list;
    }

    public void setList(List<ProjectWeekInfoModel> list) {
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

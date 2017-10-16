package com.zhongmubao.api.dto.Response.Index;

import java.util.List;

public class IndexModel {
    public IndexModel() {

    }

    public IndexModel(List<BannerViewModel> bannerList, List<ProjectViewModel> projectList, CustomerIndexModel customerIndex, NewPeopleProjectViewModel newPeopleProject, boolean showNewProject) {
        this.bannerList = bannerList;
        this.projectList = projectList;
        this.customerIndex = customerIndex;
        this.newPeopleProject = newPeopleProject;
        this.showNewProject = showNewProject;
    }

    private List<BannerViewModel> bannerList;
    private List<ProjectViewModel> projectList;

    public CustomerIndexModel getCustomerIndex() {
        return customerIndex;
    }

    public void setCustomerIndex(CustomerIndexModel customerIndex) {
        this.customerIndex = customerIndex;
    }

    private CustomerIndexModel customerIndex;
    private boolean showNewProject;
    private NewPeopleProjectViewModel newPeopleProject;

    public NewPeopleProjectViewModel getNewPeopleProject() {
        return newPeopleProject;
    }

    public void setNewPeopleProject(NewPeopleProjectViewModel newPeopleProject) {
        this.newPeopleProject = newPeopleProject;
    }

    public boolean isShowNewProject() {
        return showNewProject;
    }

    public void setShowNewProject(boolean showNewProject) {
        this.showNewProject = showNewProject;
    }

    public List<BannerViewModel> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<BannerViewModel> bannerList) {
        this.bannerList = bannerList;
    }

    public List<ProjectViewModel> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectViewModel> projectList) {
        this.projectList = projectList;
    }
}

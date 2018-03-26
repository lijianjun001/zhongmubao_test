package com.zhongmubao.api.dto.response.message;

import java.util.ArrayList;

/**
 * 消息中心
 *
 * @author 米立林
 */
public class CenterViewModel {

    /**
     * 开标信息
     */
    private ArrayList<CustomerMessageModel> projectList;
    /**
     * 系统信息
     */
    private ArrayList<CustomerMessageModel> systemList;
    /**
     * 个人信息
     */
    private ArrayList<CustomerMessageModel> personList;

    public ArrayList<CustomerMessageModel> getProjectList() {
        return projectList;
    }

    public void setProjectList(ArrayList<CustomerMessageModel> projectList) {
        this.projectList = projectList;
    }

    public ArrayList<CustomerMessageModel> getPersonList() {
        return personList;
    }

    public void setPersonList(ArrayList<CustomerMessageModel> personList) {
        this.personList = personList;
    }

    public ArrayList<CustomerMessageModel> getSystemList() {
        return systemList;
    }

    public void setSystemList(ArrayList<CustomerMessageModel> systemList) {
        this.systemList = systemList;
    }
}

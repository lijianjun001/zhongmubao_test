package com.zhongmubao.api.dto.response.message;

import java.util.ArrayList;

/**
 * 消息中心
 *
 * @author 米立林
 */
public class CenterViewModel {

    private ArrayList<CustomerMessageModel> projectList;
    private ArrayList<CustomerMessageModel> personList;
    private ArrayList<CustomerMessageModel> systemList;

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

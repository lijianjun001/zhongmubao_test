package com.zhongmubao.api.dto.response.sheep;

import java.util.List;

/**
 * 我的羊圈
 *
 * @author 米立林 2017-10-09
 */
public class MySheepfoldModel {

    /**
     * 牧羊级别
     */
    private int level;
    /**
     * 级别名称
     */
    private String levelName;
    /**
     * 买羊总数
     */
    private int sheepCount;

    /**
     * 牧场主养羊列表
     */
    private List<SheepOrderInfoViewModel> sheepOrderInfoList;

    public MySheepfoldModel(int level, String levelName, int sheepCount, List<SheepOrderInfoViewModel> sheepOrderInfoList) {
        this.level = level;
        this.levelName = levelName;
        this.sheepCount = sheepCount;
        this.sheepOrderInfoList = sheepOrderInfoList;
    }

    public MySheepfoldModel() {
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getSheepCount() {
        return sheepCount;
    }

    public void setSheepCount(int sheepCount) {
        this.sheepCount = sheepCount;
    }

    public List<SheepOrderInfoViewModel> getSheepOrderInfoList() {
        return sheepOrderInfoList;
    }

    public void setSheepOrderInfoList(List<SheepOrderInfoViewModel> sheepOrderInfoList) {
        this.sheepOrderInfoList = sheepOrderInfoList;
    }
}

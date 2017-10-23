package com.zhongmubao.api.dto;

/**
 * 当前羊项目状态
 */
public class CurrentSheepProjectState {
    /**
     * 养殖图片索引
     */
    private int dayTypeInt;
    /**
     * 养殖状态
     */
    private String dayType;

    public int getDayTypeInt() {
        return dayTypeInt;
    }

    public void setDayTypeInt(int dayTypeInt) {
        this.dayTypeInt = dayTypeInt;
    }

    public String getDayType() {
        return dayType;
    }

    public void setDayType(String dayType) {
        this.dayType = dayType;
    }
}

package com.zhongmubao.api.dto.Response.Sheep;

/**
 * 养殖流程介绍
 *
 * @author 米立林 2017-09-27
 */
public class SheepStageViewModel {
    private String name;
    private int period;
    private String icon;
    /**
     * 是否选中(当前进度) 0否 1是
     */
    private int isSelect;

    public SheepStageViewModel() {
    }

    public SheepStageViewModel(String name, int period, String icon, int isSelect) {
        this.name = name;
        this.period = period;
        this.icon = icon;
        this.isSelect = isSelect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(int isSelect) {
        this.isSelect = isSelect;
    }
}

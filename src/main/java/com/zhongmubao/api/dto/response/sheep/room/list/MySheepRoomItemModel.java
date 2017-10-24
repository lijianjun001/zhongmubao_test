package com.zhongmubao.api.dto.response.sheep.room.list;

/**
 * 我的羊圈 列表单项类
 * @author xy
 * @date 2017/10/24
 */
public class MySheepRoomItemModel {
    public MySheepRoomItemModel() {
    }


    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getVenderId() {
        return venderId;
    }

    public void setVenderId(int venderId) {
        this.venderId = venderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSheepPhoto() {
        return sheepPhoto;
    }

    public void setSheepPhoto(String sheepPhoto) {
        this.sheepPhoto = sheepPhoto;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getBreedProgress() {
        return breedProgress;
    }

    public void setBreedProgress(String breedProgress) {
        this.breedProgress = breedProgress;
    }

    public String getBreedProgressImg() {
        return breedProgressImg;
    }

    public void setBreedProgressImg(String breedProgressImg) {
        this.breedProgressImg = breedProgressImg;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public int getSurplusDay() {
        return surplusDay;
    }

    public void setSurplusDay(int surplusDay) {
        this.surplusDay = surplusDay;
    }

    private int projectId;
    private int venderId;
    private String title;
    private String sheepPhoto;
    private int count;
    private String beginTime;
    private String endTime;
    private String breedProgress;
    private String breedProgressImg;
    private String vendor;
    private int surplusDay;
}

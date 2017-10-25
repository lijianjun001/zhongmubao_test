package com.zhongmubao.api.dto.response.sheep.room.list;

/**
 * 我的羊圈 列表单项类
 *
 * @author xy
 * @date 2017/10/24
 */
public class SheepRoomItemModel {
    public SheepRoomItemModel() {
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

    /**
     * 项目Id
     */
    private int projectId;
    /**
     * 公司id （视频用）
     */
    private int venderId;
    /**
     * 标题
     */
    private String title;
    /**
     * 羊只头像
     */
    private String sheepPhoto;
    /**
     * 数量
     */
    private int count;
    /**
     * 开始时间
     */
    private String beginTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 养殖进度
     */
    private String breedProgress;
    /**
     * 养殖进度图
     */
    private String breedProgressImg;
    /**
     * 公司名称
     */
    private String vendor;
    /**
     * 剩余天数
     */
    private int surplusDay;
}

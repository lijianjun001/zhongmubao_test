package com.zhongmubao.api.dto.response.sheep.room;

/**
 * 我的羊圈 保险
 *
 * @author xy
 */
public class SheepRoomInsuranceViewModel {
    public SheepRoomInsuranceViewModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private String title;
    private String img;
}

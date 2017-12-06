package com.zhongmubao.api.config.enmu;

public enum RealNameStatus {
    HFS("已开户", "S", "HF"), HFF("未开户", "F", "HF"), HFB("未激活", "B", "HF"),
    XLS("已实名", "S", "XL"), XLF("未实名", "F", "XL");

    // 成员变量
    private String name;//S 已开户(已实名) F 未开户(未实名) B 未激活
    private String status;
    private String img;
    private String type;
    // 构造方法
    private RealNameStatus(String name, String status, String type) {
        this.name = name;
        this.status = status;
        this.type = type;
        if ("S".equals(this.status)) {
            this.img = "personal-shimingrenzheng.png";
        } else {
            this.img = "personal-shimingrenzheng.png";
        }
    }
    public String getImg() {
        return this.img;
    }
    public String getType() {
        return this.type;
    }
    public String getStatus() {
        return this.status;
    }

    public String getName() {
        return this.name;
    }
}

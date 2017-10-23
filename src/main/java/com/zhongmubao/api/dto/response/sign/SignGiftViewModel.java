package com.zhongmubao.api.dto.response.sign;

public class SignGiftViewModel {
    public SignGiftViewModel() {
    }

    public SignGiftViewModel(String id, String title, String type, int count, String unit, SignGiftRedPackageViewModel signGiftRedPackage, SignGiftAddressViewModel signGiftAddress, SignGiftCharge signGiftCharge) {
        this.id = id;
        this.type = type;
        this.count = count;
        this.signGiftRedPackage = signGiftRedPackage;
        this.signGiftAddress = signGiftAddress;
        this.title = title;
        this.unit = unit;
        this.signGiftCharge = signGiftCharge;
    }

    private String id;
    private String type;
    private int count;
    private String title;
    private SignGiftRedPackageViewModel signGiftRedPackage;
    private SignGiftAddressViewModel signGiftAddress;
    private String unit;
    private SignGiftCharge signGiftCharge;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public SignGiftRedPackageViewModel getSignGiftRedPackage() {
        return signGiftRedPackage;
    }

    public void setSignGiftRedPackage(SignGiftRedPackageViewModel signGiftRedPackage) {
        this.signGiftRedPackage = signGiftRedPackage;
    }

    public void setSignGiftAddress(SignGiftAddressViewModel signGiftAddress) {
        this.signGiftAddress = signGiftAddress;
    }

    public SignGiftAddressViewModel getSignGiftAddress() {
        return signGiftAddress;
    }

    public SignGiftCharge getSignGiftCharge() {
        return signGiftCharge;
    }

    public void setSignGiftCharge(SignGiftCharge signGiftCharge) {
        this.signGiftCharge = signGiftCharge;
    }
}

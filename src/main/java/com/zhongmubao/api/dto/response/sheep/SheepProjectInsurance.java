package com.zhongmubao.api.dto.response.sheep;

/**
 * 羊只有保险
 * @author 米立林
 * @date 2017-10-20
 */
public class SheepProjectInsurance {
    private int vendorId;
    private String insuranceCompanyUrl;
    private String vendorName;
    private String insuranceCompany;

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getInsuranceCompanyUrl() {
        return insuranceCompanyUrl;
    }

    public void setInsuranceCompanyUrl(String insuranceCompanyUrl) {
        this.insuranceCompanyUrl = insuranceCompanyUrl;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }
}

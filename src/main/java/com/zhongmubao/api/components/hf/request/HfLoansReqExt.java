package com.zhongmubao.api.components.hf.request;

/**
 * 自动扣款（放款）接口ReqExt
 *
 * @author 孙阿龙
 */
public class HfLoansReqExt {

//    /**
//     * 代金卷金额
//     */
//    private String loansVocherAmt;

    /**
     * 标的Id
     */
    private String proId;

//    public String getLoansVocherAmt() {
//        return loansVocherAmt;
//    }
//
//    public void setLoansVocherAmt(String loansVocherAmt) {
//        this.loansVocherAmt = loansVocherAmt;
//    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }
}

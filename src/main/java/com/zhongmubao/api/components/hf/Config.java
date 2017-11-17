/**
 * 说明： 配置信息仅供Demo使用，开发请根据实际情况配置
 * <p>
 * 汇付天下有限公司
 * <p>
 * Copyright (c) 2006-2013 ChinaPnR,Inc.All Rights Reserved.
 */
package com.zhongmubao.api.components.hf;

import java.io.Serializable;

/**
 * @author 孙阿龙
 */
public class Config implements Serializable {
    private static final long serialVersionUID = 3640874934537168392L;

    /**
     * 汇付HOST
     **/
    public static final String HTTP_HOST = "http://mertest.chinapnr.com/muser/publicRequests";

    /**
     * MD5签名类型
     **/
    public static final String SIGN_TYPE_MD5 = "M";

    /**
     * RSA签名类型
     **/
    public static final String SIGN_TYPE_RSA = "R";

    /**
     * RSA验证签名成功结果
     **/
    public static final int RAS_VERIFY_SIGN_SUCCESS = 0;

    /**
     * 商户客户号
     **/
    public static final String RECV_MER_ID = "531620";

    /**
     * 商户号*
     */
    public static final String MER_CUST_ID = "6000060007633813";

    /**
     * 商户公钥文件地址
     **/
    public static final String MER_PUB_KEY_PATH = "D:/hf/key/PgPubk.key";

    /**
     * 商户私钥文件地址
     **/
    public static final String MER_PRI_KEY_PATH = "D:/hf/key/MerPrK531620.key";

    /**
     * 充值回调地址
     **/
    public static final String DIRECT_RECHARGE_BG_URL = "";

    /**
     * 提现返回地址
     */
    public static final String CASH_RET_URL = "";

    /**
     * 提现回调地址
     */
    public static final String CASH_BG_RET_URL = "";

    /**
     * 自动扣款（放款）回调地址
     */
    public static final String LOANS_BG_URL = "";

}

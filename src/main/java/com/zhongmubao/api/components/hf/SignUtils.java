package com.zhongmubao.api.components.hf;

import chinapnr.SecureLink;

/**
 * 签名验证
 * @author 孙阿龙
 */
public class SignUtils {
    /**
     * RSA方式加签
     *
     * @param forEncryptionStr
     * @return 加密过后的字符串
     * @throws Exception 异常
     */
    public static String encryptByRSA(String forEncryptionStr) throws Exception {
        SecureLink sl = new SecureLink();
        int result = sl.SignMsg(Config.RECV_MER_ID, Config.MER_PRI_KEY_PATH, forEncryptionStr);
        if (result < 0) {
            // 打印日志
            throw new Exception();
        }
        return sl.getChkValue();
    }

    /**
     * 验证签名
     *
     * @param forEncryptionStr 被验证的字符串
     * @param chkValue         chk
     * @return 成功失败
     * @throws Exception 异常
     */
    public static boolean verifyByRSA(String forEncryptionStr, String chkValue)
            throws Exception {
        try {
            int verifySignResult = new SecureLink().VeriSignMsg(Config.MER_PUB_KEY_PATH, forEncryptionStr, chkValue);
            return verifySignResult == Config.RAS_VERIFY_SIGN_SUCCESS;
        } catch (Exception e) {
            // 打印日志
            throw new Exception();
        }
    }
}

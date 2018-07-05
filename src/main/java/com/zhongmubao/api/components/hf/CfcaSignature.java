package com.zhongmubao.api.components.hf;

import java.io.File;
import java.nio.charset.Charset;

import com.huifu.saturn.cfca.CFCASignature;
import com.huifu.saturn.cfca.SignResult;
import com.huifu.saturn.cfca.VerifyResult;
import com.zhongmubao.api.config.system.ConfigurationFields;

public class CfcaSignature {


    public static String NEW_LINE = System.getProperty("line.separator");
    /**
     * 证书存放路径
     */
    private static String root = ConfigurationFields.ROOT;
    /**
     * 商户证书名称
     */
    private static final String CFCA_MPFX_FILE_NAME = ConfigurationFields.CFCA_MPFX_FILE_NAME;
    /**
     * 商户证书密码，跟随证书变化
     */
    private static final String CFCA_MPFX_FILE_PWD = ConfigurationFields.CFCA_MPFX_FILE_PWD;
    /**
     * 证书链
     */
    private static final String CFCA_TRUST_CER_NAME = ConfigurationFields.CFCA_TRUST_CER_NAME;
    /**
     * 商户证书商户号，用于加密
     */
    private static final String CFCA_MER_ID = ConfigurationFields.CFCA_MER_ID;


    /**
     * 银行商户证书商户号，用于解密银行返回的数据
     */
    private static final String BOS_CFCA_MER_ID = ConfigurationFields.BOS_CFCA_MER_ID;


    /**
     * 字符编码集，请使用utf-8，不支持其他字符编码集
     */
    private static String DEFAULT_CHARSET = "utf-8";

    /**
     * CFCASignature.signature  方法说明
     * String pfxFilePath, String pfxFilePwd, String content, String charset
     * pfxFilePath  商户证书路径
     * pfxFilePwd	商户证书密码
     * content	需要钱吗字符串
     * charset 字符集 使用utf-8编码，其他编码不支持
     * 加密
     *
     * @param content 需要加密的字符串
     * @return 加密后字符串
     */
    public static String cfcaSEncrypt(String content) {

        String mpfxFilePath = root + File.separator + CFCA_MPFX_FILE_NAME;

        SignResult signResult = null;
        try {
            signResult = CFCASignature.signature(mpfxFilePath, CFCA_MPFX_FILE_PWD, content,
                    DEFAULT_CHARSET);
        } catch (Exception e) {
            // "加签异常....");
        }

        if (signResult == null || !CFCARespEnum.SUCCESS.getCode().equals(signResult.getCode())) {
            //"加签失败....");
        }
        return signResult.getSign();

    }

    /**
     * CFCASignature.verifyMerSign  方法说明
     * String merId, String signature, String charset, String trustCerPath
     * merId 为签名方证书商户号，因该方法是解密银行返回数据故需要银行证书商户号
     * signature  密文串
     * charset 字符集 使用utf-8编码，其他编码不支持
     * trustCerPath  证书链路径，既CFCA_TEST_OCA1.cer存放路径
     * 解密
     *
     * @param content 密文串
     * @return 解密后字符串
     */
    public static String cfcaSDncrypt(String content) {
        String trustCerPath = root + File.separator + CFCA_TRUST_CER_NAME;
        VerifyResult verifyResult = null;
        try {
            verifyResult = CFCASignature.verifyMerSign(BOS_CFCA_MER_ID, content, DEFAULT_CHARSET,
                    trustCerPath);
        } catch (Exception e) {
            System.out.println();
            //"解签异常....");
        }
        if (verifyResult == null || !CFCARespEnum.SUCCESS.getCode().equals(verifyResult.getCode())) {
            // "解签失败....");
        }
        return new String(verifyResult.getContent(), Charset.forName(DEFAULT_CHARSET));
    }

}

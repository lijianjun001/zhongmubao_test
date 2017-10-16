package com.zhongmubao.api.authorization.manager;

import com.zhongmubao.api.authorization.model.TokenModel;

/**
 * 对Token进行操作的接口
 */
public interface TokenManager {

    /**
     * 创建一个token关联上指定用户
     *
     * @param customerId 指定用户的id
     * @return 生成的token
     */
    TokenModel createToken(int customerId, String platform) throws Exception;

    /**
     * 检查token是否有效
     *
     * @param model token
     * @return 是否有效
     */
    boolean checkToken(TokenModel model);

    /**
     * 从字符串中解析token
     *
     * @param authentication 加密后的字符串
     * @param platform       平台
     * @return
     */
    TokenModel getToken(String authentication, String platform);

    /**
     * 清除token
     *
     * @param userId 登录用户的id
     */
    void deleteToken(int userId);

}

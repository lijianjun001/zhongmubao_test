package com.zhongmubao.api.authorization.manager.impl;

import com.zhongmubao.api.authorization.manager.TokenManager;
import com.zhongmubao.api.authorization.model.TokenModel;
import com.zhongmubao.api.util.DateUtil;
import com.zhongmubao.api.mongo.dao.SystemTokenMongoDao;
import com.zhongmubao.api.mongo.entity.SystemTokenMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * 通过Redis存储和验证token的实现类
 *
 * @see com.zhongmubao.api.authorization.manager.TokenManager
 */
@Component
public class TokenManagerImpl implements TokenManager {

    @Autowired
    private SystemTokenMongoDao systemTokenMongoDao;

    @Override
    public TokenModel createToken(int customerId, String platform) throws Exception {
        Date now = new Date();
        String token = UUID.randomUUID().toString().replace("-", "");
        SystemTokenMongo systemToken = systemTokenMongoDao.getByCustomerIdAndPlatform(customerId, platform);

        if (systemToken == null) {
            systemToken = new SystemTokenMongo();
            systemToken.created = now;
            systemToken.customerId = customerId;
            systemToken.platform = platform;
        }
        systemToken.token = token;
        systemToken.expired = DateUtil.addDay(now, 7);
        systemTokenMongoDao.save(systemToken);

        return new TokenModel(customerId, token);
    }

    @Override
    public boolean checkToken(TokenModel model) {

        return false;
    }

    @Override
    public TokenModel getToken(String authentication, String platform) {

        try {
            SystemTokenMongo systemToken = systemTokenMongoDao.getByTokenAndPlatform(authentication, platform);
            if (systemToken == null) return null;
            Date now = new Date();
            if (systemToken.expired.getTime() < now.getTime()) {
                return null;
            }
            return new TokenModel(systemToken.customerId, systemToken.token);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public void deleteToken(int userId) {

    }
}

package com.zhongmubao.api.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * @author 孙阿龙
 */
@Component
public class ApplicationStartServlet extends HttpServlet {

    @Autowired
    private Redis redis;

    @Override
    public void init(ServletConfig config) throws ServletException {

        // 缓存客户等级
        redis.setCustomerLevelCacheToRedis();

    }
}

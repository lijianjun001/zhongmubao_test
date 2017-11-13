package com.zhongmubao.api.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author 孙阿龙
 */
public class ApplicationStart implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private Redis redis;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (event.getApplicationContext().getParent() == null) {
            redis.init();
        }
    }
}

package com.zhongmubao.api.util.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Repository;

/**
 * mq消息生产者
 *
 * @author 孙阿龙
 */
@Repository
public class ProductorUtil {
    private AmqpTemplate amqpTemplate;

    public AmqpTemplate getAmqpTemplate() {
        return amqpTemplate;
    }

    public void setAmqpTemplate(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    /**
     * 发送数据到队列
     *
     * @param exchange   频道
     * @param routingKey 路由的key
     * @param obj        对象
     */
    public void sendDataToQueue(String exchange, String routingKey, Object obj) {
        amqpTemplate.convertAndSend(exchange, routingKey, obj);
    }
}

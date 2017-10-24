package com.zhongmubao.api.util.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * Redis帮助类
 *
 * @author 孙阿龙
 */
@Repository
public class RedisHelper {
    public final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisHelper(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    /**
     * 保存
     * @param key 键
     * @param value 值
     */
    public void save(String key, String value) {
        ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
        valueOper.set(key, value);
    }

    /**
     * 根据 key获取
     * @param key 键
     * @return 字符串
     */
    public String get(String key) {
        ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
        return valueOper.get(key);
    }

    /**
     * 根据key删除
     * @param key 键
     */
    public void remove(String key) {
        ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
        RedisOperations<String, String> redisOperations = valueOper.getOperations();
        redisOperations.delete(key);
    }

    /**
     * 获取HashTable
     * @param table 表名
     * @param key 键
     * @return 对象
     */
    public Object getHash(String table, String key) {
        HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
        return hashOper.get(table, key);
    }

    /**
     * 设置HashTable
     * @param table 表名
     * @param key 键
     * @param obj 对象
     */
    public void setHash(String table, String key, Object obj) {
        HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
        hashOper.put(table, key, obj);
    }
}

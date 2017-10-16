package com.zhongmubao.api.util.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisHelper {
    public final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisHelper(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(String key, String value) {
        /*redisTemplate.opsForList();
        redisTemplate.opsForSet();
        redisTemplate.opsForHash()*/
        ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
        valueOper.set(key, value);
    }

    public String get(String key) {
        ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
        return valueOper.get(key);
    }

    public void remove(String key) {
        ValueOperations<String, String> valueOper = redisTemplate.opsForValue();
        RedisOperations<String, String> RedisOperations = valueOper.getOperations();
        RedisOperations.delete(key);
    }

    public Object getHash(String table, String key) {
        HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
        return hashOper.get(table, key);
    }

    public void setHash(String table, String key, Object obj) {
        HashOperations<String, String, Object> hashOper = redisTemplate.opsForHash();
        hashOper.put(table, key, obj);
    }
}

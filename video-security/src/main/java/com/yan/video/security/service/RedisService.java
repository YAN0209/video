package com.yan.video.security.service;

/**
 * @author likaiyan
 * @date 2020/7/8 11:46 上午
 */
public interface RedisService {

    /**
     * 保存数据
     * @param key
     * @param value
     */
    void set(String key, String value);

    /**
     * 获取数据
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 设置过期时间
     * @param key
     * @param millis
     */
    void expire(String key, long millis);

    /**
     * 删除
     * @param key
     */
    void remove(String key);

}
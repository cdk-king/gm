package com.cdk.cache;

import java.util.Map;
import java.util.Set;

public interface ICacheManager {

    /***
     * 存入缓存实体
     * @param key
     * @param cache
     */
    void putCache(String key, EntityCache cache);

    /***
     * 存入缓存
     * @param key
     * @param datas
     * @param timeOut
     */
    void putCache(String key, Object datas, long timeOut);

    /***
     * 获取对应缓存实体
     * @param key
     * @return
     */
    EntityCache getCacheByKey(String key);

    /***
     * 获取对应缓存数据
     * @param key
     * @return
     */
    Object getCacheDataByKey(String key);

    /***
     * 获取所有缓存
     * @return
     */
    Map<String, EntityCache> getCachaeAll();

    /***
     * 判断是否在缓存中
     * @param key
     * @return
     */
    boolean isContains(String key);

    /***
     * 清楚所有缓存
     */
    void clearAll();

    /***
     * 清楚指定缓存
     * @param key
     */
    void clearByKey(String key);

    /***
     * 判断缓存是否超时失效
     * @param key
     * @return
     */
    boolean isTimeOut(String key);

    /***
     * 获取所有的key
     * @return
     */
    Set<String> getAllKeys();
}

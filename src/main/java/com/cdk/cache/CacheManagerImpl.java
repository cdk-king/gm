package com.cdk.cache;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class CacheManagerImpl implements ICacheManager {
    private static Map<String, EntityCache> caches = new ConcurrentHashMap<String, EntityCache>();

    /***
     * 存入缓存实体
     * @param key
     * @param cache
     */
    public void putCache(String key, EntityCache cache) {
        caches.put(key, cache);
    }

    /***
     * 存入缓存
     * @param key
     * @param datas
     * @param timeOut
     */
    public void putCache(String key, Object datas, long timeOut) {
        //数字后面跟大写的L，表明是一个长整型数
        timeOut = timeOut > 0 ? timeOut : 0L;
        // System.currentTimeMillis()返回当前的计算机时间，时间的表达格式为当前计算机时间和GMT时间(格林威治时间)1970年1月1号0时0分0秒所差的毫秒数。
        putCache(key, new EntityCache(datas, timeOut, System.currentTimeMillis()));
    }

    /***
     * 获取对应缓存实体
     * @param key
     * @return
     */
    public EntityCache getCacheByKey(String key) {
        if (this.isContains(key)) {
            return caches.get(key);
        }
        return null;
    }

    /***
     * 获取对应缓存数据
     * @param key
     * @return
     */
    public Object getCacheDataByKey(String key) {
        if (this.isContains(key)) {
            return caches.get(key).getDatas();
        }
        return null;
    }

    /***
     * 获取所有缓存
     * @return
     */
    public Map<String, EntityCache> getCachaeAll() {
        return caches;
    }

    /***
     * 判断是否在缓存中
     * @param key
     * @return
     */
    public boolean isContains(String key) {
        return caches.containsKey(key);
    }

    /***
     * 清楚所有缓存
     */
    public void clearAll() {
        caches.clear();
    }

    /***
     * 清楚指定缓存
     * @param key
     */
    public void clearByKey(String key) {
        if (this.isContains(key)) {
            caches.remove(key);
        }
    }

    /***
     * 判断缓存是否超时失效
     * @param key
     * @return
     */
    public boolean isTimeOut(String key) {
        if (!caches.containsKey(key)) {
            return true;
        }
        EntityCache cache = caches.get(key);
        long timeOut = cache.getTimeOut();
        long lastRefeshTime = cache.getLastRefeshTime();
        if (timeOut == 0 || System.currentTimeMillis() - lastRefeshTime >= timeOut) {
            return true;
        }
        return false;
    }

    /***
     * 获取所有的key
     * @return
     */
    public Set<String> getAllKeys() {
        return caches.keySet();
    }
}

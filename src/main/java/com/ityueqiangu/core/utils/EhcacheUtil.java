package com.ityueqiangu.core.utils;

import cn.hutool.core.util.ObjectUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Description:
 * @ProjectName: yueqian-base-ssm
 * @PackageName: com.ityueqiangu.core.utils
 * @ClassName: EhcacheUtil
 * @FileName: EhcacheUtil.java
 * @CreateDate: 2022-02-25 09:39:11
 * @Author: FlowerStone
 * @Copyright
 */
@Component
public class EhcacheUtil {

    @Autowired
    private CacheManager cacheManager;

    public Cache checkCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (null == cache) {
            throw new IllegalArgumentException("name=[" + cacheName + "],不存在对应的缓存组,请查看ehcache.xml");
        }
        return cache;
    }

    /**
     * 存入
     *
     * @param cacheName the cache name
     * @param key       键
     * @param value     值
     */
    public void put(String cacheName, String key, Object value) {
        Cache cache = this.checkCache(cacheName);
        Element e = new Element(key, value);
        cache.put(e);
        cache.flush();
    }

    /**
     * 存入 并设置元素是否永恒保存
     *
     * @param cacheName the cache name
     * @param key       键
     * @param value     值
     * @param eternal   对象是否永久有效，一但设置了，timeout将不起作用
     */
    public void put(String cacheName, String key, Object value, boolean eternal) {
        Cache cache = this.checkCache(cacheName);
        Element element = new Element(key, value);
        element.setEternal(eternal);
        cache.put(element);
        cache.flush();
    }

    /**
     * 存入
     *
     * @param cacheName         the cache name
     * @param key               键
     * @param value             值
     * @param timeToLiveSeconds 最大存活时间
     * @param timeToIdleSeconds 最大访问间隔时间
     */
    public void put(String cacheName, String key, Object value, int timeToLiveSeconds, int timeToIdleSeconds) {
        Cache cache = this.checkCache(cacheName);
        Element element = new Element(key, value);
        element.setTimeToLive(timeToLiveSeconds);
        element.setTimeToIdle(timeToIdleSeconds);
        cache.put(element);
        cache.flush();
    }

    /**
     * Get object.
     *
     * @param cacheName the cache name
     * @param key       the key
     * @return the object
     */
    public Object get(String cacheName, String key) {
        Cache cache = this.checkCache(cacheName);
        Element e = cache.get(key);
        if (e != null) {
            return e.getObjectValue();
        }
        return null;
    }

    /**
     * 判断是否存在
     *
     * @param cacheName 缓存名称
     * @param key  键值
     * @return
     * @date 2022年02月25日 0025 21:03:25
     */
    public boolean isExist(String cacheName, String key){
        Object getValue = this.get(cacheName, key);
        if (ObjectUtil.isNotEmpty(getValue)) {
            return true;
        }
        return false;
    }

    /**
     * Remove.
     *
     * @param cacheName the cache name
     * @param key       the key
     */
    public void remove(String cacheName, String key) {
        Cache cache = this.checkCache(cacheName);
        cache.remove(key);
    }

    /**
     * Remove all.
     *
     * @param cacheName the cache name
     * @param keys      the keys
     */
    public void removeAll(String cacheName, Collection<String> keys) {
        Cache cache = this.checkCache(cacheName);
        cache.removeAll(keys);
    }
}

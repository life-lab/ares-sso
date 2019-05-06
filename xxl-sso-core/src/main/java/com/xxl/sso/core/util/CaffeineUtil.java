//package com.xxl.sso.core.util;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cache.Cache;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.caffeine.CaffeineCacheManager;
//
//import java.util.concurrent.Callable;
//
//public class CaffeineUtil {
//
//    private static final Logger logger = LoggerFactory.getLogger(CaffeineUtil.class);
//
//    /**
//     * 默认本地缓存名称
//     */
//    private static final String NATIVE_CACHE_NAME = "CAFFEINE-CACHE-UTILS";
//    /**
//     * caffeine缓存管理器,负责caffeine的缓存实例的创建和管理。
//     *
//     * @see CacheManager
//     */
//    private static final CacheManager caffeineCacheManager = SpringUtil
//            .getBean("caffeineCacheManager", CaffeineCacheManager.class);
//
//
//    /**
//     * 本地缓存器
//     */
//    private static Cache NATIVE_CACHE;
//
//    /**
//     * 禁止实例化
//     */
//    private CaffeineUtil() {
//    }
//
//    static {
//        init();
//    }
//
//    /**
//     * 初始化缓存引擎 <p>这里会做一些，缓存管理器的初始化，缓存器的初始化等工作</>
//     */
//
//    private static void init() {
//        NATIVE_CACHE = caffeineCacheManager.getCache(NATIVE_CACHE_NAME);
//    }
//
//    /**
//     * @param key 指定的key值对象
//     * @return Object 缓存器返回的指定key值对应的值对象(由于考虑到本地缓存会占用系统内存，所以把本地缓存的值对象包装成SoftReference，当系统内存紧张时自动释放，避免内存溢出)
//     * @see Cache.ValueWrapper
//     */
//    public static Object get(Object key) {
//        Cache.ValueWrapper nativeValueWrapper = NATIVE_CACHE.get(key);
//        if (null != nativeValueWrapper && null != nativeValueWrapper.get()) {
//            return nativeValueWrapper.get();
//        }
//        return null;
//    }
//
//    public static Object get(final Object key, final Callable<Object> valueLoader) {
//        Object nativeValue = NATIVE_CACHE.get(key, valueLoader);
//        if (null != nativeValue) {
//            return nativeValue;
//        } else {
//            Object value;
//            try {
//                value = valueLoader.call();
//            } catch (Exception ex) {
//                throw new Cache.ValueRetrievalException(key, valueLoader, ex);
//            }
//            put(key, value);
//            return value;
//        }
//    }
//
//    public static void put(final Object key, final Object value) {
//        NATIVE_CACHE.put(key, value);
//    }
//
//    public static void evict(Object key) {
//        NATIVE_CACHE.evict(key);
//    }
//
//    public static void clear() {
//        NATIVE_CACHE.clear();
//    }
//
//}

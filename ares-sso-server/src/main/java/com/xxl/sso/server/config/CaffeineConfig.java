//package com.xxl.sso.server.config;
//
//import com.github.benmanes.caffeine.cache.Caffeine;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.caffeine.CaffeineCacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.concurrent.TimeUnit;
//
//
//@Configuration
//@EnableCaching
//public class CaffeineConfig {
//    public static final int DEFAULT_MAXSIZE = 10000;
//
//    /**
//     * 创建基于Caffeine的Cache Manager
//     *
//     * @return
//     */
//    @Bean(name = "caffeineCacheManager")
//    public CacheManager caffeineCacheManager() {
//        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
//        Caffeine<Object, Object> cache = Caffeine.newBuilder()
//                .expireAfterWrite(1, TimeUnit.MINUTES)
//                .weakKeys()
//                .weakValues()
//                .initialCapacity(100)
//                .maximumSize(DEFAULT_MAXSIZE)
//                .recordStats();
//        caffeineCacheManager.setCaffeine(cache);
//        return caffeineCacheManager;
//    }
//}
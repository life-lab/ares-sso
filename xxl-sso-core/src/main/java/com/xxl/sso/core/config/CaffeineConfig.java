package com.xxl.sso.core.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;


@Configuration
@EnableCaching //开启缓存
public class CaffeineConfig {
  public static final int DEFAULT_MAXSIZE = 10000;

  /**
   * 创建基于Caffeine的Cache Manager
   * @return
   */
  @Bean(name = "caffeineCacheManager")
  public CacheManager caffeineCacheManager() {
    CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
    Caffeine<Object, Object> cache = Caffeine.newBuilder()
        .expireAfterWrite(Duration.ofDays(1))
        .weakKeys()
        .weakValues()
        .initialCapacity(100)
        .maximumSize(DEFAULT_MAXSIZE)
        .recordStats();
    caffeineCacheManager.setCaffeine(cache);
    return caffeineCacheManager;
  }
}
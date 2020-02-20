package com.springboot.ms.movieratings.config;

import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

    @Bean
    public net.sf.ehcache.CacheManager ehCacheManager(){
        CacheConfiguration ratingsLruCache = new CacheConfiguration();
        ratingsLruCache.setName("Ratings-LRU-Cache");
        ratingsLruCache.setMaxEntriesLocalHeap(100);
        ratingsLruCache.setMemoryStoreEvictionPolicy("LRU");
        ratingsLruCache.setTimeToLiveSeconds(60);

        net.sf.ehcache.config.Configuration ehCacheConfig = new net.sf.ehcache.config.Configuration();
        ehCacheConfig.addCache(ratingsLruCache);
        return net.sf.ehcache.CacheManager.newInstance(ehCacheConfig);
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }
}

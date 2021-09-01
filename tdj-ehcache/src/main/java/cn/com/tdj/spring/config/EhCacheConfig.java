package cn.com.tdj.spring.config;


import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

/**
 *
 */
@Configuration
public class EhCacheConfig {

    @Value("${ehcache.config: ehcache.xml}")
    private String ehcacheConfig;

    @Bean
    public CacheManager createCacheManager() {
        URL url = Thread.currentThread().getContextClassLoader().getResource(ehcacheConfig);
        CacheManager cacheManager = CacheManager.create(url);
        return cacheManager;
    }
}
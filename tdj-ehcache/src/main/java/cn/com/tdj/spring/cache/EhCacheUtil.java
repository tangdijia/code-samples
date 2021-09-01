package cn.com.tdj.spring.cache;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
@Component
public class EhCacheUtil {

    public static final String CACHE_NAME = "testCache";

    @Autowired
    private CacheManager cacheManager;


    public void put(String key, Object value) {
        Element element = new Element(key, value);
        cacheManager.getCache(CACHE_NAME).put(element);
    }

    public Object get(String key) {
        Element element = cacheManager.getCache(CACHE_NAME).get(key);
        if (element == null) {
            return null;
        }
        return element.getObjectValue();
    }

    public void remove(String key) {
        cacheManager.getCache(CACHE_NAME).remove(key);
    }
}

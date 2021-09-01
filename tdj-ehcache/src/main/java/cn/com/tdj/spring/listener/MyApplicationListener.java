package cn.com.tdj.spring.listener;

import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class MyApplicationListener implements ApplicationListener {

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof ContextStoppedEvent) {
            System.out.println("Context Stopped Event");
        } else if (applicationEvent instanceof ContextClosedEvent) {
            System.out.println("Context Closed Event");
            cacheManager.shutdown();
        } else {
            //System.out.println(applicationEvent.getClass().getName());
        }
    }
}

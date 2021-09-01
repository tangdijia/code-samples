package cn.com.tdj.spring.controller;

import cn.com.tdj.spring.cache.EhCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
public class TestController {

    @Autowired
    private EhCacheUtil ehCacheUtil;

    @RequestMapping("/test1")
    public String test1() {
        String key = "a";
        String value = "aaa";
        ehCacheUtil.put(key, value);

        Object v = ehCacheUtil.get(key);
        System.out.println(v);

        return "success";
    }

    @RequestMapping("/test2")
    public String test2() {
        String key = "a";
        Object v = ehCacheUtil.get(key);
        System.out.println(v);
        return "success";
    }
}

package com.ityueqiangu.project.common.controller;

import com.ityueqiangu.core.annotation.NoLogined;
import com.ityueqiangu.core.utils.EhcacheUtil;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @ProjectName: yueqian-base
 * @PackageName: com.ityueqiangu.project.common.controller
 * @ClassName: TestController
 * @FileName: TestController.java
 * @CreateDate: 2022-03-30 14:22:12
 * @Author: FlowerStone
 * @Copyright
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private EhcacheUtil ehcacheUtil;

    @Autowired
    private CacheManager cacheManager;

    @GetMapping(value = "/addCache")
    @ResponseBody
    @NoLogined
    public ResponseInfo test(){
        ehcacheUtil.put("local","test1","这是一个完整的例子");
        //Cache local = cacheManager.getCache("local");
        //Element element = new Element("test", "我就是个传说");
        //local.put(element);
        //local.flush();
        //System.out.println("缓存成功");
        //Element test = local.get("test");
        return ResponseInfo.success();
    }

    @GetMapping(value = "/selectCache")
    @ResponseBody
    @NoLogined
    public ResponseInfo selectCache(){
        //Cache local1 = ehcacheUtil.checkCache("local");
        //Cache local = cacheManager.getCache("local");
        //Element result = local1.get("test");
        Object result = ehcacheUtil.get("local", "test1");
        return ResponseInfo.success(result);
    }
}

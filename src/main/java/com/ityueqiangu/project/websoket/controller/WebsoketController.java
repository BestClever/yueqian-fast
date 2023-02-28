package com.ityueqiangu.project.websoket.controller;

import cn.hutool.json.JSONUtil;
import com.ityueqiangu.core.annotation.NoLogined;
import com.ityueqiangu.core.websoket.NoticeWebSocketServer;
import com.ityueqiangu.core.websoket.WebSocketServer;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @ProjectName: yueqian-base
 * @PackageName: com.ityueqiangu.project.websoket.controller
 * @ClassName: WebsoketController
 * @FileName: WebsoketController.java
 * @CreateDate: 2022-03-15 16:41:10
 * @Author: FlowerStone
 * @Copyright
 */
@Controller
@RequestMapping(value = "/admin/websoketChat")
public class WebsoketController {

    @Autowired
    private NoticeWebSocketServer webSocketServer;

    @GetMapping(value = "/index")
    public String  index(){
        return "chat/index";
    }

    @GetMapping(value = "/pushmsg")
    @ResponseBody
    @NoLogined
    public ResponseInfo pushMsg(){
        ResponseInfo success = ResponseInfo.success("arrayList");
        success.put("count","12");
        String result = JSONUtil.parseObj(success).toString();
        webSocketServer.sendMessageTo(result,"admin");
        return ResponseInfo.success();
    }
}

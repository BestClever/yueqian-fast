package com.ityueqiangu.core.websoket;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ityueqiangu.core.constant.Constants;
import com.ityueqiangu.core.utils.spring.SpringUtils;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import com.ityueqiangu.project.system.domain.SysNotice;
import com.ityueqiangu.project.system.service.ISysNoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通知的 WebSocket服务
 */
@Controller
@ServerEndpoint(value = "/websocket/notice/{userId}")
@Slf4j
public class NoticeWebSocketServer {
    /**
     * 在线人数
     */
    private static int onlineCount = 0;

    /**
     * 在线用户的Map集合，key：用户id，value：Session对象
     */
    private static Map<String, Session> sessionMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, NoticeWebSocketServer> userMap = new ConcurrentHashMap<>();
    //会话对象
    private Session session;
    //用户标识id
    private String userId;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.userId = userId;
        this.session = session;
        if (userMap.get(userId) == null) {
            userMap.put(userId, this);
            log.info("id为{}的人上线了!!", userId);
            log.info("当前在线连接人数: {}", userMap.size());
        }
        //在webSocketMap新增上线用户
        sessionMap.put(userId, session);
        /*  System.out.println(sessionMap.get(userId));*/
        //在线人数+1
        NoticeWebSocketServer.onlineCount++;
        //System.out.println("上线人数: " + onlineCount);
        //通知除了自己之外的所有人
        //sendOnlineCount(session, "{'type':'onlineCount','onlineCount':" + WebSocketServer.onlineCount + ",userId:'" + userId + "'}");
        sendMessage(session);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {

        userMap.remove(userId);
        log.info(userId + "  用户下线啦!!");
        //下线用户id
        /*String logoutuserId = "";*/

        //从webSocketMap删除下线用户
        for (Entry<String, Session> entry : sessionMap.entrySet()) {
            if (entry.getValue() == session) {
                sessionMap.remove(entry.getKey());
                //logoutuserId = entry.getKey();
                break;
            }
        }
        //在线人数减减
        NoticeWebSocketServer.onlineCount--;
        log.info("有人下线了! 在线人数: " + onlineCount);

    }

    /**
     * 服务器接收到客户端消息时调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            //JSON字符串转 HashMap
            HashMap hashMap = new ObjectMapper().readValue(message, HashMap.class);
            //来源用户
            Map srcUser = (Map) hashMap.get("srcUser");
            //目标用户
            Map tarUser = (Map) hashMap.get("tarUser");
            //目标id
            String tarUserId = tarUser.get("userId") + "";

            //if ("allUser" .equals(tarUserId)) {// 发送给所有人
            //    for (NoticeWebSocketServer item : userMap .values()) {
            //       item.session.getAsyncRemote().sendText(message);
            //    }
            //} else{ //发给指定人
            //    if (userMap.get(tarUserId) == null) {
            //        System.out.println("该用户不在线!");
            //        //userMap.get(srcUser.get("userId")+"").session.getAsyncRemote().sendText("未找到接收人,请确认信息后重新发送");
            //    } else {
            //        userMap.get(srcUser.get("userId")+"").session.getAsyncRemote().sendText(message);
            //        userMap.get(tarUserId).session.getAsyncRemote().sendText(message);
            //    }
            //}
            userMap.get(srcUser.get("userId") + "").session.getAsyncRemote().sendText(message);
            //后期要做消息持久化

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生了一个错误!");
    }

    /**
     * 推送消息给固定的人
     *
     * @param message
     * @param to
     * @return
     * @date 2022年03月16日 0016 11:48:23
     */
    public void sendMessageTo(String message, String to) {
        // session.getBasicRemote().sendText(message);
        //session.getAsyncRemote().sendText(message);
        if (StrUtil.isNotBlank(to)) {
            for (NoticeWebSocketServer item : userMap.values()) {
                if (item.userId.equals(to)) {
                    item.session.getAsyncRemote().sendText(message);
                }
            }
        } else {
            for (NoticeWebSocketServer item : userMap.values()) {
                item.session.getAsyncRemote().sendText(message);
            }
        }
    }

    /**
     * 发送初始化信息给前端
     *
     * @param session
     * @return
     * @date 2022年03月16日 0016 22:11:23
     */
    public void sendMessage(Session session) {
        //查询 通知列表是否存在这个人的消息
        SysNotice sysNotice = new SysNotice();
        sysNotice.setOwnerId(userId);
        sysNotice.setIsCheck(Constants.NO);
        ISysNoticeService noticeService = SpringUtils.getBean("sysNoticeServiceImpl");
        List<SysNotice> sysNotices = noticeService.selectSysNoticeList(sysNotice);
        ResponseInfo success = ResponseInfo.success(sysNotice);
        success.put("count", sysNotices.size());
        String result = JSONUtil.parseObj(success).toString();
        session.getAsyncRemote().sendText(result);
    }
}

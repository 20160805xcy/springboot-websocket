package com.xcy.springbootwebsocket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

/**
 * @Author xcy
 * @Description 客户端建立连接事件监听器
 * @Date 2018/12/14 22:13
 * @Version 1.0
 */
@Component
public class ConnectEventListener implements ApplicationListener<SessionConnectEvent> {


    @Override
    public void onApplicationEvent(SessionConnectEvent sessionConnectEvent) {
        //通过StompHeaderAccessor对象获取前端传入的headers信息
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(sessionConnectEvent.getMessage());
        String userId = sha.getNativeHeader("userId").get(0);
        String userName = sha.getNativeHeader("userName").get(0);
        String sessionId = sha.getSessionId();
        System.out.println("刚创建链接时从headers监听到的用户名: "+userName + "; 用户ID: " + userId + "; sessionId: " + sessionId);

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionConnectEvent.getMessage());
        System.out.println("[ConnectEventListener 监听器事件 类型] = "+headerAccessor.getCommand().getMessageType());
    }
}

package com.xcy.springbootwebsocket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * @Author xcy
 * @Description 客户端断开连接事件监听器
 * @Date 2018/12/14 22:37
 * @Version 1.0
 */
@Component
public class DisconnectEventListener implements ApplicationListener<SessionDisconnectEvent> {
    @Override
    public void onApplicationEvent(SessionDisconnectEvent sessionConnectEvent) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionConnectEvent.getMessage());
        System.out.println("[ConnectEventListener 监听器事件 类型] = "+headerAccessor.getCommand().getMessageType());
    }
}
package com.xcy.springbootwebsocket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

/**
 * @Author xcy
 * @Description 客户端订阅事件监听器
 * @Date 2018/12/14 22:32
 * @Version 1.0
 */
@Component
public class SubscribeEventListener implements ApplicationListener<SessionSubscribeEvent> {
    @Override
    public void onApplicationEvent(SessionSubscribeEvent sessionSubscribeEvent) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());
        System.out.println("[SubscribeEventListener 监听器事件 类型] = "+headerAccessor.getCommand().getMessageType());
        System.out.println("[SubscribeEventListener 监听器事件 类型] sessionId = "+ headerAccessor.getSessionAttributes().get("sessionId"));
    }
}

package com.xcy.springbootwebsocket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

/**
 * @Author xcy
 * @Description 客户端取消订阅事件监听器
 * @Date 2018/12/14 22:41
 * @Version 1.0
 */
//@Component
public class UnsubscribeEventListener implements ApplicationListener<SessionUnsubscribeEvent> {
    @Override
    public void onApplicationEvent(SessionUnsubscribeEvent sessionSubscribeEvent) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());
        System.out.println("[SubscribeEventListener 监听器事件 类型] ="+headerAccessor.getCommand().getMessageType());
    }
}
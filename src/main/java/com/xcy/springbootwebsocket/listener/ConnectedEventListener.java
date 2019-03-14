package com.xcy.springbootwebsocket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

/**
 * @Author xcy
 * @Description ？ 监听器
 * @Date 2018/12/14 22:45
 * @Version 1.0
 */
//@Component
public class ConnectedEventListener implements ApplicationListener<SessionConnectedEvent> {
    @Override
    //TODO 看不懂
    public void onApplicationEvent(SessionConnectedEvent sessionConnectEvent) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionConnectEvent.getMessage());
        System.out.println("[ConnectedEventListener 监听器事件 类型] = "+headerAccessor.getCommand().getMessageType());
    }
}

package com.xcy.springbootwebsocket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

/**
 * @Author xcy
 * @Description 客户端订阅事件监听器
 *              前端执行stompClient.subscribe("频道",...)时触发.可以进行权限配置,如jack没有权限订阅查看内存信息服务
 * @Date 2018/12/14 22:32
 * @Version 1.0
 */
@Component
public class SubscribeEventListener implements ApplicationListener<SessionSubscribeEvent> {
    @Override
    public void onApplicationEvent(SessionSubscribeEvent sessionSubscribeEvent) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());
        //类型
        SimpMessageType messageType = headerAccessor.getCommand().getMessageType();
        //频道
        String destination = headerAccessor.getDestination();
        //sessionID
        Object sessionId = headerAccessor.getSessionAttributes().get("sessionId");

        System.out.println("[SubscribeEventListener监听器事件 类型= "+ messageType + ";频道= " + destination + ";sessionID= " + sessionId);
    }
}

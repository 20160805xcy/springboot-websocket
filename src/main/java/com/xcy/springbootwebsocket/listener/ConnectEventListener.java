package com.xcy.springbootwebsocket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import java.security.Principal;
import java.util.List;
import java.util.Map;

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
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionConnectEvent.getMessage());


        //Map<String, List<String>> nativeHeaders = headerAccessor.getNativeHeaders();
        //for (String key : nativeHeaders.keySet()){
        //    if("aa".equals(key)){
        //        System.out.println("value=" + nativeHeaders.get("aa"));
        //    }
        //}
        System.out.println("userId"+ headerAccessor.getMessage());
        System.out.println("[ConnectEventListener 监听器事件 类型] = "+headerAccessor.getCommand().getMessageType());
    }
}

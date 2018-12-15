package com.xcy.springbootwebsocket.intercepter;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author xcy
 * @Description
 * @Date 2018/12/15 17:00
 * @Version 1.0
 */
public class HttpHandShakeInterceptor implements HandshakeInterceptor{
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        System.out.println("[握手拦截器] beforeHandshake");

        if(request instanceof ServletServerHttpRequest){
            ServletServerHttpRequest httpRequest = (ServletServerHttpRequest) request;
            Cookie[] cookies = httpRequest.getServletRequest().getCookies();
            //此处可以获取到很多request对象的属性值
            HttpSession session = httpRequest.getServletRequest().getSession();

            String sessionId = session.getId();
            System.out.println("[握手拦截器] beforeHandshake sessionId = " + sessionId);
            //将sessionId放入域中，方便后操作获取
            attributes.put("sessionId",sessionId);
        }

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, @Nullable Exception exception) {
        System.out.println("[握手拦截器] afterHandshake");
        if(request instanceof ServletServerHttpRequest){
            ServletServerHttpRequest httpRequest = (ServletServerHttpRequest) request;
            HttpSession session = httpRequest.getServletRequest().getSession();

            String sessionId = session.getId();
            System.out.println("[握手拦截器] afterHandshake sessionId = " + sessionId);
        }
    }
}

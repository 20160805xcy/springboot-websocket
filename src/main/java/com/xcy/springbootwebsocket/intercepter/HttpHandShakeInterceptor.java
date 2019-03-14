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
 * @Description 功能描述:http握手拦截器,可以通过这个类的方法获取request,response
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
            System.out.println("http握手拦截从session中获取到当前登录用户为: " + session.getAttribute("userName") + "; 用户id为: " + session.getAttribute("id"));

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

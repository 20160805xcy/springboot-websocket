package com.xcy.springbootwebsocket.intercepter;

import com.xcy.springbootwebsocket.controller.UserController;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author xcy
 * @Description
 * @Date 2018/12/15 20:44
 * @Version 1.0
 */
public class SocketChannelInterceptor extends ChannelInterceptorAdapter {

    /**
     * 在消息被实际发送到频道之前调用
     * @param message
     * @param channel
     * @return
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        System.out.println("SocketChannelInterceptor --> preSend");
        return super.preSend(message, channel);
    }



    /**
     * 发送消息调用后立即调用
     * @param message
     * @param channel
     * @param sent
     */
    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        System.out.println("SocketChannelInterceptor --> postSend");
        //消息头访问器我的
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);
        if(null == headerAccessor.getCommand()) return; //避免非stomp消息类型，例如心跳检测


        String sessionId = headerAccessor.getSessionAttributes().get("sessionId").toString();
        System.out.println("SocketChannelInterceptor --> postSend sessionId = " + sessionId);

        switch (headerAccessor.getCommand()){
            case CONNECT:
                connect(sessionId);


                break;
            case DISCONNECT:
                disConnect(sessionId);

                break;
            case SUBSCRIBE:
                subscribe(sessionId);
                break;
            case UNSUBSCRIBE:
                disSubscribe(sessionId);
                break;
            default:
                break;
        }

    }

    /**
     * 在完成发送之后进行调用,不管是否有异常发生,一般用于资源清理
     * @param message
     * @param channel
     * @param sent
     * @param ex
     */
    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, @Nullable Exception ex) {
        System.out.println("SocketChannelInterceptor --> afterSendCompletion");
        super.afterSendCompletion(message,channel,sent,ex);
    }

    private void connect(String sessionId){
        System.out.println("通过SocketChannelInterceptor 拦截器,拦截到用户刚连接成功,其 sessionId = " + sessionId);
        //用户登录成功后保存用户信息到onlineUser
        UserController.onlineUser.put("sessionId",sessionId);
        System.out.println("+1后 此时在线用户有" + UserController.onlineUser.size() + "个");
    }

    private void disConnect(String sessionId){
        System.out.println("通过SocketChannelInterceptor 拦截器,拦截到用户刚断开连接,其 sessionId = " + sessionId);
        //用户退出系统后保存用户信息到onlineUser
        UserController.onlineUser.remove(sessionId);
        System.out.println("-1后 此时在线用户有" + UserController.onlineUser.size() + "个");
    }

    private void subscribe(String sessionId){
        System.out.println("通过SocketChannelInterceptor 拦截器,拦截到用户刚订阅成功,其 sessionId = " + sessionId);
    }

    private void disSubscribe(String sessionId){
        System.out.println("通过SocketChannelInterceptor 拦截器,拦截到用户刚取消订阅,其 sessionId = " + sessionId);
    }
}

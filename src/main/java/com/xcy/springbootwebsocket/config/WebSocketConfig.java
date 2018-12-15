package com.xcy.springbootwebsocket.config;

import com.xcy.springbootwebsocket.intercepter.HttpHandShakeInterceptor;
import com.xcy.springbootwebsocket.intercepter.SocketChannelInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import java.security.Principal;

/**
 * @author xcy
 * @date 2018/12/11 14:50
 * @description
 * @since V1.0.0
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * 将"/endpointWisely"路径注册为STOMP端点,这个路径与发送和接收消息的目的路径有所不同,
     * 这是一个端点,客户端在订阅或发布消息到目的地址前,要连接该端点,
     * 即用户发送请求url="/applicationName/endpointWisely"与STOMP server进行连接.之后再转发到订阅url;
     * PS:端点的作用——>客户端在订阅或发布消息到目的地址前,要连接该端点!
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/endpointWisely")
                .addInterceptors(new HttpHandShakeInterceptor()) //添加一个自己写的拦截器
                .setAllowedOrigins("*").withSockJS();
    }

    /**
     * 配置了一个简单的消息代理，如果不重载，默认情况下回自动配置一个简单的内存消息代理，用来处理以"/topic"为前缀的消息。
     * 这里重载configureMessageBroker()方法，消息代理将会处理前缀为"/topic""的消息。
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //服务端发送消息给客户端的域,多个用逗号隔开
        registry.enableSimpleBroker("/topic", "/user");
        //定义一对一推送的时候前缀
        registry.setUserDestinationPrefix("/user");
        //定义webSocket前缀
        registry.setApplicationDestinationPrefixes("/ws-push");
    }

    /**
     * 1、设置拦截器
     * 2、首次连接的时候，获取其Header信息，利用Header里面的信息进行权限认证
     * 3、通过认证的用户，使用 accessor.setUser(user); 方法，将登陆信息绑定在该 StompHeaderAccessor 上，在Controller方法上可以获取 StompHeaderAccessor 的相关信息
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptorAdapter() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                //1、判断是否首次连接
                if (StompCommand.CONNECT.equals(accessor.getCommand())){
                    //获取从客户端放入header里面的值
                    String username = accessor.getNativeHeader("userId").get(0);

                        Principal principal = new Principal() {
                            @Override
                            public String getName() {
                                return username;
                            }
                        };
                        accessor.setUser(principal);
                        return message;
                }
                //不是首次连接，已经登陆成功
                return message;
            }

        });
    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        registration.interceptors(new SocketChannelInterceptor());
    }
}

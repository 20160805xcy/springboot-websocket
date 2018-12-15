package com.xcy.springbootwebsocket.controller;

import com.xcy.springbootwebsocket.common.Constant;
import com.xcy.springbootwebsocket.pojo.Message;
import com.xcy.springbootwebsocket.pojo.Response;
import com.xcy.springbootwebsocket.service.WebSocketService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xcy
 * @date 2018/12/11 14:59
 * @description
 * @since V1.0.0
 */
@Controller
public class WsController {

    @Resource
    WebSocketService webSocketService;

    /**
     * 发送给指定用户(可以多个)
     * @param message
     */
    @MessageMapping("/sendToOne")//@MessageMapping和@RequestMapping功能类似，用于设置URL映射地址，浏览器向服务器发起请求，需要通过该地址。
    public void sendToOne(Message message) {

        List<String> users = new ArrayList();
        String[] userIdArr = message.getUserId().split(",");
        for (int i = 0; i < userIdArr.length; i++) {
            users.add(userIdArr[i]);//此值需要对应页面中订阅个人消息的userId
        }
        webSocketService.send2Users(users, new Response(message.getUserName() + "说:" + message.getMessage()));
    }


    /**
     * 广播给所有在线用户
     * @param message
     * @return
     */
    //@MessageMapping("/sendToAll")//@MessageMapping和@RequestMapping功能类似，用于设置URL映射地址，浏览器向服务器发起请求，需要通过该地址。
    //@SendTo("/topic/getResponse")//如果服务器接受到了消息，就会对订阅了@SendTo括号中的地址传送消息。
    //public Response sendToAll(Message message) {
    //
    //    return new Response("广播消息: " + message.getMessage() + ". 发送者:" + message.getUserName());
    //}

    /**
     * 广播给所有在线用户
     * @param message
     * @return
     */
    @MessageMapping("/sendToAll")//@MessageMapping和@RequestMapping功能类似，用于设置URL映射地址，浏览器向服务器发起请求，需要通过该地址。
    //@SendTo("/topic/getResponse")//如果服务器接受到了消息，就会对订阅了@SendTo括号中的地址传送消息。
    public void sendToAll(Message message) {

        //return new Response("广播消息: " + message.getMessage() + ". 发送者:" + message.getUserName());
        webSocketService.sendMsg(new Response("广播消息: " + message.getMessage() + ". 发送者:" + message.getUserName()));
    }

    /**
     * 注解 @SendTo("/topic/getResponse") 等价 为  template.convertAndSend("/topic/getResponse", msg);
     * 如果不用注解方式那么就调用SimpMessagingTemplate.convertAndSend(D destination, Object payload)来完成.
     *
     */







}

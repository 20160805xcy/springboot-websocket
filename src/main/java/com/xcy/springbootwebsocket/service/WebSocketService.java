package com.xcy.springbootwebsocket.service;

import com.xcy.springbootwebsocket.common.Constant;
import com.xcy.springbootwebsocket.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xcy
 * @date 2018/12/12 12:14
 * @description
 * @since V1.0.0
 */
@Service
public class WebSocketService {
    @Autowired
    private SimpMessagingTemplate template;

    /**
     * 广播
     * 发给所有在线用户
     *
     * @param msg
     */
    public void sendMsg(Response msg) {
        template.convertAndSend("/topic/getResponse", msg);
    }

    /**
     * 发送给指定用户
     *
     * @param users
     * @param msg
     */
    public void send2Users(List<String> users, Response msg) {
        users.forEach(userName -> {
            template.convertAndSendToUser(userName, "/msg", msg);
        });
    }
}

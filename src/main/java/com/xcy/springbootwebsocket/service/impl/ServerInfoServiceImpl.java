package com.xcy.springbootwebsocket.service.impl;

import com.xcy.springbootwebsocket.pojo.ServerInfo;
import com.xcy.springbootwebsocket.service.ServerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author xcy
 * @Description 获取服务器内存使用状态信息
 * @Date 2018/12/15 12:46
 * @Version 1.0
 */
@Service("serverInfoService")
public class ServerInfoServiceImpl implements ServerInfoService {

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public ServerInfo getServerInfoToAll() {
        int processors = Runtime.getRuntime().availableProcessors();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        ServerInfo info = new ServerInfo(processors,freeMemory,maxMemory);
        //发送给所有在线用户(如:股票信息推送给所有已订阅消息的用户)
        template.convertAndSend("/topic/getServerInfoResponse", info);
        return info;
    }

    @Override
    public ServerInfo sendServerInfoToOne(List<String> users) {
        int processors = Runtime.getRuntime().availableProcessors();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        ServerInfo info = new ServerInfo(processors,freeMemory,maxMemory);
        //发送给指定特殊用户(私有信息推送给指定特殊用户)
        users.forEach(userName -> {
            template.convertAndSendToUser(userName, "/privateMsg", info);
        });
        return info;
    }
}

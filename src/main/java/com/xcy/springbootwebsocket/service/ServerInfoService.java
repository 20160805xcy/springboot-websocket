package com.xcy.springbootwebsocket.service;

import com.xcy.springbootwebsocket.pojo.ServerInfo;

import java.util.List;

public interface ServerInfoService {
    /**
     * 获取服务器的内存性能信息(给所有已订阅消息的用户)
     * @return
     */
    ServerInfo getServerInfoToAll();

    /**
     * 获取服务器的内存性能信息(给指定用户)
     * @return
     */
    ServerInfo sendServerInfoToOne(List<String> users);
}

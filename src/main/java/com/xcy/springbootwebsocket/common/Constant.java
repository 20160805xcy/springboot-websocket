package com.xcy.springbootwebsocket.common;

/**
 * @author xcy
 * @date 2018/12/12 12:09
 * @description webSocket相关配置常量
 * @since V1.0.0
 */
public interface Constant {
    //链接地址
    String WEBSOCKETPATHPERFIX = "/ws-push";
    String WEBSOCKETPATH = "/endpointWisely";

    //消息代理路径
    String WEBSOCKETBROADCASTPATH = "/topic";

    //前端发送给服务端请求地址
    final String FORETOSERVERPATH = "/welcome";

    //服务端生产地址,客户端订阅此地址以接收服务端生产的消息
    final String PRODUCERPATH = "/topic/getResponse";

    //点对点消息推送地址前缀
    final String P2PPUSHBASEPATH = "/user";

    //点对点消息推送地址后缀,最后的地址为/user/用户识别码/msg
    final String P2PPUSHPATH = "/msg";
}

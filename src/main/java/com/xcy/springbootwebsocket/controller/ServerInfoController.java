package com.xcy.springbootwebsocket.controller;

import com.xcy.springbootwebsocket.model.UserWeb;
import com.xcy.springbootwebsocket.pojo.Response;
import com.xcy.springbootwebsocket.pojo.ServerInfo;
import com.xcy.springbootwebsocket.service.ServerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xcy
 * @Description 服务端内存信息
 * @Date 2018/12/15 12:36
 * @Version 1.0
 */
@Controller
public class ServerInfoController {
    @Autowired
    private ServerInfoService serverInfoService;

    @Scheduled(fixedRate = 10000) //10s执行一次
    public void sendServerInfo(){
        ServerInfo info = serverInfoService.getServerInfoToAll();
        System.out.println("在线用户=====>" + info);
    }

    @Scheduled(fixedRate = 3000) //3s执行一次
    public void sendServerInfoToOne(){
        List userLists = new ArrayList();
        //暂且写死,只给id为1的用户发送,真实场景可以通过规则来指定给谁发.
        userLists.add("1");
        ServerInfo info = serverInfoService.sendServerInfoToOne(userLists);
        System.out.println("指定用户=====>" + info);
    }

}

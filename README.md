# springboot-websocket
webService + SockJS + STOMP+ tkmapper + springboot 实现 广播给所有在线用户消息,发送消息给指定的用户的功能.


##一 结构
###1.pom.xml 关键添加

    <!--webSocket-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-websocket</artifactId>
    </dependency>
    
###2.说明:
数据持久化采用了tkMapper,前端页面显示采用了jsp,springBoot采用的是2版本.
特别注意的是2版本和1版本的区别,除了有些类已经过时了(但是还是可以使用的),还有个地方就是
当项目启动时候,2版本会默认跳转到index.jsp页面,但是1版本不会自动跳转.

###3.表user_web
见sql文件

##二 实现
###1. 后端WebSocketConfig.java
配置注册STOMP端点endPoint,路径为:
registry.addEndpoint("/endpointWisely"),
前端使用
new SockJS("endpointWisely")进行连接.


###2.后端WebSocketConfig.java配置消息代理,
定义服务端发送给客户端允许的域.("/topic", "/user");
定义一对一推送的前缀.("/user");
定义webSocket前缀.("/ws-push");

###3.客户端发送消息到服务端
* 指定用户(多个):stompClient.send("/ws-push/sendToOne", {}, JSON.stringify({ 'message': message,"userId":userIdStr,"userName":userName}));
* 所有用户(在线):stompClient.send("/ws-push/sendToAll", {}, JSON.stringify({ 'message': message,"userName":userName}));


###4.服务监听
* 指定用户(多个):URL映射-@MessageMapping("/sendToOne")
    调用SimpMessagingTemplate.convertAndSendToUser(userName, "/msg", msg);
    
* 所有用户(在线):URL映射-@MessageMapping("/sendToAll")
    调用SimpMessagingTemplate.convertAndSend("/topic/getResponse", msg);

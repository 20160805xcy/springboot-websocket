<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>

    <meta charset="UTF-8" />
    <title>${user.userName}-${user.id}</title>

</head>
<body onload="disconnect()">
<noscript><h2 style="color: #ff0000">貌似你的浏览器不支持websocket</h2></noscript>
<div>
    <div>
        <button id="connect" onclick="connect();">连接</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">断开连接</button>
    </div>
    选择你要给谁发消息
    <div>
        <c:forEach var="user" items="${userList}">
        <input type="checkbox" name="idSelect" value="${user.id}">${user.userName}
        </c:forEach>
    </div>

    <div id="conversationDiv">
        <label>输入要发送的消息</label><input type="text" id="message" />
        <button id="sendToOne" onclick="sendToOne();">发送给指定用户</button>
        <button id="sendToAll" onclick="sendToAll();">发送给所有用户</button>
        <input id="userId" value="${user.id}" disabled="disabled"/>
        <input id="userName" value="${user.userName}" disabled="disabled"/>
        <p id="response"></p>
        <p id="response1"></p>
    </div>
</div>
<script src="https://cdn.bootcss.com/sockjs-client/1.1.4/sockjs.min.js"></script>
<script src="https://cdn.bootcss.com/stomp.js/2.3.3/stomp.min.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
    var stompClient = null;
    //此值由服务端传递给前端,实现方式没有要求
    var userIdSelf = $("#userId").val();

    function setConnected(connected) {
        document.getElementById('connect').disabled = connected;
        document.getElementById('disconnect').disabled = !connected;
        document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
        $('#response').html();
    }

    function connect() {
        var socket = new SockJS('/endpointWisely'); //1连接SockJS的endpoint是“endpointWisely”，与后台代码中注册的endpoint要一样。
        stompClient = Stomp.over(socket);//2创建STOMP协议的webSocket客户端。
        stompClient.connect({}, function(frame) {//3连接webSocket的服务端。
            setConnected(true);
            console.log('开始进行连接Connected: ' + frame);

            //4通过stompClient.subscribe（）订阅服务器的目标是'/topic/getResponse'发送过来的地址，与@SendTo中的地址对应。
            stompClient.subscribe('/topic/getResponse', function(respnose){
                showResponse(JSON.parse(respnose.body).responseMessage);
            });

            //4通过stompClient.subscribe（）订阅服务器的目标是'/user/' + userId + '/msg'接收一对一的推送消息,其中userId由服务端传递过来,用于表示唯一的用户,通过此值将消息精确推送给一个用户
            stompClient.subscribe('/user/' + userIdSelf + '/msg', function(respnose){
                console.log(respnose);
                showResponse1(JSON.parse(respnose.body).responseMessage);
            });
        });
    }


    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    }

    function sendToOne() {
        var message = $('#message').val();
        var userId = $('input[name="idSelect"]:checked ').val();

        var userIdStr = "";
        $.each($('input:checkbox:checked'),function(){
            console.log("你选了："+$('input[type=checkbox]:checked').length+"个，其中有："+$(this).val());
            userIdStr += ($(this).val()) +",";
        });

        var userName = $('#userName').val();
        //通过stompClient.send（）向地址为"/welcome"的服务器地址发起请求，与@MessageMapping里的地址对应。因为我们配置了registry.setApplicationDestinationPrefixes(Constant.WEBSOCKETPATHPERFIX);所以需要增加前缀/ws-push/
        stompClient.send("/ws-push/sendToOne", {}, JSON.stringify({ 'message': message,"userId":userIdStr,"userName":userName}));
    }

    function sendToAll() {
        var userName = $('#userName').val();
        var message = $('#message').val();
        //通过stompClient.send（）向地址为"/welcome"的服务器地址发起请求，与@MessageMapping里的地址对应。因为我们配置了registry.setApplicationDestinationPrefixes(Constant.WEBSOCKETPATHPERFIX);所以需要增加前缀/ws-push/
        stompClient.send("/ws-push/sendToAll", {}, JSON.stringify({ 'message': message,"userName":userName}));
    }


    function showResponse(message) {
        var response = $("#response");
        response.append(message+"<br />");
    }

    function showResponse1(message) {
        var response = $("#response1");
        response.append(message+"<br />");
    }
</script>
</body>
</html>
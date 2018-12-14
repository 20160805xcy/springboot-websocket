<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>

    <meta charset="UTF-8" />
    <title>login</title>

</head>
<body>
<div>
    <form action="/user/login" method="get">
        用户名:
        <input name="userName" id="userName"/>
        密码:
        <input name="passWord" id="passWord"/>
        <input id="btn" type="submit" value="登录"/>
    </form>
</div>
<script src="https://cdn.bootcss.com/sockjs-client/1.1.4/sockjs.min.js"></script>
<script src="https://cdn.bootcss.com/stomp.js/2.3.3/stomp.min.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">

</script>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: lhl
  Date: 2019/10/11
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
client01登录成功后的主页
<a href="http://localhost:8080/client02/toIndex">前往client02主页</a>
<a href="${pageContext.request.contextPath}/loginOut">退出登录</a>
</body>
</html>

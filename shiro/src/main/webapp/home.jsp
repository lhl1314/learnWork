<%--
  Created by IntelliJ IDEA.
  User: lhl
  Date: 2019/12/18
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
登录成功后的主页<br>
欢迎${sessionScope.username}
<a href="${pageContext.request.contextPath}/logout">注销登录</a>
</body>
</html>

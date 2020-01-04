<%--
  Created by IntelliJ IDEA.
  User: lhl
  Date: 2019/12/18
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
这里是主页
${sessionScope.user.username}
<a href="${pageContext.request.contextPath}/logout">退出登录</a>
</body>
</html>

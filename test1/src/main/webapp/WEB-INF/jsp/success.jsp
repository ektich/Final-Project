<%--
  Created by IntelliJ IDEA.
  User: 13979
  Date: 2018/10/19
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType = "text/html; charset = UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <p>welcome:${message}</p>
    <p>${sessionScope.user.id}</p>
    <a href="${pageContext.request.contextPath}/logout" style="text-decoration: none">logout</a>
    <script>
        //取session值
        window.onload = function() {
            console.log("${sessionScope.user.identity}");
            window.location.href = "http://localhost:8080";
        }
    </script>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页面</title>
</head>
<body>

    <ol>
        <c:choose>
            <c:when test="${user.id != null}">
                <li>id:${user.id}</li>
                <li>name:${user.name}</li>
                <li>age:${user.age}</li>
                <li>username:${user.username}</li>
                <li>password:${user.password}</li>
                <li>identity:${user.identity}</li>
            </c:when>
            <c:otherwise>
                <p>该用户不存在!<p>
            </c:otherwise>
        </c:choose>
    </ol>

</body>
</html>
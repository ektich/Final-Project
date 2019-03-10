<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <form action="/addProject" method="post">
            <input type="text" id="title" name="title">
            <input type="text" id="language" name="language">
            <input type="text" id="supervisor" name="supervisor">
            <input type="text" id="category" name="category">
            <textarea name="reference" id="reference" cols="10" rows="10"/>
            <textarea name="description" id="description" cols="30" rows="10"/>
        </form>
    </div>
</body>
</html>

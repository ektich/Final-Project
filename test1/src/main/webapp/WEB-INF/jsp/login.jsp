<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div align="center" style="margin-top: auto; margin-bottom: auto;">
        <p>login</p>
        <input id="username" type="text" name="username" placeholder="username"><br>
        <input id="password" type="password" name="password" placeholder="password"><br>
        <button type="submit" onclick="result()">submit</button>
    </div>
    <script type="text/javascript">
        function result () {
            let data = {
                username : document.getElementById('username').value,
                password : document.getElementById('password').value
            };
            loginCheck(data).then(result => {
                if(result.username !== undefined) {
                    console.log(result.username);
                    sessionStorage.setItem("username", result.username);
                    window.location.href = "index.jsp";
                } else {
                    alert(result.response);
                }
            });
        }

        function loginCheck (data) {
            let url = '/login';
            return new Promise((resolve, reject) => {
                let req = new XMLHttpRequest();
                //req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                req.responseType = 'json';
                req.open('POST', url, true);
                req.setRequestHeader('Content-Type', 'application/json');
                req.withCredentials = true;
                req.onload = () => {
                    if(req.status === 200) {
                        resolve(req.response);
                    } else {
                        reject(Error(req.statusText));
                    }
                };
                req.onerror = () => {
                    reject(Error('Network Error'));
                };
                req.send(JSON.stringify(data));
            });
        }
    </script>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: 13979
  Date: 2018/11/10
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../static/css/test.css">
</head>
<body>
    <div id="container" class="container">
        <div id="head" class="head">
            <h2 align="center">Index</h2>
            <div class="navigation-bar">
                <div>
                    <button onclick="testTimeOut()">Test</button>
                </div>
                <div class="navigation-bar-left" align="left" style="float: left">
                    <a href="https://maynoothuniversity.ie">home</a>
                </div>
                <div class="navigation-bar-right" align="right">
                    <input type="button" value="login" id="login-btn" onclick="loginButton()">
                    <a href="login">login</a>
                </div>
            </div>
            <div class="details-project-body" align="center" style="height: 300px">
                ${result.title}<br>
            </div>
            <div class="details-project-body" align="left">
                Description:<br>
                ${result.description}<br>
            </div>
            <div align="center">
                <input type="button" value="Submit" id="submit-interest" class="submit-interest" >
            </div>
        </div>
    </div>
</body>
<script>
    function test10 () {
        let url = '/testTimeOut';
        return new Promise((resolve, reject) => {
            let req = new XMLHttpRequest();
            req.responseType = 'json';
            req.open('GET', url, true);
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
            req.send();
        });
    }

    function testTimeOut() {
        test10().then(result => {
            console.log(result);
        });
    }
</script>
</html>

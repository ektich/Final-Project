<%--
  Created by IntelliJ IDEA.
  User: 13979
  Date: 2018/10/19
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <p>ajax查询用户</p>
        <div id="test">
            <input type="text" id="user_id" name="user_id" placeholder="输入id">
            <input type="button" value="查询" onclick="requestSelect()">
            <p id="query-box">查询状态:<span id="query-box-span"></span></p>
        </div>
        <div>
            <div align="right" style="float: left">
                id:<br>name:<br>age:<br>username:<br>password:<br>identity:<br>
            </div>
            <div id="showTable">
                <li id="query-user-id"></li>
                <li id="query-user-name"></li>
                <li id="query-user-age"></li>
                <li id="query-user-username"></li>
                <li id="query-user-password"></li>
                <li id="query-user-identity"></li>
            </div>
        </div>
    </div>
    <br>
    <div>
        <form action="/findUser" method="post">
            <table>
                <tr>
                    <p>没有ajax查询用户</p>
                    <td>id</td>
                    <td><input type="text" id="id" name="id"></td>
                    <td><button type="submit">提交</button></td>
                </tr>
            </table>
        </form>
    </div>

    <br><br><br>

    <div>
        <p>新增用户</p>
        <div align="right" style="float: left">
            name:<br>age:<br>username:<br>password:<br>
        </div>
        <div>
            <input type="text" name="insert-user-name"><br>
            <input type="text" name="insert-user-age"><br>
            <input type="text" name="insert-user-username"><br>
            <input type="text" name="insert-user-password"><br>
            <select name="insert-user-identity" id="insert-user-identity">
                <option value="student">Student</option>
                <option value="lecture">Lecture</option>
            </select>
            <input type="button" value="提交" onclick="insertUser()">
        </div>
    </div>

    <br><br><br>

    <div>
        <p>删除用户</p>
        <div>

        </div>
        <input type="text" name="delete-user">
        <input type="button" value="提交" onclick="deleteUser()">
    </div>

    <br><br><br>

    <div>
        <p>模糊查询/还不支持</p>
        <div align="right" style="float: left">
            <br>title:<br>supervisor:<br>language:<br>category:<br>
        </div>
        <div>
            <input type="button" value="query" onclick="queryProject()"><br>
            <input type="text" id="query-project-title" name="query-project-title"><br>
            <input type="text" id="query-project-supervisor" name="query-project-supervisor"><br>
            <input type="text" id="query-project-language" name="query-project-language"><br>
            <input type="text" id="query-project-category" name="query-project-category">
        </div>
    </div>

    <br><br><br>

    <div>
        <p>新增项目</p>
        <form action="/insertProject" method="post">
            <div align="right" style="float: left">
                <br>title:<br>language:<br>supervisor:<br>category:<br>reference:<br>description:<br>
            </div>
            <div>
                <input type="button" value="submit" onclick="insertProject()"><br>
                <input type="text" id="insert-project-title" name="insert-project-title"><br>
                <input type="text" id="insert-project-language" name="insert-project-language"><br>
                <input type="text" id="insert-project-supervisor" name="insert-project-supervisor"><br>
                <input type="text" id="insert-project-category" name="insert-project-category"><br>
                <input type="text" id="insert-project-reference" name="insert-project-reference"><br>
                <textarea name="insert-project-description" id="insert-project-description" cols="100" rows="10"></textarea>
            </div>
        </form>
    </div>

    <br><br><br>

</body>
</html>

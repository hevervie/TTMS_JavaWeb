<%--
  Created by IntelliJ IDEA.
  User: zhoupan
  Date: 17-5-30
  Time: 下午9:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="zh">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>登录</title>
  <link rel="stylesheet" type="text/css" href="/css/styles.css">
</head>
<body>
<div class="wrapper">
  <div class="container">
    <h1>Welcome</h1>
    <form class="form" action="/login" method="post">
      <input type="text" name="username" placeholder="Username">
      <input type="password" name="password" placeholder="Password">
      <button type="submit" id="login-button">Login</button>
        <div style="color: red">
            <br/>
            <br/>
            <br/>
            <%
                if(request.getAttribute("reason") != null){
                    out.println(request.getAttribute("reason"));
                }
            %>
        </div>
    </form>
  </div>

  <ul class="bg-bubbles">
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
  </ul>
</div>

<script src="/js/jquery-2.1.1.min.js" type="text/javascript"></script>
</body>
</html>

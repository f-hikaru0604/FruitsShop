<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
<body class=c1>
<span class="label label-danger">${emsg}</span>
<div class=c36>
    <form action="login-servlet" method="POST">
        <P class=c37>loginform</P>
        <div><input type="text" name="username" placeholder="username"></div><br>
        <div><input type="password" name="password" placeholder="password"></div><br>
        <input type="submit" value="ログイン">
    </form>
</div>
</body>
</html>
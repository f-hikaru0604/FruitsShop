<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理メニュー</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
<body class=c1>
<jsp:include page="header.jsp"></jsp:include>
<p>商品管理メニュー</p>
<div>
    <form action="productManagement-servlet" method="POST">
        <input type="submit" value="商品一覧" class=c8>
    </form>
</div>
<div>
    <form action="adRegistration.jsp">
        <input type="submit" value="商品情報登録" class=c8>
    </form>
</div>
<div>
    <form action="adEdit.jsp">
        <input type="submit" value="商品情報変更" class=c8>
    </form>
</div>
<div>
    <form action="deleteSupport-servlet" method="POST">
        <input type="submit" value="商品情報削除" class=c8>
    </form>
</div>
<form action="logout-servlet" method="POST">
    <div class=c3>
        <input type="submit" value="ログアウト">
    </div>
</form>
</body>
</html>
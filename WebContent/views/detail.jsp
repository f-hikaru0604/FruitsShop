<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="beans.ItemBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
<body class=c35>
<jsp:include page="header.jsp"></jsp:include>
<div class=c37>
    <h1>商品詳細</h1>
    <form action="shopping-servlet" method="POST">
        <input type="submit" value="商品一覧">
    </form>
</div>
<% ItemBean item = (ItemBean)session.getAttribute("itemBean"); %>
<form action="cart-servlet" method="POST">
        <div class=c22><img src="<%= item.getPng_path() %>" width="400" height="300" align="middle"></div>
        <h3><%= item.getItem_name()%></h3><br>
        <div><%= item.getItem_info() %></div>
        <div>価格:&#165;<%= item.getItem_price()%></div>
        <% if (item.getItem_count() != 0) { %>
            <select name="count">
            <% for (int i = 0; i <= item.getItem_count(); i++) {%>
                <option value="<%= i%>"><%= i%></option>
            <% }%>
            </select>
        <input type="hidden" name="item_no" value="<%= item.getItem_no()%>">
        <input type="submit" value="カートに入れる">
        <% } else { %>
                    <dd>売り切れ！</dd>
        <% } %>
</form>
<form action="logout-servlet" method="POST">
    <div class=c3><input type="submit" value="ログアウト"></div>
</form>
</body>
</html>
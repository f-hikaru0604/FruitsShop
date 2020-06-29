<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,java.util.ArrayList,beans.ItemBean,beans.CartBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<script>
function cartDelete(){
	document.getElementById('form').action = 'cartDelete-servlet';
	}
</script>
</head>
<body class=c35>
<jsp:include page="header.jsp"></jsp:include>
<div class=c37>
    <h1>🛒カート</h1>
    <form action="shopping-servlet" method="POST">
        <input type="submit" value="商品一覧">
    </form>
</div>
<%
    List<CartBean> cartList = (List<CartBean>) session.getAttribute("cartList");
    if (cartList == null) {
%>
    カートに商品がありません
<%} else { %>
<form id='form' name='form' action="buy-servlet" method="POST">
    <div class=c16>
        <input type="submit" value="レジに進む">
    </div>
<%
    int i = 0;
    int subtotal = 0;
    int total = 0;
    for (CartBean cart : cartList) {
        ItemBean item = (ItemBean)cart.getItemBean();
%>
    <div class=c23><img src="<%= item.getPng_path() %>" width="200" height="150" align="middle"></div>
        <h3><%= item.getItem_name()%></h3><br>
        <div>価格:&#165;<%= item.getItem_price()%></div>
        <div>
            <select name="count">
            <% for (int j = 0; j <= item.getItem_count(); j++) {%>
                <option value="<%= j%>"
                <% if (j == cart.getCount()) { %>
                   selected
                <%} %>><%= j%></option>
            <% }%>
            </select>
        </div>
        <div>
            <input type="hidden" name="cart_no" value="<%=i %>">
            <input type="submit" value="削除" onclick="cartDelete();">
            <span class=c24>
                <% subtotal = item.getItem_price() * cart.getCount();  %>
                小計:<%=subtotal %>円
            </span>
       </div>
       <hr style="border:none;border-top:dashed 1px;height:1px;width:600px;">
       <% total += subtotal; %>
       <%i++;
        } %>
    <div class=c25>
        <% int tax = total / 10; %>
        消費税:<%=tax %>円<br>
        <% int total1 = total + tax; %>
        合計:<%=total1 %>円
    </div>
</form>
<% session.setAttribute("cartList",cartList);
    } %>
<form action="logout-servlet" method="POST">
    <div class=c3><input type="submit" value="ログアウト"></div>
</form>
</body>
</html>
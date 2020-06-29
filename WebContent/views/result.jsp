<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,beans.ItemBean,beans.CartBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入完了</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
<body class=c35>
<jsp:include page="header.jsp"></jsp:include>
<div class=c37>
    <h1>購入</h1>
    <span class="label label-danger">${msg}</span>
    <form action="shopping-servlet" method="POST">
        <input type="submit" value="商品一覧">
    </form>
    <div style="text-align:center;margin-bottom:10px;">
        以下の商品の購入が完了しました
    </div>
</div>
<%
    List<CartBean> buyList = (List<CartBean>) session.getAttribute("cartList");
    int[] count = (int[]) session.getAttribute("count");

               int subtotal = 0;
               int total = 0;
               int i = 0;
               for (CartBean buy : buyList) {
            	    ItemBean item = (ItemBean)buy.getItemBean();
%>
<div class=c23>
    <img src="<%= item.getPng_path() %>" width="200" height="150" align="middle">
</div>
<h3><%= item.getItem_name()%></h3><br>
<div>価格:&#165;<%= item.getItem_price()%></div>
<div><%= count[i]%>個</div>
    <% subtotal = item.getItem_price() * count[i];%>
<span class=c26>小計:<%=subtotal %>円</span>
<hr style="border:none;border-top:dashed 1px;height:1px;width:600px;">
<%
    total += subtotal;
    i++;
}
%>
<div class=c25>
    <% int tax = total / 10; %>
       消費税:<%=tax %>円<br>
    <% int total1 = total + tax; %>
       合計:<%=total1 %>円
</div>
<%
    String customer_name = (String) session.getAttribute("customer_name");
    String customer_name_kana = (String) session.getAttribute("customer_name_kana");
    String customer_tel = (String) session.getAttribute("customer_tel");
    String customer_address = (String) session.getAttribute("customer_address");
    String pay_method = (String) session.getAttribute("pay_method");
%>
<div class=c27>
    <div class=34>
        お客様情報
    </div>
    <div>
        名前:<%=customer_name %>
    </div>
    <div>
        名前（かな）:<%=customer_name_kana %>
    </div>
    <div>
        電話番号:<%=customer_tel %>
    </div>
    <div>
        住所:<%=customer_address %>
    </div>
    <div>
        お支払方法:<%=pay_method %>
    </div>
</div>
<% session.removeAttribute("cartList"); %>
</body>
</html>
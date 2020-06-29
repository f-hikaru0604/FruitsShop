<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,beans.ItemBean,beans.CartBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>レジ</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<script type="text/javascript">
<!--
function check() {
var flag = 0;
if (document.form1.customer_name.value == "") {
	flag = 1;
}else if (document.form1.customer_name_kana.value == "") {
	flag = 1;
}else if (document.form1.customer_tel.value == "") {
	flag = 1;
}else if (document.form1.customer_address.value == "") {
	flag = 1;
}else if (document.form1.pay_method.value == "") {
	flag = 1;
}
if (flag){
	window.alert ('入力されていない項目があります');
	return false;
}else {
	return true;
}
}
//-->
</script>
</head>
<body class=c35>
<jsp:include page="header.jsp"></jsp:include>
<div class=c37>
    <h1>レジ</h1>
     <form action="shopping-servlet" method="POST">
         <input type="submit" value="商品一覧">
     </form>
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
                <div class=c23><img src="<%= item.getPng_path() %>" width="200" height="150" align="middle"></div>
                <h3><%= item.getItem_name()%></h3><br>
                <div>価格:&#165;<%= item.getItem_price()%></div>
                <div><%= count[i]%>個</div>
                <% subtotal = item.getItem_price() * count[i];%>
                <span class=c26>小計:<%=subtotal %>円</span>
                <hr style="border:none;border-top:dashed 1px;height:1px;width:600px;">
                <% total += subtotal;
                i++;
               }%>
<div class=c25>
    <% int tax = total / 10; %>
    消費税:<%=tax %>円<br>
    <% int total1 = total + tax; %>
    合計:<%=total1 %>円
</div>
<div class=c27>
    <form action="buyResult-servlet" method="POST" name="form1" onSubmit="return check()">
    <div>
        お客様情報
    </div>
    <div>
        名前:
        <input type="text" name="customer_name">
    </div>
    <div class=c30>
        名前（かな）:
        <input type="text" name="customer_name_kana">
    </div>
    <div class=c31>
        電話番号:
        <input type="text" name="customer_tel">
    </div>
    <div class=c32>
        住所:
        <textarea name="customer_address" cols="20" rows="2"></textarea>
    </div>
    <div class=c33>
       お支払方法:
        <select name="pay_method">
            <option value=""></option>
            <option value="クレジットカード">クレジットカード</option>
            <option value="銀行振込">銀行振込</option>
            <option value="代引き">代引き</option>
        </select>
    </div>
    <div>
        <input type="submit" value="購入">
    </div>
    </form>
</div>
<form action="logout-servlet" method="POST">
    <div class=c3><input type="submit" value="ログアウト"></div>
</form>
</body>
</html>
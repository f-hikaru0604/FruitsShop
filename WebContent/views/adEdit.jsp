<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="beans.ItemBean,java.util.List"%>
    <jsp:useBean id="itemBean" class="beans.ItemBean" scope="session" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品情報変更</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
<body class=c1>
<jsp:include page="header.jsp"></jsp:include>
<p>商品情報変更</p>
<script>
	function edit(){
		if(confirm("本当に変更しますか？")){
			document.getElementById("fm").submit();
		}else{
			alert("キャンセルしました。");
		}
	}
</script>
<span class="label label-danger">${msg}</span>
<div class=c9>
    <form action="menu-servlet" method="POST">
        <input type="submit" value="メニュー" style="width:70px;height:25px;">
    </form>
</div>
<form action="display-servlet" method="POST">
    <p><span class=c7>商品番号</span>
    <input type="text" name="item_no" width="20">
    <input type="submit" value="表示"></p>
</form>
<%  List<String> list = (List<String>)session.getAttribute("list");
    if (list != null) {
            int count = Integer.parseInt(list.get(2));
%>

<form action="edit-servlet" method="POST" id="fm">
    <div class=c4>商品名:
        <input type="text" name="item_name" size="" placeholder="item_name" value="<%=list.get(0)%>"></div>
    <div class=c5>色:
        <select name="item_color" style="width:165px">
            <option value=""
            <%if (list.get(1) == null){ %>
                selected
            <%} %>></option>
            <option value="赤"
            <%if ("赤".equals(list.get(1))){ %>
                selected
            <%} %>>赤</option>
            <option value="黄"
            <%if ("黄".equals(list.get(1))){ %>
                selected
            <%} %>>黄</option>
            <option value="緑"
            <%if ("緑".equals(list.get(1))){ %>
                selected
            <%} %>>緑</option>
            <option value="青"
            <%if ("青".equals(list.get(1))){ %>
                selected
            <%} %>>青</option>
            <option value="紫"
            <%if ("紫".equals(list.get(1))){ %>
                selected
            <%} %>>紫</option>
            <option value="茶"
            <%if ("茶".equals(list.get(1))){ %>
                selected
            <%} %>>茶</option>
            <option value="白黒"
            <%if ("白黒".equals(list.get(1))){ %>
                selected
            <%} %>>白黒</option>
        </select></div>
    <div>個数:
        <select name="item_count" style="width:165px;">
            <option value="0"></option>
            <%for (int i = 1; i <= 1000; i++) { %>
            <option value="<%=i %>"
                <%if (i == count){ %>
                selected
                <%} %>><%=i %></option>
            <%} %>
        </select></div>
    <div>価格:
        <input type="text" name="item_price" size="20" placeholder="item_price" value="<%=list.get(3)%>"></div>
    <div class=c21>商品情報:
        <input type="text" name="item_info" size="20"placeholder="item_info" value="<%=list.get(4)%>"></div>
    <div class=c6>画像データ:
        <input type="text" name="png_path" size="20" placeholder="png_path" value="<%=list.get(5)%>"></div>
    <div>
        <input type="hidden" name="item_no" value="<%=list.get(6)%>">
        <input type="submit" value="変更" onclick="edit();">
    </div>
</form>
<%}else { %>
<%

    String item_name = itemBean.getItem_name();
    String item_color = itemBean.getItem_color();
    int item_count = itemBean.getItem_count();
    int item_price = itemBean.getItem_price();
    String item_info = itemBean.getItem_info();
    String png_path = itemBean.getPng_path();
%>
<form action="edit-servlet" method="POST" id="fm">
    <div class=c4>商品名:
        <input type="text" name="item_name"
        <% if (item_name == null) {%>
               value=""
        <%} else {%>
               value="<%=item_name %>"
        <%} %>size="20" placeholder="item_name"></div>
    <div class=c5>色:
        <select name="item_color" style="width:165px">
            <option value=""
            <% if (item_color == null) { %>
                selected
            <%} %>></option>
            <option value="赤"
            <% if ("赤".equals(item_color)) {%>
                selected
            <%} %>>赤</option>
            <option value="黄"
            <% if ("黄".equals(item_color)) { %>
                selected
            <%} %>>黄</option>
            <option value="緑"
            <% if ("緑".equals(item_color)) { %>
                selected
            <%} %>>緑</option>
            <option value="青"
            <% if ("青".equals(item_color)) { %>
                selected
            <%} %>>青</option>
            <option value="紫"
            <% if ("紫".equals(item_color)) {%>
                selected
            <%} %>>紫</option>
            <option value="茶"
            <% if ("茶".equals(item_color)) {%>
                selected
            <%} %>>茶</option>
            <option value="白黒"
            <% if ("白黒".equals(item_color)) {%>
                selected
            <%} %>>白黒</option>
        </select></div>
    <div>個数:
        <select name="item_count" style="width:165px;">
            <option value="0"></option>
            <%for (int i = 1; i <= 1000; i++) { %>
            <option value="<%=i %>"
            <%if (i == item_count){%>
	            selected
	        <%}%>><%=i %></option>
            <%} %>
        </select></div>
    <div>価格:
        <input type="text" name="item_price" size="20"
        <% if (item_price == 0) {%>
            value=""
        <%} else {%>
            value="<%=item_price %>"
        <%} %>></div>
    <div class=c21>商品情報:
        <input type="text" name="item_info" size="20"
        <% if (item_info == null || item_info.equals("")) {%>
            value=""
        <%} else {%>
            value="<%=item_info %>"
        <%} %>></div>
    <div class=c6>画像データ:
        <input type="text" name="png_path" size="20"
        <% if (png_path == null) {%>
            value=""
        <%} else {%>
            value="<%=png_path %>"
        <%} %>></div>
    <div>
        <input type="hidden" name="item_no" value="<%=itemBean.getItem_no()%>">
        <input type="submit" value="変更" onclick="edit();">
    </div>
</form>
<%} %>
<% session.removeAttribute("itemBean"); %>
<form action="logout-servlet" method="POST">
    <div class=c3>
        <input type="submit" value="ログアウト">
    </div>
</form>
</body>
</html>
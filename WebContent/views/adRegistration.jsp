<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品情報登録</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
<body class=c1>
<jsp:include page="header.jsp"></jsp:include>
<p>商品情報登録</p>
<script>
	function registration(){
		if(confirm("本当に登録しますか？")){
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
<%  List<String> list = (List<String>)session.getAttribute("list");
    if (list != null) {
            int count = Integer.parseInt(list.get(2));
%>
<form action="registration-servlet" method="POST" id="fm">
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
        <input type="text" name="item_price" size="20" placeholder="item_price"
        <%if (list.get(3) != null) { %>
             value="<%=list.get(3)%>"
        <%} %>></div>
    <div class=c21>商品情報:
        <input type="text" name="item_info" size="20"placeholder="item_info"
        <%if (list.get(4) != null) { %>
            value="<%=list.get(4)%>"
        <%} %>></div>
    <div class=c6>画像データ:
        <input type="text" name="png_path" size="20" placeholder="png_path"
        <%if (list.get(5) != null) { %>
            value="<%=list.get(5)%>"
        <%} %>></div>
    <div><input type="submit" value="登録" onclick="registration();"></div>
</form>
<%}else { %>
<form action="registration-servlet" method="POST" id="fm">
    <div class=c4>商品名:
        <input type="text" name="item_name" size="" placeholder="item_name"></div>
    <div class=c5>色:
        <select name="item_color" style="width:165px">
            <option value=""></option>
            <option value="赤">赤</option>
            <option value="黄">黄</option>
            <option value="緑">緑</option>
            <option value="青">青</option>
            <option value="紫">紫</option>
            <option value="茶">茶</option>
            <option value="白黒">白黒</option>
        </select></div>
    <div>個数:
        <select name="item_count" style="width:165px;">
            <option value="0"></option>
            <%for (int i = 1; i <= 100; i++) { %>
            <option value="<%=i %>"><%=i %></option>
            <%} %>
        </select></div>
    <div>価格:
        <input type="text" name="item_price" size="20" placeholder="item_price"></div>
    <div class=c21>商品情報:
        <input type="text" name="item_info" size="20"placeholder="item_info"></div>
    <div class=c6>画像データ:
        <input type="text" name="png_path" size="20" placeholder="png_path"></div>
    <div><input type="submit" value="登録" onclick="registration();"></div>
</form>
<%} %>
<form action="logout-servlet" method="POST">
<div class=c3><input type="submit" value="ログアウト"></div></form>
</body>
</html>
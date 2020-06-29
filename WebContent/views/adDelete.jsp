<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, beans.ItemBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品情報削除</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
<body class=c1>
<jsp:include page="header.jsp"></jsp:include>
<p>商品情報削除</p>
<script>
	function sakujo(){
		if(confirm("本当に削除しますか？")){
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
<div><button onclick="sakujo();" class=c10>削除</button></div><br>
	<form id="fm" action="delete-servlet" method="POST">
<%
    List<ItemBean> itemList = (List) session.getAttribute("itemList");
    if (itemList == null){
%>
    商品がありません
<%} else { %>
<table border="1" style="width:800px;">
    <tr>
        <th></th>
        <th>画像</th>
        <th>No</th>
        <th>商品名</th>
        <th>色</th>
        <th>個数</th>
        <th>価格（円）</th>
        <th>商品情報</th>
    </tr>
    <%
        for (ItemBean item : itemList) {
    %>
    <tr>
        <td><input type="checkbox" name="item_no" value="<%=item.getItem_no() %>"></td>
        <td><img src="<%=item.getPng_path()%>" width="100" height="80"></td>
        <td><%=item.getItem_no()%></td>
        <td><%=item.getItem_name()%></td>
        <td><%=item.getItem_color()%></td>
        <td><%=item.getItem_count()%></td>
        <td><%=item.getItem_price()%></td>
        <td><%=item.getItem_info()%></tr>

    <%} %>
</table>
<%} %>
</form>
<form action="logout-servlet" method="POST">
    <div class=c3>
        <input type="submit" value="ログアウト">
    </div>
</form>
</body>
</html>
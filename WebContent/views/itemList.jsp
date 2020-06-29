<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,beans.ItemBean"%>
<!DOCTYPE html>
<html>
<head>
<title>商品一覧</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
$(function(){
	//ソート順変更
	$('.btn1').click(function(){

		//li要素を取得して並び替え
		var $elements = $('ul#myList > li').sort(function(a , b){

			//★カスタムデータ属性の値を参照
			var aa = $(a).data('num');
			var bb = $(b).data('num');
			//昇順ソート。不等号を逆にすることで降順にできる。
			if(aa > bb){
				return 1;
			}else if( aa < bb){
				return -1;
			}
			return 0;
		});

		//リスト（ulの中のli）を全て削除
		$('ul#myList').empty();

		//並び替えた順にliを追加する
		$elements.each(function(){
			$('ul#myList').append($(this));

 		});
	});
});
</script>
<script type="text/javascript">
$(function(){
	//ソート順変更
	$('.btn2').click(function(){

		//li要素を取得して並び替え
		var $elements = $('ul#myList > li').sort(function(a , b){

			//★カスタムデータ属性の値を参照
			var aa = $(a).data('num');
			var bb = $(b).data('num');
			//昇順ソート。不等号を逆にすることで降順にできる。
			if(aa < bb){
				return 1;
			}else if( aa > bb){
				return -1;
			}
			return 0;
		});

		//リスト（ulの中のli）を全て削除
		$('ul#myList').empty();

		//並び替えた順にliを追加する
		$elements.each(function(){
			$('ul#myList').append($(this));

 		});
	});
});
</script>
</head>
<body class=c35>
<span class="label label-danger">${msg}</span>
<jsp:include page="header.jsp"></jsp:include>
<div class=c37>
    <h1>商品一覧</h1>
    <div class=c15>
        <form action="cart-servlet" method="POST">
            <input type="submit" value="🛒カート">
        </form>
    </div>
    <div class=c14>
        <form action="search-servlet" method="POST">
            <select name="category">
                <option value="item_name">商品名</option>
                <option value="item_color">色</option>
                <option value="item_price1">価格（以下）</option>
                <option value="item_price2">価格（以上）</option>
            </select>
            <input type="text" name="conditions" size="30" placeholder="search>>">
            <input type="submit" value="検索">
        </form>
    </div>
    <div>
        <input type="button" class="btn1" value="価格の安い順">
        <input type="button" class="btn2" value="価格の高い順">
    </div>
</div>
<div>
    <ul class=c19 id="myList">
 <%
    List<ItemBean> itemList = (List) session.getAttribute("itemList");
    int i = 1 ;
        if (itemList == null) {
%>
        	該当する商品がありません
<%
    } else {
        for (ItemBean item : itemList) {
%>
        <li class=c20 data-num="<%= item.getItem_price()%>">
            <a href="/FruitsShop/views/detail-servlet?item_no=<%=item.getItem_no()%>"><img src="<%= item.getPng_path() %>" width="100" height="80">
            <dt><%= item.getItem_name()%></dt>
            <dd>&#165;<%= item.getItem_price()%></dd></a>
        </li>
<%
        }
    }
%>
    </ul>
</div>
<form action="logout-servlet" method="POST">
    <div class=c3><input type="submit" value="ログアウト"></div>
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="beans.LoginBean"%>
<header>
    <% String username = (String) session.getAttribute("username"); %>
    <p>
        ようこそ「<%= username %>」さん！
    </p>
</header>
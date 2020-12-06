<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登陆成功</title>
</head>
<body>
	<c:if test="${user != null}">
		<h1>${msg }</h1>
		<h3>欢迎${username }登陆！</h3>
	</c:if>
	<c:if test="${loginTime != null}">
		<h3>当前时间：${loginTime }</h3>
	</c:if>
	<a href="logout">登出</a>
</body>
</html>
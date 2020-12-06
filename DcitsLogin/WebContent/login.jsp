<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>网上银行系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" media="screen" href="css/login.css">
<link rel="stylesheet" type="text/css" href="css/reset.css" />
<link rel="stylesheet" href="css/animate.min.css" />
</head>
<body>
	<div id="particles-js">
		<div id="base" class="login">
			<form name="myForm" action="login" method="post">
				<div class="login-top">
					欢迎登录网上银行系统
					<c:if test="${errMsg != null }">
						<span style="color: red;font-size: 12px;">${errMsg }</span>
					</c:if>
				</div>
				<div class="login-center clearfix">
					<div class="login-center-img">
						<img src="images/name.png" />
					</div>
					<div class="login-center-input">
						<input type="text" placeholder="请输入您的用户名"
							onfocus="this.placeholder=''"
							onblur="this.placeholder='请输入您的用户名'" name="username" />
						<div class="login-center-input-text">用户名</div>
					</div>
				</div>
				<div class="login-center clearfix">
					<div class="login-center-img">
						<img src="images/password.png" />
					</div>
					<div class="login-center-input">
						<input type="password" placeholder="请输入您的密码"
							onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的密码'"
							name="password" />
						<div class="login-center-input-text">密码</div>
					</div>
				</div>
				<div class="login-center clearfix" style="padding: 0 60px;">
					<button class="login-button" type="submit">登录</button>
					<button class="reset-button" type="reset">重置</button>
				</div>
			</form>
		</div>
		<div class="sk-rotating-plane"></div>
	</div>

	<!-- scripts -->
	<script src="js/particles.min.js"></script>
	<script src="js/app.js"></script>
</body>
</html>

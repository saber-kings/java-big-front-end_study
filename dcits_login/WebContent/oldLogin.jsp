<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录</title>

</head>
<body>

    <div id="login">
	
	     <div id="top">
		      <div id="top_left"><img src="images/login_03.gif" /></div>
			  <div id="top_center"></div>
		 </div>
		 
		 <div id="center">
		      <div id="center_left"></div>
			  <div id="center_middle">
			    <form name="myForm" class="form-signin" action="login" method="post">
			       <div id="user">用 户
			         <input type="text" name="username" />
			       </div>
				   <div id="password">密   码
				     <input type="password" name="password" />
				   </div>
				   <div id="btn"><button class="btn btn-large btn-primary" type="submit">登录</button></div>
			  </form>
			  </div>
			  <div id="center_right"></div>		 
		 </div>
		 <div id="down">
		      <div id="down_left">
			      
			  </div>
			  <div id="down_center"></div>		 
		 </div>
		 <c:if test="${msg != null }">
		 	<p style="color: red;">${msg }</p>
		 </c:if>
	</div>
</body>
</html>

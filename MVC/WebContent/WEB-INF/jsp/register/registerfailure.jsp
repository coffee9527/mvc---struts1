<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册失败</title>
</head>
<body>
	<span>${message}</span><br>
	<span style="color:red;font-size:30px;">注册失败</span><br>
	<a href="http://localhost:8080${pageContext.request.contextPath}/toLogin">去登陆</a><br>
	<a href="http://localhost:8080${pageContext.request.contextPath}/toRegister">继续注册</a><br>
</body>
</html>
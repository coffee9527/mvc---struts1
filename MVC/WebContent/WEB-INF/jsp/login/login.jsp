<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生登陆</title>
</head>
<body>
	<h3>学生登陆</h3>
	<form action="${pageContext.request.contextPath}/login" method="post">
		<table>
			<tr>
				<td><label>姓名</label></td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td><label>密码</label></td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td><input type="submit" value="登陆"></td>
				<td><a href="http://localhost:8080${pageContext.request.contextPath}/toRegister">注册</a></td>
			</tr>
		</table>
	</form>
</body>
</html>
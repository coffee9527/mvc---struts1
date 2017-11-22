<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>学生注册</h3>
	<form action="${pageContext.request.contextPath}/register" method="post">
		<table>
			<tr>
				<td><lable>姓名:</lable></td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td><lable>密码:</lable></td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td><lable>年龄:</lable></td>
				<td><input type="text" name="age"></td>
			</tr>
			<tr>
				<td><lable>生日:</lable></td>
				<td><input type="text" name="birthday"></td>
				<td><lable>(yyyy-MM-dd格式)</lable></td>
			</tr>
			<tr>
				<td><input type="submit" value="注册"></td>
				<td><a href="http://localhost:8080${pageContext.request.contextPath}/toLogin">登陆</a></td>
			</tr>
		</table>
	</form>
</body>
</html>
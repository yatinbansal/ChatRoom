<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<%
if (session.getAttribute("currentSessionUser") != null)
	response.sendRedirect("mainpage.jsp");
%>
	<form action="login.do" method="post"
		style="width: 300px; margin: 0 auto;">
		<table>
			<tr>
				<td>UserId :</td>
				<td><input type="text" name="userid"></td>
			</tr>
			<tr>
				<td>Password :</td>
				<td><input type="password" name="passwd"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="submit"></td>
			</tr>
			<tr>
				<td><a href="signup.html">Sign Up</a></td>
			</tr>
		</table>
	</form>
</body>
</html>
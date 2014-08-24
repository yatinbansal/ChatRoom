<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<script type="text/javascript" src="script_src.js">
	
</script>
</head>
<body>
	<%
		//allow access only if session exists
		String user = null;
		if (session.getAttribute("currentSessionUser") == null ) {
			response.sendRedirect("login.jsp");
		} else
			user = (String) session.getAttribute("currentSessionUser");
		String userId = null;
		String sessionID = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user"))
					userId = cookie.getValue();
				if (cookie.getName().equals("JSESSIONID"))
					sessionID = cookie.getValue();
			}
		}
	%>
	<div id="header">
		<h3 align="center">MyChatRoom</h3>
		<a href=logout.do style="top: 0; right: 0; position: fixed">Logout</a>
	</div>
	<div id="getmsgbox"></div>
	<div id="dec"></div>
	<div id="sendmsgbox">
		<form id="input" action="http://pune876.egain.in:8080/ChatRoom/chat/sendmsg"
			method="post">
			<table>
				<tr>
					<td><input type="hidden" name="user" value=<%=userId%>>
					</td>
					<td><input id="date" type="hidden" name="ts"></td>
				</tr>
				<tr>
					<td>Type Message :</td>
					<td><textarea rows="4" cols="30" id="msg" name="msg"></textarea></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Send"
						onclick="return sendTime();"></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>
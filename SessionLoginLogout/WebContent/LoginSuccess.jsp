<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Login Success</title>
</head>
<body>
	<%
		String user = null;
		if(session.getAttribute("user") == null){
			response.sendRedirect("login.html");
		}
		else {
			user = (String) session.getAttribute("user");
		}
		String userName = null;
		String sessionID = null;
		Cookie[] cookies = request.getCookies();
		for(Cookie c : cookies){
			if(c.getName().equals("user")) {
				userName = c.getValue();
			}
			if(c.getName().equals("JSESSIONID")) {
				sessionID = c.getValue();
			}
		}
	%>
	
	<h3>Hello, <%= userName %>, login successful. Your session ID is <%= sessionID %></h3>
	<p>User is <%= user %></p>
	<p><a href="CheckoutPage.jsp">Checkout Page</a></p>
	<form action="LogoutServlet" method="POST">
		<input type="submit" value="Logout">
	</form>
</body>
</html>
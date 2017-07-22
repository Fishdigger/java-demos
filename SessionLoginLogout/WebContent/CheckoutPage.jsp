<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Checkout</title>
</head>
<body>

	<%
		if(session.getAttribute("user") == null){
			response.sendRedirect("login.html");
		}
		String userName = null;
		String sessionID = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie c : cookies){
				if(c.getName().equals("user")){
					userName = c.getValue();
				}
			}
		}
	%>
	
	<h3>Hello, <%= userName %>, do the needful.</h3>
	<form action="LogoutServlet" method="POST">
		<input type="submit" value="Logout">
	</form>

</body>
</html>
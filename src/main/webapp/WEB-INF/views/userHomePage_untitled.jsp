<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Home Page</title>
<link rel="stylesheet" href="css/login.css" />
</head>
<body>
	<div class="container">
		<h1>User Home Page</h1>
		<!-- Have logic here:
		- If user is admin, display
	 -->
		<a href="${pageContext.request.contextPath}/adminUserManagement">Administrator
			User Management</a>
	</div>
</body>
</html>
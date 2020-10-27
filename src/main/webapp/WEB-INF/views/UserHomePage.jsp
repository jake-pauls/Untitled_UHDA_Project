<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<h2>Hello, ${user.firstName}</h2>
		<c:if test="${user.role == 'admin'}">
			<a href="${pageContext.request.contextPath}/AdminUserManagement">Administrator
				User Management</a>
		</c:if>
	</div>
</body>
</html>
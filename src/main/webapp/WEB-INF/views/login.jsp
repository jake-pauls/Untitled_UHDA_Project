<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel = "stylesheet" href = "css/login.css"/>
</head>
<body>
<div class = "container">
	<h1>Welcome to the UHDA</h1>
	<h2>${test }</h2>
	<form:form action="${pageContext.request.contextPath}/login" class="login_form" modelAttribute="login" method = "post">
	<table>
		<tr class = "login_form_items">
			<td><label for = "username">Username: </label></td>
			<td><form:input path = "username"/></td>
		</tr>
		<tr>
			<td><label for = "password">Password: </label></td>
			<td><form:input path = "password"/></td>
		</tr>
		<tr>
			<td colspan="2"><button>Login</button></td>
		</tr>
	</table>
	</form:form>
	<h4>Need an account?</h4>
	<a href = "${pageContext.request.contextPath}/register">Register Here</a><br>
	<a href = "${pageContext.request.contextPath}/forgotpassword">Forgot Password?</a>
</div>
</body>
</html>
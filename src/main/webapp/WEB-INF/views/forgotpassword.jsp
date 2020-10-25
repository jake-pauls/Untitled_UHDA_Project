<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password</title>
<link rel = "stylesheet" href = "css/login.css"/>
</head>
<body>
<div class = "container">
	<h1>If you forgot your password you can request a reset email here</h1>
	<h2>${test }</h2>
	<form:form action="${pageContext.request.contextPath}/forgotpassword" class="login_form" modelAttribute="forgotPassword" method = "post">
	<table>
		<tr class = "login_form_items">
			<td><label for = "email">email: </label></td>
			<td><form:input path = "email"/></td>
		</tr>
		<tr>
			<td><label for = "password">Password: </label></td>
			<td><form:input path = "password"/></td>
		</tr>
		<tr>
			<td colspan="2"><button>Submit</button></td>
		</tr>
	</table>
	</form:form>
	<a href = "${pageContext.request.contextPath}/login">Back to login page</a>
</div>
</body>
</html>
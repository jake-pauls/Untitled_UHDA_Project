<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel = "stylesheet" href = "css/login.css"/>
</head>
<body>
<div>
<form:form action="${pageContext.request.contextPath}/register" class="register_form" modelAttribute="user" method = "post">
	<table>
		<tr>
			<td><label id="label"  for="username">Username: </label></td>
			<td><form:input id="input" name = "username" path="username"/></td>
		</tr>
		<tr>
			<td><label id="label" for="password">Password: </label></td>
			<td><form:input id="input" name = "password" path="password"/></td>
		</tr>
		<tr>
			<td><label id="label" for="email">Email: </label></td>
			<td><form:input id="input" name = "email" path="email"/></td>
		</tr>
		<tr>
			<td><label id="label" for="firstName">First Name: </label></td>
			<td><form:input id="input" name = "firstName" path="firstName"/></td>
		</tr>
		<tr>
			<td><label id="label" for="lastName">Last Name: </label></td>
			<td><form:input id="input" name = "lastName" path="lastName"/></td>
		</tr>
		<tr>
			<td><label id="label" for="securityQ">Security Question: </label></td>
			<td><form:select id = "securityQ" path = "securityQ">
				<form:options id="input" items = "${sq}"></form:options>
			</form:select></td>
		</tr>
		<tr>
			<td><label id="label" for="securityA">Answer To Security Question: </label></td>
			<td><form:input id="input" name = "securityA" path="securityA"/></td>
		</tr>
		
	</table>
	<button>Create Account</button>
	<form:hidden path="role" value = "user"/>
	<form:hidden path="resetToken" value = ""/>
	
</form:form>
</div>
</body>
</html>
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
<div class = "container">
<c:if test="${error != null }">
<p>${error}</p>
</c:if>
<h2>Please fill out the registration form</h2>
<div class = "register_container">
<form:form action="${pageContext.request.contextPath}/register" class="register_form" modelAttribute="user" method = "post">
	<table>
		<tr>
			<td><label id="label"  for="username">Username: </label></td>
			<td><form:input id="input" name = "username" path="username" required="true"/></td>
		</tr>
		<tr>
			<td><label id="label" for="password">Password: </label></td>
			<td><form:input id="input" name = "password" path="password" required="true"/></td>
		</tr>
		<tr>
			<td><label id="label" for="email">Email: </label></td>
			<td><form:input id="input" name = "email" path="email" required="true"/></td>
		</tr>
		<tr>
			<td><label id="label" for="firstName">First Name: </label></td>
			<td><form:input id="input" name = "firstName" path="firstName" required="true"/></td>
		</tr>
		<tr>
			<td><label id="label" for="lastName">Last Name: </label></td>
			<td><form:input id="input" name = "lastName" path="lastName" required="true"/></td>
		</tr>
		<tr>
			<td><label id="label" for="securityQ">Security Question: </label></td>
			<td><form:select id = "securityQ" path = "securityQ" required="true">
				<form:options id="input" items = "${sq}"></form:options>
			</form:select></td>
		</tr>
		<tr>
			<td><label id="label" for="securityA">Answer To Security Question: </label></td>
			<td><form:input id="input" name = "securityA" path="securityA" required="true"/></td>
		</tr>
		<tr><td colspan="2"><button>Create Account</button></td></tr>
	</table>
	
	<form:hidden path="role" value = "user"/>
	<form:hidden path="resetToken" value = ""/>
	
</form:form>
</div>
</div>
</body>
</html>
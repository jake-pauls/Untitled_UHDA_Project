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
	<h3>Enter your email associated to your account below to reset your password</h1>
	<h2>${resetmessage }</h2>
	<form:form action="${pageContext.request.contextPath}/forgotpassword" class="login_form" modelAttribute="forgotpassword" method = "post">
	<table>
		<tr class = "login_form_items">
			<td><label for = "email">Email: </label></td>
			<td><form:input path = "email"/></td>
		</tr>
		<tr>
			<td colspan="2"><button>Submit</button></td>
		</tr>
	</table>
	</form:form>
	<h4>Back to Login Page? <a href = "${pageContext.request.contextPath}/login">Click Here</a></h4>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password</title>
<link rel="stylesheet" href="css/login.css" />
</head>
<body>
	<div class="container">
		<h1>Enter your email below and click submit to reset password</h1>
		<form:form action="${pageContext.request.contextPath}/forgot-password"
			class="register_form" modelAttribute="user" method="post">
			<table>
				<tr>
					<td><label id="label" for="email">Email: </label></td>
					<td><form:input id="input" name="email" path="email"
							required="true" /></td>
				</tr>
				<tr>
					<td colspan="2"><button>Submit</button></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>
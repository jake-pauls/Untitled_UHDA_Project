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
<link rel = "stylesheet" href = "css/login.css"/>
</head>
<body>
	<div class="container">
		<h1>Edit the Selected Student and click Save</h1>
		<form:form action="${pageContext.request.contextPath}/forgot-password"
			cssClass="form-horizontal" method="post" modelAttribute="user">
			<div class="form-group">
				<label for="email" class="col-md-3 controllabel"> email: </label>
				<div class="col-md-9">
					<form:input path="email" name="email" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<!-- Button -->
				<div class="col-md-offset-3 col-md-9">
					<form:button cssClass="btn btnprimary">Save</form:button>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>
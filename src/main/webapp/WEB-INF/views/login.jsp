<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<!-- UIkit CSS/JS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/uikit@3.5.9/dist/css/uikit.min.css" />
<script
	src="https://cdn.jsdelivr.net/npm/uikit@3.5.9/dist/js/uikit.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/uikit@3.5.9/dist/js/uikit-icons.min.js"></script>
</head>
<body>
	<div class="uk-position-center">
		<h1 class="uk-text-lead uk-text-bold">Welcome to the UHDA</h1>

		<c:if test="${not empty param['error']}">
			<div class="uk-alert-danger" uk-alert>
				<c:out value="Username or Password Incorrect" />
			</div>
		</c:if>

		<c:if test="${successMessage != null}">
			<div class="uk-alert-success" uk-alert>
				<p>${successMessage}</p>
			</div>
		</c:if>

		<form:form action="${pageContext.request.contextPath}/login"
			class="uk-form-stacked" modelAttribute="login" method="post">

			<div class="uk-margin">
				<label class="uk-form-label" for="username">Username: </label>
				<div class="uk-form-controls">
					<div class="uk-inline">
						<span class="uk-form-icon" uk-icon="icon: user"></span>
						<form:input class="uk-input uk-form-width-large" id="username"
							type="text" path="username" />
					</div>
				</div>
			</div>

			<div class="uk-margin">
				<label class="uk-form-label" for="password">Password: </label>
				<div class="uk-form-controls">
					<div class="uk-inline">
						<span class="uk-form-icon" uk-icon="icon: lock"></span>
						<form:input class="uk-input uk-form-width-large" id="password"
							type="password" path="password" />
					</div>
				</div>
			</div>

			<button class="uk-button uk-button-primary">Login</button>

		</form:form>

		<h4>Need an account?</h4>
		<a href="${pageContext.request.contextPath}/register">Register
			Here</a><br> <a
			href="${pageContext.request.contextPath}/forgotpassword">Forgot
			Password?</a><br> <a
			class="uk-button uk-button-default uk-margin-top"
			href="${pageContext.request.contextPath}/">Back</a>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
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
		<h2 class="uk-text-lead">Please fill out the registration form below</h2>

		<c:if test="${ error != null }">
			<div class="uk-alert-danger" uk-alert>
				<p>${error}</p>
			</div>
		</c:if>

		<form:form action="${pageContext.request.contextPath}/register"
			class="register_form" modelAttribute="user" method="post">

			<div class="uk-margin">
				<label class="uk-form-label" for="username">Username: </label>
				<div class="uk-form-controls">
					<div class="uk-inline">
						<span class="uk-form-icon" uk-icon="icon: user"></span>
						<form:input class="uk-input uk-form-width-large" id="username"
							type="text" path="username" required="true" />
					</div>
				</div>
			</div>

			<div class="uk-margin">
				<label class="uk-form-label" for="password">Password: </label>
				<div class="uk-form-controls">
					<div class="uk-inline">
						<span class="uk-form-icon" uk-icon="icon: lock"></span>
						<form:input class="uk-input uk-form-width-large" id="password"
							type="password" path="password" required="true" />
					</div>
				</div>
			</div>

			<div class="uk-margin">
				<label class="uk-form-label" for="email">Email: </label>
				<div class="uk-form-controls">
					<div class="uk-inline">
						<span class="uk-form-icon" uk-icon="icon: mail"></span>
						<form:input class="uk-input uk-form-width-large" id="email"
							type="text" path="email" required="true" />
					</div>
				</div>
			</div>

			<div class="uk-margin">
				<label class="uk-form-label" for="firstName">First Name: </label>
				<div class="uk-form-controls">
					<form:input class="uk-input uk-form-width-large" id="firstName"
						type="text" path="firstName" required="true" />
				</div>
			</div>

			<div class="uk-margin">
				<label class="uk-form-label" for="lastName">Last Name: </label>
				<div class="uk-form-controls">
					<form:input class="uk-input uk-form-width-large" id="lastName"
						type="text" path="lastName" required="true" />
				</div>
			</div>

			<div class="uk-margin">
				<label class="uk-form-label" for="securityQ">Security
					Question: </label>
				<div class="uk-form-controls">
					<form:select class="uk-select uk-form-width-large" id="securityQ"
						path="securityQ" required="true">
						<form:options id="input" items="${sq}"></form:options>
					</form:select>
				</div>
			</div>

			<div class="uk-margin">
				<label class="uk-form-label" for="securityA">Security
					Answer: </label>
				<div class="uk-form-controls">
					<form:input class="uk-input uk-form-width-large" id="securityA"
						type="text" path="securityA" required="true" />
				</div>
			</div>

			<button class="uk-button uk-button-primary">Create Account</button>
			<form:hidden path="role" value="user" />
			<form:hidden path="resetToken" value="" />
			
		</form:form>
	</div>
</body>
</html>
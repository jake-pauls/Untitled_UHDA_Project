<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password</title>
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

		<c:if test="${resetErrorMessage != null}">
			<div class="uk-alert-danger" uk-alert>
				<p>${resetErrorMessage}</p>
			</div>
		</c:if>

		<c:if test="${resetSuccessMessage != null}">
			<div class="uk-alert-success" uk-alert>
				<p>${resetSuccessMessage}</p>
			</div>
		</c:if>

		<form:form action="${pageContext.request.contextPath}/forgotpassword"
			class="uk-form-stacked" modelAttribute="forgotpassword" method="post">

			<div class="uk-margin">
				<label class="uk-form-label uk-text-bold uk-margin-bottom"
					for="email">Enter the email associated with your account
					below to reset your password </label>
				<div class="uk-form-controls">
					<div class="uk-inline">
						<span class="uk-form-icon" uk-icon="icon: mail"></span>
						<form:input class="uk-input uk-form-width-large" id="email"
							type="text" path="email" />
					</div>
				</div>
			</div>

			<div uk-grid>
				<div class="uk-width-1-4">
					<button class="uk-button uk-button-primary">Submit</button>
				</div>
				<div class="uk-width-1-4 uk-margin-auto-left">
					<a class="uk-button uk-button-default"
						href="${pageContext.request.contextPath}/login">Back</a>
				</div>
			</div>
		</form:form>

	</div>
</body>
</html>
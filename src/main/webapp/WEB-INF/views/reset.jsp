<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reset Password</title>
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
		<h2 class="uk-text-lead">${resetmessage}</h2>
		<c:choose>
			<c:when test="${error != null}">
				
				<div class="uk-alert-danger" uk-alert>
					<h1 class="uk-text-lead uk-text-bold">Sorry this is an invalid
						password reset link.</h1>
				</div>
				
			</c:when>
			<c:otherwise>

				<form:form action="${pageContext.request.contextPath}/reset"
					class="uk-form-stacked" modelAttribute="resetpassword"
					method="post">

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

					<button class="uk-button uk-button-primary">Reset Password</button>
					<form:hidden path="resetToken" value="" />
				</form:form>

			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
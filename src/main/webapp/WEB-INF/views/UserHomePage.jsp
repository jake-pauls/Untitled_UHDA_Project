<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Home Page</title>
<!-- UIkit CSS/JS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/uikit@3.5.9/dist/css/uikit.min.css" />
<script
	src="https://cdn.jsdelivr.net/npm/uikit@3.5.9/dist/js/uikit.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/uikit@3.5.9/dist/js/uikit-icons.min.js"></script>
</head>
<body>
	<div class="uk-section uk-section-default">
		<div class="uk-container uk-position-center">
			<h2 class="uk-heading-small uk-text-bold uk-text-center">Hello,
				${loggedInUser.firstName}</h2>

			<div class="uk-margin-medium" uk-grid>

				<div class="uk-width-auto@m">
					<sec:authorize access="hasRole('ADMIN')">
						<a href="${pageContext.request.contextPath}/AdminUserManagement"
							class="uk-icon-button" uk-icon="icon: users; ratio: 1.25"
							uk-tooltip="Manage Users"></a>
					</sec:authorize>
				</div>
				<div class="uk-width-auto@m">
					<sec:authorize access="hasRole('EMPLOYEE')">
						<a href="${pageContext.request.contextPath}/employeeHomePage"
							class="uk-icon-button" uk-icon="icon: users; ratio: 1.25"
							uk-tooltip="Employee Page"></a>
					</sec:authorize>
				</div>
				<div class="uk-width-auto@m">
					<sec:authorize access="hasRole('USER')">
						<a href="${pageContext.request.contextPath}/employeeHomePage"
							class="uk-icon-button" uk-icon="icon: users; ratio: 1.25"
							uk-tooltip="Employee Page"></a>
					</sec:authorize>
				</div>

			</div>

			<div class="uk-margin-medium">
				<h4 class="uk-text-lead uk-text-center">
					<a class="uk-button uk-button-default"
						href="${pageContext.request.contextPath}/logout">Logout</a>
				</h4>
			</div>
		</div>
	</div>
</body>
</html>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<title>Welcome</title>
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
			<h2 class="uk-heading-medium uk-text-bolder">Welcome to the UHDA</h2>
			<h3 class="uk-text-lead uk-text-center uk-text-lighter">Lightweight
				Help Desk Ticketing, Simplified.</h3>
			<h4 class="uk-text-lead uk-text-center">
				<a class="uk-button uk-button-default"
					href="${pageContext.request.contextPath}/login">Login</a>
			</h4>
		</div>
	</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reset Password</title>
<link rel="stylesheet" href="css/login.css" />
</head>
<body>
	<div class="container">
	<div class="login_container">
		<h2>${resetmessage }</h2>
		<c:choose>
			<c:when test="${error != null }">
				<h1>Sorry this is an invalid password reset link.</h1>
			</c:when>
			<c:otherwise>
				
					<form:form action="${pageContext.request.contextPath}/reset"
						class="login_form" modelAttribute="resetpassword" method="post">
						<table>
							<tr class = "login_form_items">
								<td><label for="password">Password: </label></td>
								<td><form:input name="password" path="password"
										required="true" /></td>
							</tr>
							<tr>
								<td colspan="2"><button>Reset Password</button></td>
							</tr>
						</table>

						<form:hidden path="resetToken" value="" />
					</form:form>
				
			</c:otherwise>
		</c:choose>
	</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<c:url value="/js/jquery-3.5.1.min.js" />"></script>
<script src="<c:url value="/js/AdminUserManagementTable_jpa_66.js" />"></script>
<title>Workspace Name - User Management</title>
<!-- UIkit CSS/JS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/uikit@3.5.9/dist/css/uikit.min.css" />
<script
	src="https://cdn.jsdelivr.net/npm/uikit@3.5.9/dist/js/uikit.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/uikit@3.5.9/dist/js/uikit-icons.min.js"></script>
</head>
<body>
	<div class="uk-container uk-position-center">
		<h1
			class="uk-heading-small uk-text-bold uk-heading-line uk-text-left uk-margin-small">
			<span>User List</span>
		</h1>

		<c:if test="${errorMessage != null}">
			<div class="uk-alert-danger" uk-alert>
				<p>${errorMessage}</p>
			</div>
		</c:if>

		<c:if test="${successMessage != null}">
			<div class="uk-alert-success" uk-alert>
				<p>${successMessage}</p>
			</div>
		</c:if>

		<table class="uk-table uk-table-divider">
			<thead>
				<tr>
					<th>Username</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Role</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${userList}">
				
					<tr>
						<td><input id="row-reference-${user.username}-username"
							class="uk-input username" type="text" value="${user.username}"
							disabled /></td>
						<td><input id="row-reference-${user.username}-firstName"
							class="uk-input ${user.username} firstName" type="text"
							value="${user.firstName}" disabled /></td>
						<td><input id="row-reference-${user.username}-lastName"
							class="uk-input ${user.username} lastName" type="text"
							value="${user.lastName}" disabled /></td>
						<td><input id="row-reference-${user.username}-email"
							class="uk-input ${user.username} email" type="text"
							value="${user.email}" disabled /></td>
						<td><select id="row-reference-${user.username}-role"
							class="uk-select ${user.username} role" disabled>
								<c:if test="${user.role == 'admin'}">
									<option value="admin" selected>admin</option>
									<option value="user">user</option>
									<option value="employee">employee</option>
								</c:if>
								<c:if test="${user.role == 'user'}">
									<option value="admin">admin</option>
									<option value="user" selected>user</option>
									<option value="employee">employee</option>
								</c:if>
								<c:if test="${user.role == 'employee'}">
									<option value="admin">admin</option>
									<option value="user">user</option>
									<option value="employee" selected>employee</option>
								</c:if>
						</select></td>
						<td><button class="uk-button uk-button-default"
								onclick="editTableRow('${user.username}')">Edit</button></td>
						<td><form:form method="post"
								action="${pageContext.request.contextPath}/AdminUserManagementDeleteUser"
								modelAttribute="user">
								<form:input type="hidden" path="username"
									value="${user.username}" />
								<button class="uk-button uk-button-danger" type="submit"
									name="deleteUser">Delete</button>
							</form:form></td>
					</tr>
					
				</c:forEach>
				<tr id="newTableRow" style="display: none;">
				
					<form:form method="post"
						action="${pageContext.request.contextPath}/AdminUserManagementCreateUser"
						modelAttribute="user">
						<td><form:input class="uk-input newUser username"
								path="username" type="text" /></td>
						<td><form:input class="uk-input newUser firstName"
								path="firstName" type="text" /></td>
						<td><form:input class="uk-input newUser lastName"
								path="lastName" type="text" /></td>
						<td><form:input class="uk-input newUser email" path="email"
								type="text" /></td>
						<td><form:select class="uk-select newUser role" path="role">
								<option value="admin" selected>admin</option>
								<option value="user">user</option>
								<option value="employee">employee</option>
							</form:select></td>
						<td><form:button class="uk-button uk-button-primary">Submit</form:button></td>
						<td><button class="uk-button uk-button-default" type="button"
								onclick="cancelAddUser()">Cancel</button></td>
					</form:form>
					
				</tr>
			</tbody>
		</table>

		<form:form method="post"
			action="${pageContext.request.contextPath}/AdminUserManagementUpdateUser"
			modelAttribute="user">
			<form:hidden id="hidden-username" path="username" value="" />
			<form:hidden id="hidden-firstName" path="firstName" value="" />
			<form:hidden id="hidden-lastName" path="lastName" value="" />
			<form:hidden id="hidden-email" path="email" value="" />
			<form:hidden id="hidden-role" path="role" value="" />

			<div uk-margin>
				<form:button class="uk-button uk-button-primary"
					id="button-reference" type="submit" name="saveUser" disabled="true">Save Changes</form:button>
				<button class="uk-button uk-button-secondary uk-margin-left"
					id="addNewUser" onclick="addNewTableRow()">Add New User</button>
				<a class="uk-button uk-button-default uk-margin-left" href="${pageContext.request.contextPath}/login">Back</a>
			</div>

		</form:form>
	</div>
</body>
</html>

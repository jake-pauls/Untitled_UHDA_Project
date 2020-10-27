<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<c:url value="/js/jquery-3.5.1.min.js" />"></script>
<script src="<c:url value="/js/adminUserManagementTable_untitled.js" />"></script>
<title>Workspace Name - User Management</title>
</head>
<body>
	<h1>Workspace Name - User Management</h1>
	<div class="container">
		<h1>User List</h1>
		<hr />
		<c:if test="${errorMessage != null}">
			<div>${errorMessage}</div>
		</c:if>
		<c:if test="${successMessage != null}">
			<div>${successMessage}</div>
		</c:if>
		<table>
			<tr>
				<td>Username</td>
				<td>First Name</td>
				<td>Last Name</td>
				<td>Email</td>
				<td>Role</td>
			</tr>
			<c:forEach var="user" items="${userList}">
				<tr>
					<td><input id="row-reference-${user.username}-username"
						class="username" type="text" value="${user.username}" disabled /></td>
					<td><input id="row-reference-${user.username}-firstName"
						class="${user.username} firstName" type="text"
						value="${user.firstName}" disabled /></td>
					<td><input id="row-reference-${user.username}-lastName"
						class="${user.username} lastName" type="text"
						value="${user.lastName}" disabled /></td>
					<td><input id="row-reference-${user.username}-email"
						class="${user.username} email" type="text" value="${user.email}"
						disabled /></td>
					<td><select id="row-reference-${user.username}-role"
						class="${user.username} role" disabled>
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
					<td><button onclick="editTableRow('${user.username}')">Edit</button></td>
					<td><form:form method="post"
							action="${pageContext.request.contextPath}/adminUserManagementDeleteUser"
							modelAttribute="user">
							<form:input type="hidden" path="username"
								value="${user.username}" />
							<button type="submit" name="deleteUser">Delete</button>
						</form:form></td>
				</tr>
			</c:forEach>
			<tr id="newTableRow" style="display: none;">
				<form:form method="post"
					action="${pageContext.request.contextPath}/adminUserManagementCreateUser"
					modelAttribute="user">
					<td><form:input class="newUser username" path="username"
							type="text" /></td>
					<td><form:input class="newUser firstName" path="firstName"
							type="text" /></td>
					<td><form:input class="newUser lastName" path="lastName"
							type="text" /></td>
					<td><form:input class="newUser email" path="email" type="text" /></td>
					<td><form:select class="newUser role" path="role">
							<option value="admin" selected>admin</option>
							<option value="user">user</option>
							<option value="employee">employee</option>
						</form:select></td>
					<td><form:button>Submit</form:button></td>
					<td><button type="button" onclick="cancelAddUser()">Cancel</button></td>
				</form:form>
			</tr>
		</table>
		<form:form method="post"
			action="${pageContext.request.contextPath}/adminUserManagementUpdateUser"
			modelAttribute="user">
			<form:hidden id="hidden-username" path="username" value="" />
			<form:hidden id="hidden-firstName" path="firstName" value="" />
			<form:hidden id="hidden-lastName" path="lastName" value="" />
			<form:hidden id="hidden-email" path="email" value="" />
			<form:hidden id="hidden-role" path="role" value="" />
			<form:button id="button-reference" type="submit" name="saveUser"
				disabled="true">Save Changes</form:button>
		</form:form>
		<button id="addNewUser" onclick="addNewTableRow()">Add New
			User</button>
	</div>
</body>
</html>

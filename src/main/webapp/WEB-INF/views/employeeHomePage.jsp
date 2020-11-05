<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel = "stylesheet" href = "css/homepage.css" />
<meta charset="ISO-8859-1">
<title>Employee</title>
</head>
<body>
<div class = "header" style="font-family: arial">
	<div class="myTicket">
		<p id="headerText">My Tickets</p>
	</div>
	<div class="avaliableTickets">
		<p id="headerText">Available Tickets</p>
	</div>
	<div id="headerSpace"></div>
		
	<div id="profile">
		<c:choose>
			<c:when test="${user != null }"><p id="headerText">${user.username }</p></c:when>
			<c:otherwise><p id="headerText">Sign In</p></c:otherwise>
		</c:choose>
		
	</div>
</div>
<div class="view">
<div id = "viewTitle">
	${tab}
 
</div>

</div>

</body>
</html>
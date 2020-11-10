<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Home Page</title>
<link rel="stylesheet" href="css/homepage.css" />
<!-- UIkit CSS/JS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/uikit@3.5.9/dist/css/uikit.min.css" />
<script
	src="https://cdn.jsdelivr.net/npm/uikit@3.5.9/dist/js/uikit.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/uikit@3.5.9/dist/js/uikit-icons.min.js"></script>
</head>
<body>
	
	<div class="uk-container uk-container-large">	
			
			<div class = "uk-clearfix" id = "top_margin_small">
			<div class = "uk-float-right" uk-grid>

				<div>
					<sec:authorize access="hasRole('ADMIN')">
						<a href="${pageContext.request.contextPath}/AdminUserManagement"
							class="uk-icon-button" uk-icon="icon: users; ratio: 1.25"
							uk-tooltip="Manage Users"></a>
					</sec:authorize>
				</div>
				<div>
					<sec:authorize access="hasRole('EMPLOYEE')">
						<a href="${pageContext.request.contextPath}/employeeHomePage"
							class="uk-icon-button" uk-icon="icon: users; ratio: 1.25"
							uk-tooltip="Employee Page"></a>
					</sec:authorize>
				</div>
				<div>
				<h4 class="uk-text-lead">
					<a class="uk-icon-button" uk-icon="icon: sign-out; ratio: 1.25"
						href="${pageContext.request.contextPath}/logout" 
						uk-tooltip="Log Out"></a>
				</h4>
				</div>

			</div>
			</div>
		
		
	
	


		<ul class="uk-tab" uk-tab>
			<li class="uk-active"><a href="#">My Tickets</a></li>
			<li><a href="#">Create Ticket</a></li>

		</ul>
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


		<ul class="uk-switcher">
			<!-- Assigned Ticket display -->
			<li class="uk-active">
				<ul class="uk-tab">
					<li><a href="#">Sort By <span class="uk-margin-small-left"
							uk-icon="icon: triangle-down"></span></a>
						<div uk-dropdown="mode: click">
							<ul class="uk-nav uk-dropdown-nav">
								<li><a
									href="${pageContext.request.contextPath}/sortUser?order=username">Author</a></li>
								<li><a
									href="${pageContext.request.contextPath}/sortUser?order=priority">Priority</a></li>
								<li><a
									href="${pageContext.request.contextPath}/sortUser?order=status">Status</a></li>
								<li><a
									href="${pageContext.request.contextPath}/sortUser?order=dateOpened">Date
										Opened</a></li>
								<li><a
									href="${pageContext.request.contextPath}/sortUser?order=title">Title</a></li>
								<li><a
									href="${pageContext.request.contextPath}/sortUser?order=lastUpdated">Last
										Updated</a></li>
							</ul>
						</div></li>
				</ul>
				<div uk-slider="finite: true">
					<div class="uk-slider-container">
						<div class="uk-position-relative uk-visible-toggle" tabindex="-1">

							<ul
								class="uk-slider-items uk-child-width-1-5@s uk-child-width-1-4@">
								
								<c:forEach items="${createdList}" var="ticket">
									<li class="uk-padding-small">
										<div id = "bor">
											<h3>${ticket.title }</h3>
											<hr>
											<h4>${ticket.username }</h4>
											<p>Status: ${ticket.status }</p>
											<p>Priority: ${ticket.priority }</p>
											<p>
												Opened:<br> ${ticket.formDateOpen }
											</p>
										 <a class="uk-button uk-button-default"
										href="#my${ticket.ticketID }" uk-toggle>Open</a>
										</div>
										<div id="my${ticket.ticketID }" uk-modal>
											<div class="uk-modal-dialog">
												<button class="uk-modal-close-default" type="button"
													uk-close></button>
												<div class="uk-modal-header">
													<h2 class="uk-modal-title">${ticket.title }</h2>
												</div>
												<div class="uk-modal-body">
													<p>${ticket.description }</p>
													<form:form method="post"
														action="${pageContext.request.contextPath}/ChangeTicketStatus"
														modelAttribute="ticket">
														<table class="uk-table">
															<tr>
																<td>Status:</td>
																<td><form:select path="status">
																		<c:forEach items="${statusList}" var="status">
																			<form:option value="${status }"
																				selected="${status == ticket.status ? 'selected' : ''}" />
																		</c:forEach>

																	</form:select></td>
																<td><form:button
																		class="uk-button uk-button-primary uk-button-small">save</form:button></td>
															</tr>
														</table>
														<form:hidden path="ticketID" value="${ticket.ticketID }" />
														<form:hidden path="username" value="${ticket.username }" />
														<form:hidden path="assignee" value="${ticket.assignee }" />
													</form:form>
													<form:form method="post"
														action="${pageContext.request.contextPath}/AssignTicket"
														modelAttribute="ticket">
														
														<form:hidden path="ticketID" value="${ticket.ticketID }" />
														<form:hidden path="username" value="${ticket.username }" />
													</form:form>
													<form:form method="post"
														action="${pageContext.request.contextPath}/ChangeTicketPriority"
														modelAttribute="ticket">
														<table class="uk-table">
															<tr>
																<td>Priority:</td>
																<td><form:select path="priority">
																		<c:forEach items="${priorityList}" var="priority">
																			<form:option value="${priority }"
																				selected="${priority == ticket.priority ? 'selected' : ''}" />
																		</c:forEach>
																	</form:select></td>
																<td><form:button
																		class="uk-button uk-button-primary uk-button-small">save</form:button></td>
															</tr>
														</table>
														<form:hidden path="ticketID" value="${ticket.ticketID }" />
														<form:hidden path="username" value="${ticket.username }" />
														<form:hidden path="assignee" value="${ticket.assignee }" />
													</form:form>
												</div>
												<div class="uk-modal-footer uk-text-right">
													<button class="uk-button uk-button-default uk-modal-close"
														type="button">Cancel</button>
												</div>
											</div>
										</div>
									</li>


								</c:forEach>

							</ul>

							<a
								class="uk-position-center-left uk-position-small uk-hidden-hover"
								href="#" uk-slidenav-previous uk-slider-item="previous"></a> <a
								class="uk-position-center-right uk-position-small uk-hidden-hover"
								href="#" uk-slidenav-next uk-slider-item="next"></a>

						</div>
					</div>
				</div>
			</li>
			<!-- Available ticket display -->
			
			<li>
			<!-- Create ticket form goes here -->
				
			</li>
		</ul>
		
		

	</div>
</body>
</html>
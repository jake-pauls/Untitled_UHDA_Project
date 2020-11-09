<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/uikit@3.5.9/dist/css/uikit.min.css" />
<link rel="stylesheet" href="css/homepage.css" />
<script
	src="https://cdn.jsdelivr.net/npm/uikit@3.5.9/dist/js/uikit.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/uikit@3.5.9/dist/js/uikit-icons.min.js"></script>
<meta charset="ISO-8859-1">
<title>Employee</title>
</head>
<body>
	<div class="uk-container uk-container-large">
		<nav class="uk-navbar-container" uk-navbar>
			<div class="uk-navbar-left">
				<ul class="uk-navbar-nav">
					<li class="uk-active"><a href="">My Tickets</a></li>
					<li class="uk-nav"><a href="">Active Tickets</a></li>
					<li class="uk-nav"><a href="#createTicket" uk-toggle>Create Ticket</a></li>
						
						<div id="createTicket" uk-modal>
							<div class="uk-modal-dialog">
								<button class="uk-modal-close-default" type="button" uk-close></button>
								<div class="uk-modal-header">
									<h2 class="uk-modal-title">Create A Ticket</h2>
								</div>
							<form:form action="${pageContext.request.contextPath}/createTicket" modelAttribute="ticket">
								<div class="uk-modal-body">
									<p>Please fill the information below to create a new ticket</p>
										<div class="uk-margin">
											<label class="uk-form-label" for="title">Ticket Title: </label>
											<div class="uk-form-controls">
												<div class="uk-inline">
													<form:input class="uk-input uk-form-width-large" id="title"
														type="text" path="title" />
												</div>
											</div>
										</div>
										
										<div class="uk-margin">
											<label class="uk-form-label" for="description">Description: </label>
											<div class="uk-form-controls">
												<div class="uk-inline">
													<form:input class="uk-input uk-form-width-large" id="description"
														type="text" path="description" />
												</div>
											</div>
										</div>
										
										<div class="uk-margin">
											<label class="uk-form-label" for="description">Priority: </label>
											<div class="uk-form-controls">
												<div class="uk-inline">
													<form:select class="uk-input uk-form-width-large" id="priority" path="priority">
														<form:option value="" disabled="true" selected="true">Select a priority level</form:option>
														<c:forEach items="${priorityList}" var="priority">
															<form:option value="${priority}" />
														</c:forEach>
													</form:select>
												</div>
											</div>
										</div>
										
										<div class="uk-margin">
											<label class="uk-form-label" for="category">Category: </label>
											<div class="uk-form-controls">
												<div class="uk-inline">
													<form:select class="uk-input uk-form-width-large" id="category" path="category">
														<form:option value="" disabled="true" selected="true">Select a category</form:option>
														<c:forEach items="${categoryList}" var="category">
															<form:option value="${category }" />
														</c:forEach>
													</form:select>
												</div>
											</div>
										</div>
										
										<sec:authorize access="isAuthenticated()">
											<form:input type="hidden" path="username" value="${loggedInUser.username}"/>
										</sec:authorize>
										
										<div class="uk-modal-footer uk-text-right">
											<button class="uk-button uk-button-default uk-modal-close"
												type="button">Cancel</button>
											<button class="uk-button uk-button-primary" type="submit">Create Ticket</button>
										</div>
									</div>
								</form:form>		
							</div>
						</div>
						
					<li class="uk-nav">
						<button class="uk-button uk-button-default uk-margin-top uk-margin-small-left" type="button">Sort By</button>
						<div uk-dropdown="mode: click">
							<ul class="uk-nav uk-dropdown-nav">
								<li><a href="${pageContext.request.contextPath}/sort?order=username">Author</a></li>
								<li><a href="${pageContext.request.contextPath}/sort?order=priority">Priority</a></li>
								<li><a href="${pageContext.request.contextPath}/sort?order=status">Status</a></li>
								<li><a href="${pageContext.request.contextPath}/sort?order=dateOpened">Date Opened</a></li>
								<li><a href="${pageContext.request.contextPath}/sort?order=title">Title</a></li>
								<li><a href="${pageContext.request.contextPath}/sort?order=lastUpdated">Last Updated</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
		</nav>

		<div uk-slider="finite: true">
			<div class="uk-slider-container">
				<div class="uk-position-relative uk-visible-toggle" tabindex="-1">

					<ul
						class="uk-slider-items uk-child-width-1-5@s uk-child-width-1-4@">
						<c:forEach items="${assignedTickets }" var="ticket">
							<li class="uk-padding-small">
								<div>
									<h3>${ticket.title }</h3>
									<h4>${ticket.username }</h4>
									<p>Status: ${ticket.status }</p>
									<p>Priority: ${ticket.priority }</p>
									<p>Opened: ${ticket.dateOpened }</p>
								</div> <a class="uk-button uk-button-default"
								href="#my${ticket.ticketID }" uk-toggle>Open</a>

								<div id="my${ticket.ticketID }" uk-modal>
									<div class="uk-modal-dialog">
										<button class="uk-modal-close-default" type="button" uk-close></button>
										<div class="uk-modal-header">
											<h2 class="uk-modal-title">${ticket.title }</h2>
										</div>
										<div class="uk-modal-body">
											<p>${ticket.description }</p>
											<form:form modelAttribute="ticket">
												<table class="uk-table">
													<tr>
														<td>Status:</td>
														<td><form:select path="status">
																<c:forEach items="${statusList}" var="status">
																	<form:option value="${status }"
																		selected="${status == ticket.status ? 'selected' : ''}" />
																</c:forEach>

															</form:select></td>
													</tr>
												</table>
											</form:form>
										</div>
										<div class="uk-modal-footer uk-text-right">
											<button class="uk-button uk-button-default uk-modal-close"
												type="button">Cancel</button>
											<button class="uk-button uk-button-primary" type="button">Save</button>
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

	</div>


</body>
</html>
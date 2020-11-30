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
		<div class="uk-clearfix" id="top_margin_small">
			<div class="uk-float-right uk-grid-small"  uk-grid>
				<div>
					<sec:authorize access="hasRole('ADMIN')">
						<a href="${pageContext.request.contextPath}/AdminUserManagement"
							class="uk-icon-button" uk-icon="icon: users; ratio: 1.25"
							uk-tooltip="Manage Users"></a>
					</sec:authorize>
				</div>
				<div>
					<a href="${pageContext.request.contextPath}/UserHomePage"
						class="uk-icon-button" uk-icon="icon: home; ratio: 1.25"
						uk-tooltip="Home page"></a>
				</div>
				<div>
					<a class="uk-icon-button" uk-icon="icon: sign-out; ratio: 1.25"
						href="${pageContext.request.contextPath}/logout"
						uk-tooltip="Log Out"></a>
				</div>
			</div>
		</div>

		<ul class="uk-tab" uk-tab>
			<li class="uk-active"><a href="#">Assigned Tickets</a></li>
			<li><a href="#">Available Tickets</a></li>
			<li><a href="#">Create Ticket</a></li>

		</ul>
		<c:if test="${errorMessage != null}">
			<div class="uk-alert-danger" uk-alert>
				<a class="uk-alert-close" uk-close></a>
				<p>${errorMessage}</p>
			</div>
		</c:if>

		<c:if test="${successMessage != null}">
			<div class="uk-alert-success" uk-alert>
				<a class="uk-alert-close" uk-close></a>
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
									href="${pageContext.request.contextPath}/sort?order=username">Author</a></li>
								<li><a
									href="${pageContext.request.contextPath}/sort?order=priority">Priority</a></li>
								<li><a
									href="${pageContext.request.contextPath}/sort?order=status">Status</a></li>
								<li><a
									href="${pageContext.request.contextPath}/sort?order=dateOpened">Date
										Opened</a></li>
								<li><a
									href="${pageContext.request.contextPath}/sort?order=title">Title</a></li>
								<li><a
									href="${pageContext.request.contextPath}/sort?order=lastUpdated">Last
										Updated</a></li>
							</ul>
						</div></li>
				</ul>
				<div uk-slider="finite: true">
					<div class="uk-slider-container">
						<div class="uk-position-relative uk-visible-toggle" tabindex="-1">

							<ul
								class="uk-slider-items uk-child-width-1-5@s uk-child-width-1-4@">
								<c:forEach items="${assignedTickets }" var="ticket">
									<li class="uk-padding-small">
										<div id = "bor">
											<h3>${ticket.title }</h3>
											<h5>Assigned to: ${ticket.assignee }</h5>
											<hr>
											<h4><span class="uk-text-light">Author: </span> ${ticket.username }</h4>
											<p>Category: ${ticket.category }</p>
											<p>Status: ${ticket.status }</p>
											<p>Priority: <span class="uk-text-light" style="color: ${ticket.priorityColour};">${ticket.priority }</span></p>
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
													<form:form class="uk-form-horizontal uk-margin-small" method="post"
														action="${pageContext.request.contextPath}/ChangeTicketStatus"
														modelAttribute="ticket">
														<label class="uk-form-label" for="status">Status:</label>
														<div class="uk-form-controls">
															<div class="uk-inline">
																<form:select class="uk-input uk-form-width-medium uk-margin-small-right" id="status" path="status">
																		<c:forEach items="${statusList}" var="status">
																			<form:option value="${status }"
																				selected="${status == ticket.status ? 'selected' : ''}" />
																		</c:forEach>
																</form:select>
																<form:button
																		class="uk-button uk-button-primary uk-button-small">save</form:button>
															</div>
														</div>
														<form:hidden path="ticketID" value="${ticket.ticketID }" />
														<form:hidden path="username" value="${ticket.username }" />
														<form:hidden path="assignee" value="${ticket.assignee }" />
														<form:hidden path="title" value="${ticket.title }" />
													</form:form>
													<form:form class="uk-form-horizontal uk-margin-small" method="post"
														action="${pageContext.request.contextPath}/AssignTicket"
														modelAttribute="ticket">
														<label class="uk-form-label" for="assignee">Assigned To:</label>
														<div class="uk-form-controls">
															<div class="uk-inline">
																<form:select class="uk-input uk-form-width-medium uk-margin-small-right" id="assignee" path="assignee">
																	<c:forEach items="${employeeList}" var="assignee">
																		<form:option value="${assignee.username}" />
																	</c:forEach>
																</form:select>
																<form:button
																		class="uk-button uk-button-primary uk-button-small">save</form:button>
															</div>
														</div>
														<form:hidden path="ticketID" value="${ticket.ticketID }" />
														<form:hidden path="username" value="${ticket.username }" />
														<form:hidden path="title" value="${ticket.title }" />
													</form:form>
													<form:form class="uk-form-horizontal uk-margin-small" method="post"
														action="${pageContext.request.contextPath}/ChangeTicketPriority"
														modelAttribute="ticket">
														<label class="uk-form-label" for="priority">Priority:</label>
														<div class="uk-form-controls">
															<div class="uk-inline">
																<form:select class="uk-input uk-form-width-medium uk-margin-small-right" id="priority" path="priority">
																	<c:forEach items="${priorityList}" var="priority">
																		<form:option value="${priority }"
																			selected="${priority == ticket.priority ? 'selected' : ''}" />
																	</c:forEach>
																</form:select>
																<form:button
																		class="uk-button uk-button-primary uk-button-small">save</form:button>
															</div>
														</div>
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
				<ul class="uk-tab">
					<li><a href="#">Sort By <span class="uk-margin-small-left"
							uk-icon="icon: triangle-down"></span></a>
						<div uk-dropdown="mode: click">
							<ul class="uk-nav uk-dropdown-nav">
								<li><a
									href="${pageContext.request.contextPath}/sort?order=username">Author</a></li>
								<li><a
									href="${pageContext.request.contextPath}/sort?order=priority">Priority</a></li>
								<li><a
									href="${pageContext.request.contextPath}/sort?order=status">Status</a></li>
								<li><a
									href="${pageContext.request.contextPath}/sort?order=dateOpened">Date
										Opened</a></li>
								<li><a
									href="${pageContext.request.contextPath}/sort?order=title">Title</a></li>
								<li><a
									href="${pageContext.request.contextPath}/sort?order=lastUpdated">Last
										Updated</a></li>
							</ul>
						</div></li>
				</ul>
				<div uk-slider="finite: true">
					<div class="uk-slider-container">
						<div class="uk-position-relative uk-visible-toggle" tabindex="-1">

							<ul
								class="uk-slider-items uk-child-width-1-5@s uk-child-width-1-4@">
								<c:forEach items="${unAssignedTickets }" var="ticket">
									<li class="uk-padding-small">
										<div id = "bor">
											<h3>${ticket.title }</h3>
											<h5>Not yet assigned</h5>
											<hr>
											<h4><span class="uk-text-light">Author: </span> ${ticket.username }</h4>
											<p>Category: ${ticket.category }</p>
											<p>Status: ${ticket.status }</p>
											<p>Priority: <span class="uk-text-light" style="color: ${ticket.priorityColour};">${ticket.priority }</span></p>
											<p>
												Opened:<br> ${ticket.formDateOpen }
											</p>
										 <a class="uk-button uk-button-default"
										href="#my${ticket.ticketID }" uk-toggle>Open</a>
										<hr> <a class="uk-button uk-button-default"
										href="${pageContext.request.contextPath}/pickUp?id=${ticket.ticketID }">Pick
											Up</a>
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
													<form:form class="uk-form-horizontal uk-margin-small" method="post"
														action="${pageContext.request.contextPath}/ChangeTicketStatus"
														modelAttribute="ticket">
														<label class="uk-form-label" for="status">Status:</label>
														<div class="uk-form-controls">
															<div class="uk-inline">
																<form:select class="uk-input uk-form-width-medium uk-margin-small-right" id="status" path="status">
																		<c:forEach items="${statusList}" var="status">
																			<form:option value="${status }"
																				selected="${status == ticket.status ? 'selected' : ''}" />
																		</c:forEach>
																</form:select>
																<form:button
																		class="uk-button uk-button-primary uk-button-small">save</form:button>
															</div>
														</div>
														<form:hidden path="ticketID" value="${ticket.ticketID }" />
														<form:hidden path="username" value="${ticket.username }" />
														<form:hidden path="assignee" value="${ticket.assignee }" />
														<form:hidden path="title" value="${ticket.title }" />
													</form:form>
													<form:form class="uk-form-horizontal uk-margin-small" method="post"
														action="${pageContext.request.contextPath}/AssignTicket"
														modelAttribute="ticket">
														<label class="uk-form-label" for="assignee">Assigned To:</label>
														<div class="uk-form-controls">
															<div class="uk-inline">
																<form:select class="uk-input uk-form-width-medium uk-margin-small-right" id="assignee" path="assignee">
																	<c:forEach items="${employeeList}" var="assignee">
																		<form:option value="${assignee.username}" />
																	</c:forEach>
																</form:select>
																<form:button
																		class="uk-button uk-button-primary uk-button-small">save</form:button>
															</div>
														</div>
														<form:hidden path="ticketID" value="${ticket.ticketID }" />
														<form:hidden path="username" value="${ticket.username }" />
														<form:hidden path="title" value="${ticket.title }" />
													</form:form>
													<form:form class="uk-form-horizontal uk-margin-small" method="post"
														action="${pageContext.request.contextPath}/ChangeTicketPriority"
														modelAttribute="ticket">
														<label class="uk-form-label" for="priority">Priority:</label>
														<div class="uk-form-controls">
															<div class="uk-inline">
																<form:select class="uk-input uk-form-width-medium uk-margin-small-right" id="priority" path="priority">
																	<c:forEach items="${priorityList}" var="priority">
																		<form:option value="${priority }"
																			selected="${priority == ticket.priority ? 'selected' : ''}" />
																	</c:forEach>
																</form:select>
																<form:button
																		class="uk-button uk-button-primary uk-button-small">save</form:button>
															</div>
														</div>
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
			<li>
				<form:form action="${pageContext.request.contextPath}/createTicket/?redirectUrl=employeeHomePage" modelAttribute="ticket">
					<div class="uk-modal-body">
						<h1 class="uk-text-lead uk-text-light uk-margin-medium-bottom">Please fill the information below to create a new ticket</h1>
							<div class="uk-margin">
								<label class="uk-form-label" for="title">Ticket Title: </label>
								<div class="uk-form-controls">
									<div class="uk-inline">
										<form:input class="uk-input uk-form-width-large" id="title"
											type="text" path="title" required="true" />
									</div>
								</div>
							</div>
							
							<div class="uk-margin">
								<label class="uk-form-label" for="description">Description: </label>
								<div class="uk-form-controls">
									<div class="uk-inline">
										<form:textarea class="uk-input uk-form-width-large" id="description"
											 rows="4" cols="50" path="description" required="true" />
									</div>
								</div>
							</div>
							
							<div class="uk-margin">
								<label class="uk-form-label" for="description">Priority: </label>
								<div class="uk-form-controls">
									<div class="uk-inline">
										<form:select class="uk-input uk-form-width-large" id="priority" path="priority" required="true">
											<form:option value="" disabled="true" selected="true">Select a priority level</form:option>
											<c:forEach items="${priorityList}" var="priority">
												<form:option value="${priority}" />
											</c:forEach>
										</form:select>
									</div>
								</div>
							</div>
							
							<div class="uk-margin-large-bottom">
								<label class="uk-form-label" for="category">Category: </label>
								<div class="uk-form-controls">
									<div class="uk-inline">
										<form:select class="uk-input uk-form-width-large" id="category" path="category" required="true">
											<form:option value="" disabled="true" selected="true">Select a category</form:option>
											<c:forEach items="${categoryList}" var="category">
												<form:option value="${category}" />
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
				</li>
			</ul>


	</div>


</body>
</html>
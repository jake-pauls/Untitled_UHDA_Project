<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<!-- FontAwesome CDN for Slack Icon -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
	integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA=="
	crossorigin="anonymous" />
<!-- UIKit CSS/JS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/uikit@3.5.9/dist/css/uikit.min.css" />
<link rel="stylesheet" href="css/homepage.css" />
<script
	src="https://cdn.jsdelivr.net/npm/uikit@3.5.9/dist/js/uikit.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/uikit@3.5.9/dist/js/uikit-icons.min.js"></script>
<meta charset="ISO-8859-1">
<script src="<c:url value="/js/jquery-3.5.1.min.js" />"></script>
<script src="<c:url value="/js/HardwareManagement_gpo_20.js" />"></script>
<title>Employee</title>
</head>
<body>
	<div class="uk-container uk-container-large">

		<c:if test="${slackSuccessNotification != null}">
			<div class="uk-alert-success uk-margin-small-top" uk-alert>
				<a class="uk-alert-close" uk-close></a>
				<p>${slackSuccessNotification}</p>
			</div>
		</c:if>

		<div class="uk-clearfix" id="top_margin_small">
			<div class="uk-float-right uk-grid-small" uk-grid>
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
				<!-- Situational styling for Slack button based on user association -->
				<c:choose>
					<c:when test="${slackErrorNotification != null}">
						<div>
							<a class="uk-icon-button uk-button-danger uk-margin-large-left"
								id="slack_override" uk-icon="ratio: 2.5" href=""
								uk-tooltip="${slackTooltip}"><i class="fab fa-slack fa-lg"></i></a>
						</div>
					</c:when>
					<c:otherwise>
						<div>
							<a class="uk-icon-button uk-button-primary uk-margin-large-left"
								id="slack_override" uk-icon="ratio: 2.5" href=""
								uk-tooltip="${slackTooltip}"><i class="fab fa-slack fa-lg"></i></a>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<ul class="uk-tab" uk-tab>
			<li class="uk-active"><a href="#">Assigned Tickets</a></li>
			<li><a href="#">Available Tickets</a></li>
			<li><a href="#">Create Ticket</a></li>
			<li><a href="#">Available Hardware</a></li>
			<li><a href="#">Assigned Hardware</a></li>
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
				<p class="uk-text-large">
					<c:choose>
						<c:when test="${empty topPriorityTickets}">
							<span class="uk-text-bold">Top Priority</span>
							<br />
							<span class="uk-text-meta">Oh no! There's nothing to show
								here!</span>
						</c:when>
						<c:otherwise>
							<span class="uk-text-bold">Top Priority</span>
							<br />
							<span class="uk-text-meta">Your assigned tickets with the
								highest priority.</span>
						</c:otherwise>
					</c:choose>
				<p>
				<div uk-slider="finite: true">
					<div class="uk-slider-container">
						<div class="uk-position-relative uk-visible-toggle" tabindex="-1">

							<ul
								class="uk-slider-items uk-child-width-1-3@s uk-child-width-1-2@">
								<c:forEach items="${topPriorityTickets }" var="ticket">
									<li class="uk-padding-small">
										<div id="bor">
											<h3 class="title-overflow">${ticket.title }</h3>
											<h5>Assigned to: ${ticket.assignee }</h5>
											<hr>
											<h4>
												<span class="uk-text-light">Author: </span>
												${ticket.username }
											</h4>
											<p>Category: ${ticket.category }</p>
											<p>Status: ${ticket.status }</p>
											<p>
												Priority: <span class="uk-text-light"
													style="color: ${ticket.priorityColour};">${ticket.priority }</span>
											</p>
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
													<form:form class="uk-form-horizontal uk-margin-small"
														method="post"
														action="${pageContext.request.contextPath}/ChangeTicketStatus"
														modelAttribute="ticket">
														<label class="uk-form-label" for="status">Status:</label>
														<div class="uk-form-controls">
															<div class="uk-inline">
																<form:select
																	class="uk-input uk-form-width-medium uk-margin-small-right"
																	id="status" path="status">
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
													</form:form>
													<form:form class="uk-form-horizontal uk-margin-small"
														method="post"
														action="${pageContext.request.contextPath}/AssignTicket"
														modelAttribute="ticket">
														<label class="uk-form-label" for="assignee">Assigned
															To:</label>
														<div class="uk-form-controls">
															<div class="uk-inline">
																<form:select
																	class="uk-input uk-form-width-medium uk-margin-small-right"
																	id="assignee" path="assignee">
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
													</form:form>
													<form:form class="uk-form-horizontal uk-margin-small"
														method="post"
														action="${pageContext.request.contextPath}/ChangeTicketPriority"
														modelAttribute="ticket">
														<label class="uk-form-label" for="priority">Priority:</label>
														<div class="uk-form-controls">
															<div class="uk-inline">
																<form:select
																	class="uk-input uk-form-width-medium uk-margin-small-right"
																	id="priority" path="priority">
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

													<h3>Comments</h3>
													<hr>
													<div class="outer_wrapper">
														<div class="comment_container">
															<ul class="uk-list">

																<c:choose>
															<c:when test="${empty ticket.comments}">
																<p id="no_comments_message">There are no comments at the moment</p>
															</c:when>
															<c:otherwise>
																<c:forEach items="${ticket.comments }" var="comment">
																<li class ="comment_organ">
																	<div class="comment">
																		
																			<p id ="comment_author"><i>${comment.author }</i></p> 
																			<p id ="comment_text">${comment.value }</p>
																			<p id ="comment_date">${comment.formattedDateCreated }</p>
																			<c:if
																				test="${loggedInUser.username == comment.author}">
																				<a
																					href="${pageContext.request.contextPath}/deleteComment/?id=${comment.commentId}&redirectUrl=employeeHomePage">
																					<em>delete</em>
																				</a>
																			</c:if>
																		
																	</div>

																</li>
															</c:forEach>
															</c:otherwise>
															</c:choose>
															</ul>
														</div>
													</div>
													<hr>
													<div class="create_comment_container">
													<form:form
														action="${pageContext.request.contextPath}/createComment/?redirectUrl=employeeHomePage"
														method="post" modelAttribute="comment">
														<table>
															<tr>
																<td><form:textarea path="value" required="true" /></td>
																<td><form:button
																		class="uk-button uk-button-primary uk-button-small">Comment</form:button></td>
															</tr>
														</table>
														<form:hidden path="ticketId" value="${ticket.ticketID }" />
														<form:hidden path="author" value="${ticket.assignee }" />

													</form:form>
													</div>
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
					<p class="uk-text-large">
						<c:choose>
							<c:when test="${empty assignedTickets}">
								<span class="uk-text-bold">All Tickets</span>
								<br />
								<span class="uk-text-meta">Oh no! You don't have any
									assigned tickets yet!</span>
							</c:when>
							<c:otherwise>
								<span class="uk-text-bold">All Tickets</span>
								<br />
								<span class="uk-text-meta">Your collection of assigned
									tickets.</span>
							</c:otherwise>
						</c:choose>
					<p>
					<ul class="uk-tab">
						<li><a href="#">Sort By <span
								class="uk-margin-small-left" uk-icon="icon: triangle-down"></span></a>
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
					<table class="uk-table uk-table-divider uk-table-striped">
						<thead>
							<tr>
								<th>Title</th>
								<th>Author</th>
								<th>Assigned To</th>
								<th>Status</th>
								<th>Priority</th>
								<th>Opened</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${assignedTickets}" var="ticket">
								<tr>
									<td>${ticket.title }</td>
									<td>${ticket.username }</td>
									<td>${ticket.assignee }</td>
									<td>${ticket.status }</td>
									<td style="color: ${ticket.priorityColour};">${ticket.priority }</td>
									<td>${ticket.formDateOpen }</td>
									<td><a class="uk-button uk-button-default"
										href="#my${ticket.ticketID }" uk-toggle>Open</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<c:forEach items="${assignedTickets }" var="ticket">
						<div id="my${ticket.ticketID }" uk-modal>
							<div class="uk-modal-dialog">
								<button class="uk-modal-close-default" type="button" uk-close></button>
								<div class="uk-modal-header">
									<h2 class="uk-modal-title">${ticket.title }</h2>
								</div>
								<div class="uk-modal-body">
									<p>${ticket.description }</p>
									<form:form class="uk-form-horizontal uk-margin-small"
										method="post"
										action="${pageContext.request.contextPath}/ChangeTicketStatus"
										modelAttribute="ticket">
										<label class="uk-form-label" for="status">Status:</label>
										<div class="uk-form-controls">
											<div class="uk-inline">
												<form:select
													class="uk-input uk-form-width-medium uk-margin-small-right"
													id="status" path="status">
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
									<form:form class="uk-form-horizontal uk-margin-small"
										method="post"
										action="${pageContext.request.contextPath}/AssignTicket"
										modelAttribute="ticket">
										<label class="uk-form-label" for="assignee">Assigned
											To:</label>
										<div class="uk-form-controls">
											<div class="uk-inline">
												<form:select
													class="uk-input uk-form-width-medium uk-margin-small-right"
													id="assignee" path="assignee">
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
									<form:form class="uk-form-horizontal uk-margin-small"
										method="post"
										action="${pageContext.request.contextPath}/ChangeTicketPriority"
										modelAttribute="ticket">
										<label class="uk-form-label" for="priority">Priority:</label>
										<div class="uk-form-controls">
											<div class="uk-inline">
												<form:select
													class="uk-input uk-form-width-medium uk-margin-small-right"
													id="priority" path="priority">
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
										<form:hidden path="title" value="${ticket.title }" />
									</form:form>
									<c:if test="${ticket.category == 'Hardware'}">
										<form:form class="uk-form-horizontal uk-margin-small"
											method="post"
											action="${pageContext.request.contextPath}/AssignHardware"
											modelAttribute="hardware">
											<br>
											<p>Hardware Requests:</p>

											<label class="uk-form-label" for="hardwareName">Hardware
												Name:</label>
											<div class="uk-form-controls">
												<div class="uk-inline">
													<form:select
														class="uk-input uk-form-width-medium uk-margin-small-right"
														id="hardwareName" path="hardwareName">
														<form:option value="" />
														<c:forEach items="${hardwareNameList }" var="hardwareName">
															<form:option
																value="${hardwareName.hardwareTypeDescription }" />
														</c:forEach>
													</form:select>
													<br> <br>
													<form:button
														class="uk-button uk-button-primary uk-button-small">Assign Hardware</form:button>
												</div>
											</div>
											<form:hidden path="status" value="Assigned" />
											<form:hidden path="usernameAssignedTo"
												value="${ticket.username }" />
										</form:form>
									</c:if>

								</div>
								<div class="uk-modal-footer uk-text-right">
									<button class="uk-button uk-button-default uk-modal-close"
										type="button">Cancel</button>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</li>
			<!-- Available ticket display -->
			<li>
				<div uk-slider="finite: true">
					<p class="uk-text-large">
						<c:choose>
							<c:when test="${empty mostRecentUnassignedTickets}">
								<span class="uk-text-bold">Most Recent</span>
								<br />
								<span class="uk-text-meta">Oh no! There's nothing to show
									here!</span>
							</c:when>
							<c:otherwise>
								<span class="uk-text-bold">Most Recent</span>
								<br />
								<span class="uk-text-meta">Brand new tickets.</span>
							</c:otherwise>
						</c:choose>
					<p>
					<div class="uk-slider-container">
						<div class="uk-position-relative uk-visible-toggle" tabindex="-1">
							<ul
								class="uk-slider-items uk-child-width-1-3@s uk-child-width-1-2@">
								<c:forEach items="${mostRecentUnassignedTickets }" var="ticket">
									<li class="uk-padding-small">
										<div id="bor">
											<h3 class="title-overflow">${ticket.title }</h3>
											<h5>Not yet assigned</h5>
											<hr>
											<h4>
												<span class="uk-text-light">Author: </span>
												${ticket.username }
											</h4>
											<p>Category: ${ticket.category }</p>
											<p>Status: ${ticket.status }</p>
											<p>
												Priority: <span class="uk-text-light"
													style="color: ${ticket.priorityColour};">${ticket.priority }</span>
											</p>
											<p>
												Opened:<br> ${ticket.formDateOpen }
											</p>
											<a class="uk-button uk-button-default"
												href="#my${ticket.ticketID }" uk-toggle>Open</a>
											<hr>
											<a class="uk-button uk-button-default"
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
													<form:form class="uk-form-horizontal uk-margin-small"
														method="post"
														action="${pageContext.request.contextPath}/ChangeTicketStatus"
														modelAttribute="ticket">
														<label class="uk-form-label" for="status">Status:</label>
														<div class="uk-form-controls">
															<div class="uk-inline">
																<form:select
																	class="uk-input uk-form-width-medium uk-margin-small-right"
																	id="status" path="status">
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
													</form:form>
													<form:form class="uk-form-horizontal uk-margin-small"
														method="post"
														action="${pageContext.request.contextPath}/AssignTicket"
														modelAttribute="ticket">
														<label class="uk-form-label" for="assignee">Assigned
															To:</label>
														<div class="uk-form-controls">
															<div class="uk-inline">
																<form:select
																	class="uk-input uk-form-width-medium uk-margin-small-right"
																	id="assignee" path="assignee">
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
													</form:form>
													<form:form class="uk-form-horizontal uk-margin-small"
														method="post"
														action="${pageContext.request.contextPath}/ChangeTicketPriority"
														modelAttribute="ticket">
														<label class="uk-form-label" for="priority">Priority:</label>
														<div class="uk-form-controls">
															<div class="uk-inline">
																<form:select
																	class="uk-input uk-form-width-medium uk-margin-small-right"
																	id="priority" path="priority">
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
					<p class="uk-text-large">
						<c:choose>
							<c:when test="${empty unAssignedTickets}">
								<span class="uk-text-bold">All Tickets</span>
								<br />
								<span class="uk-text-meta">There aren't any unassigned
									tickets.</span>
							</c:when>
							<c:otherwise>
								<span class="uk-text-bold">All Tickets</span>
								<br />
								<span class="uk-text-meta">Your collection of available
									tickets.</span>
							</c:otherwise>
						</c:choose>
					<p>
					<ul class="uk-tab">
						<li><a href="#">Sort By <span
								class="uk-margin-small-left" uk-icon="icon: triangle-down"></span></a>
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
					<table class="uk-table uk-table-divider uk-table-striped">
						<thead>
							<tr>
								<th>Title</th>
								<th>Author</th>
								<th>Assigned To</th>
								<th>Status</th>
								<th>Priority</th>
								<th>Opened</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${unAssignedTickets}" var="ticket">
								<tr>
									<td>${ticket.title }</td>
									<td>${ticket.username }</td>
									<td>${ticket.assignee }</td>
									<td>${ticket.status }</td>
									<td style="color: ${ticket.priorityColour};">${ticket.priority }</td>
									<td>${ticket.formDateOpen }</td>
									<td><a class="uk-button uk-button-default"
										href="#my${ticket.ticketID }" uk-toggle>Open</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<c:forEach items="${unAssignedTickets }" var="ticket">
						<div id="my${ticket.ticketID }" uk-modal>
							<div class="uk-modal-dialog">
								<button class="uk-modal-close-default" type="button" uk-close></button>
								<div class="uk-modal-header">
									<h2 class="uk-modal-title">${ticket.title }</h2>
								</div>
								<div class="uk-modal-body">
									<p>${ticket.description }</p>
									<form:form class="uk-form-horizontal uk-margin-small"
										method="post"
										action="${pageContext.request.contextPath}/ChangeTicketStatus"
										modelAttribute="ticket">
										<label class="uk-form-label" for="status">Status:</label>
										<div class="uk-form-controls">
											<div class="uk-inline">
												<form:select
													class="uk-input uk-form-width-medium uk-margin-small-right"
													id="status" path="status">
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
									<form:form class="uk-form-horizontal uk-margin-small"
										method="post"
										action="${pageContext.request.contextPath}/AssignTicket"
										modelAttribute="ticket">
										<label class="uk-form-label" for="assignee">Assigned
											To:</label>
										<div class="uk-form-controls">
											<div class="uk-inline">
												<form:select
													class="uk-input uk-form-width-medium uk-margin-small-right"
													id="assignee" path="assignee">
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
									<form:form class="uk-form-horizontal uk-margin-small"
										method="post"
										action="${pageContext.request.contextPath}/ChangeTicketPriority"
										modelAttribute="ticket">
										<label class="uk-form-label" for="priority">Priority:</label>
										<div class="uk-form-controls">
											<div class="uk-inline">
												<form:select
													class="uk-input uk-form-width-medium uk-margin-small-right"
													id="priority" path="priority">
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
										<form:hidden path="title" value="${ticket.title }" />
									</form:form>
								</div>
								<div class="uk-modal-footer uk-text-right">
									<button class="uk-button uk-button-default uk-modal-close"
										type="button">Cancel</button>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</li>
			<li><form:form
					action="${pageContext.request.contextPath}/createTicket/?redirectUrl=employeeHomePage"
					modelAttribute="ticket">
					<div class="uk-modal-body">
						<h1 class="uk-text-lead uk-text-light uk-margin-medium-bottom">Please
							fill the information below to create a new ticket</h1>
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
							<label class="uk-form-label" for="description">Description:
							</label>
							<div class="uk-form-controls">
								<div class="uk-inline">
									<form:textarea class="uk-input uk-form-width-large"
										id="description" rows="4" cols="50" path="description"
										required="true" />
								</div>
							</div>
						</div>

						<div class="uk-margin">
							<label class="uk-form-label" for="priority">Priority: </label>
							<div class="uk-form-controls">
								<div class="uk-inline">
									<form:select class="uk-input uk-form-width-large" id="priority"
										path="priority" required="true">
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
									<form:select class="uk-input uk-form-width-large" id="category"
										path="category" required="true">
										<form:option value="" disabled="true" selected="true">Select a category</form:option>
										<c:forEach items="${categoryList}" var="category">
											<form:option value="${category}" />
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>

						<sec:authorize access="isAuthenticated()">
							<form:input type="hidden" path="username"
								value="${loggedInUser.username}" />
						</sec:authorize>

						<div class="uk-modal-footer uk-text-right">
							<button class="uk-button uk-button-default uk-modal-close"
								type="button">Cancel</button>
							<button class="uk-button uk-button-primary" type="submit">Create
								Ticket</button>
						</div>
					</div>
				</form:form></li>
			<li>
				<!-- Available Hardware List -->
				<table class="uk-table uk-table-divider">
					<thead>
						<tr>
							<th>Hardware Name</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="hardwareTypes" items="${hardwareTypeList }">

							<tr>
								<td><input
									id="row-reference-${hardwareTypes.hardwareTypeID}-hardwareTypeDesc"
									class="uk-input hardwareTypeDes" type="text"
									value="${hardwareTypes.hardwareTypeDescription}" disabled /></td>
								<!-- commented out the delete button for this form as there are referential integrity issues
								<td><form:form method="post"
										action="${pageContext.request.contextPath}/DeleteHardwareType?redirectUrl=employeeHomePage"
										modelAttribute="hardwareType">
										<form:input type="hidden" path="hardwareTypeID"
											value="${hardwareTypes.hardwareTypeID}" />
										<button class="uk-button uk-button-danger" type="submit"
											name="deletehardware">Delete</button>
									</form:form></td>
									 -->
							</tr>

						</c:forEach>
						<tr id="newTableRow" style="display: none;">

							<form:form method="post"
								action="${pageContext.request.contextPath}/CreateHardwareType?redirectUrl=employeeHomePage"
								modelAttribute="hardwareType">
								<td width="75%"><form:input
										class="uk-input newHardware hardwareTypeDescription"
										path="hardwareTypeDescription" type="text" /></td>
								<td><form:button class="uk-button uk-button-primary">Submit</form:button>
									<button class="uk-button uk-button-default" type="button"
										onclick="cancelAddHardware()">Cancel</button></td>
							</form:form>

						</tr>
					</tbody>
				</table>
				<div uk-margin>
					<button class="uk-button uk-button-secondary uk-margin-left"
						id="addNewHardware" onclick="addNewTableRow()">Add New
						Hardware</button>
				</div>

			</li>
			<li>
				<!-- List of the Assigned Hardware -->
				<table class="uk-table uk-table-divider">
					<thead>
						<tr>
							<th>Hardware ID</th>
							<th>Hardware Type</th>
							<th>Status</th>
							<th>User Assigned</th>
							<th>Date Assigned</th>
							<th>Date Returned</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="hardwareAssigned" items="${hardwareAssignedList }">

							<tr>
								<td><input
									id="row-reference-${hardwareAssigned.hardwareID}-hardwareID"
									class="uk-input hardwareID" type="text"
									value="${hardwareAssigned.hardwareID}" disabled /></td>
								<td><input
									id="row-reference-${hardwareAssigned.hardwareID}-hardwareName"
									class="uk-input hardwareTypeName" type="text"
									value="${hardwareAssigned.hardwareName}" disabled /></td>
								<td><input
									id="row-reference-${hardwareAssigned.status}-status"
									class="uk-input status" type="text"
									value="${hardwareAssigned.status}" disabled /></td>
								<td><input
									id="row-reference-${hardwareAssigned.hardwareID}-usernameAssignedTo"
									class="uk-input ${hardwareAssigned.hardwareID} usernameAssignedTo"
									type="text" value="${hardwareAssigned.usernameAssignedTo}"
									disabled /></td>
								<td><input
									id="row-reference-${hardwareAssigned.hardwareID}-dateAssigned"
									class="uk-input dateAssigned" type="text"
									value="${hardwareAssigned.dateAssigned}" disabled /></td>
								<td><input
									id="row-reference-${hardwareAssigned.hardwareID}-dateReturned"
									class="uk-input dateReturned" type="text"
									value="${hardwareAssigned.dateReturned}" disabled /></td>
							</tr>
							<tr>
								<td><form:form method="post"
										action="${pageContext.request.contextPath}/ReturnHardware?redirectUrl=employeeHomePage"
										modelAttribute="assignedHardware">
										<form:input type="hidden" path="hardwareID"
											value="${hardwareAssigned.hardwareID}" />
										<button class="uk-button uk-button-danger" type="submit"
											name="returnHardware">Hardware Returned</button>
									</form:form></td>
								<td><form:form method="post"
										action="${pageContext.request.contextPath}/LostHardware?redirectUrl=employeeHomePage"
										modelAttribute="assignedHardware">
										<form:input type="hidden" path="hardwareID"
											value="${hardwareAssigned.hardwareID}" />
										<button class="uk-button uk-button-danger" type="submit"
											name="returnHardware">Mark Hardware Lost</button>
									</form:form></td>
								<!-- edited out the reassign hardware as could not get this feature to work -->
								<!--  
								<td><button class="uk-button uk-button-default"
										onclick="editTableRow('${hardwareAssigned.hardwareID}')">Reassign Hardware</button></td>
								
								<td><form:form method="post"
										action="${pageContext.request.contextPath}/ReassignHardware?redirectUrl=employeeHomePage"
										modelAttribute="assignedHardware">
										<form:hidden id="hidden-hardwareID" path="hardwareID" value="" />
										<form:hidden id="hidden-usernameAssignedTo"
											path="usernameAssignedTo" value="" />

										<div uk-margin>
											<form:button class="uk-button uk-button-primary"
												id="button-reference${hardwareAssigned.hardwareID}" type="submit"
												name="saveReasignHardware" disabled="true">Save Changes</form:button>
										</div>

									</form:form></td>-->
							</tr>

						</c:forEach>
					</tbody>
				</table>
			</li>
		</ul>


	</div>


</body>
</html>
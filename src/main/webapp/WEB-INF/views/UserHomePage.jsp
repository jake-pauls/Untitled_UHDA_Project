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
<!-- FontAwesome CDN for Slack Icon -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"
	integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA=="
	crossorigin="anonymous" />
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

		<c:if test="${slackSuccessNotification != null}">
			<div class="uk-alert-success uk-margin-small-top" uk-alert>
				<a class="uk-alert-close" uk-close></a>
				<p>${slackSuccessNotification}</p>
			</div>
		</c:if>

		<c:if test="${slackErrorNotification != null}">
			<div class="uk-alert-danger uk-margin-small-top" uk-alert>
				<a class="uk-alert-close" uk-close></a>
				<p>${slackErrorNotification}</p>
			</div>
		</c:if>

		<div class="uk-clearfix" id="top_margin_small">
			<div class="uk-float-right uk-grid-small" uk-grid>

				<c:if test="${loggedInUser.role == 'admin' }">
					<div>
						<sec:authorize access="hasRole('ADMIN')">
							<a href="${pageContext.request.contextPath}/AdminUserManagement"
								class="uk-icon-button" uk-icon="icon: users; ratio: 1.25"
								uk-tooltip="Admin Page"></a>
						</sec:authorize>
					</div>
				</c:if>

				<c:if
					test="${loggedInUser.role == 'employee' || loggedInUser.role == 'admin'}">
					<div>
						<sec:authorize access="hasAnyRole('ADMIN', 'EMPLOYEE')">
							<a href="${pageContext.request.contextPath}/employeeHomePage"
								class="uk-icon-button" uk-icon="icon: tag; ratio: 1.25"
								uk-tooltip="Employee Page"></a>
						</sec:authorize>
					</div>
				</c:if>

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
			<li class="uk-active"><a href="#">My Tickets</a></li>
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
				<div uk-slider="finite: true">
					<p class="uk-text-large">
						<c:choose>
							<c:when test="${empty mostRecentTickets}">
								<span class="uk-text-bold">Featured Tickets</span>
								<br />
								<span class="uk-text-meta">Oh no! There's nothing to show
									here!</span>
							</c:when>
							<c:otherwise>
								<span class="uk-text-bold">Featured Tickets</span>
								<br />
								<span class="uk-text-meta">Your most recently created
									tickets.</span>
							</c:otherwise>
						</c:choose>
					<p>
					<div class="uk-slider-container">
						<div class="uk-position-relative uk-visible-toggle" tabindex="-1">
							<ul
								class="uk-slider-items uk-child-width-1-3@s uk-child-width-1-2@">

								<c:forEach items="${mostRecentTickets }" var="ticket">
									<li class="uk-padding-small">
										<div id="bor">
											<h3 class="title-overflow">${ticket.title }</h3>
											<c:choose>
												<c:when test="${ticket.assignee != null }">
													<h5>Assigned to: ${ticket.assignee }</h5>
												</c:when>
												<c:otherwise>
													<h5>Not yet assigned</h5>
												</c:otherwise>
											</c:choose>

											<hr>
											<h4>
												<span class="uk-text-light">Author: </span>${ticket.username }</h4>
											<p>
												Assigned To:
												<c:choose>
													<c:when test="${ticket.assignee == null}">
														<span class="uk-text-warning">Unassigned</span>
													</c:when>
													<c:otherwise>
														${ticket.assignee}
													</c:otherwise>
												</c:choose>
											</p>
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
							<c:when test="${empty createdList}">
								<span class="uk-text-bold">Featured Tickets</span>
								<br />
								<span class="uk-text-meta">Oh no! You haven't created any
									tickets yet!</span>
							</c:when>
							<c:otherwise>
								<span class="uk-text-bold">All Tickets</span>
								<br />
								<span class="uk-text-meta">Your collection of created
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
							<c:forEach items="${createdList}" var="ticket">
								<tr>
									<td>${ticket.title }</td>
									<td>${ticket.username }</td>
									<td><c:choose>
											<c:when test="${ticket.assignee != null }">
												<h5>${ticket.assignee }</h5>
											</c:when>
											<c:otherwise>
												<h5>Not yet assigned</h5>
											</c:otherwise>
										</c:choose></td>
									<td>${ticket.status }</td>
									<td style="color: ${ticket.priorityColour};">${ticket.priority }</td>
									<td>${ticket.formDateOpen }</td>
									<td><a class="uk-button uk-button-default"
										href="#my${ticket.ticketID }" uk-toggle>Open</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<c:forEach items="${createdList }" var="ticket">
						<div id="my${ticket.ticketID }" uk-modal>
							<div class="uk-modal-dialog">
								<button class="uk-modal-close-default" type="button" uk-close></button>
								<div class="uk-modal-header">
									<h2 class="uk-modal-title">${ticket.title }</h2>
								</div>
								<div class="uk-modal-body">
									<p>${ticket.description }</p>
									<c:if test="${ticket.assignee != null }">
										<h3>Comments</h3>
										<hr>
										<div class="outer_wrapper">
											<div class="comment_container">
												<ul class="uk-list">
													<c:choose>
														<c:when test="${empty ticket.comments}">
															<p>There are no comments at the moment</p>
														</c:when>
														<c:otherwise>
															<c:forEach items="${ticket.comments }" var="comment">
																<li class="comment_organ">
																	<div class="comment">

																		<p id="comment_author">
																			<i>${comment.author }</i>
																		</p>
																		<p id="comment_text">${comment.value }</p>
																		<p id="comment_date">${comment.formattedDateCreated }</p>
																		<c:if
																			test="${loggedInUser.username == comment.author}">
																			<a
																				href="${pageContext.request.contextPath}/deleteComment/?id=${comment.commentId}&redirectUrl=UserHomePage">
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
										<div class="create_comment_container">
											<form:form
												action="${pageContext.request.contextPath}/createComment/?redirectUrl=UserHomePage"
												method="post" modelAttribute="comment">
												<table>
													<tr>
														<td><form:textarea path="value" required="true" /></td>
														<td><form:button
																class="uk-button uk-button-primary uk-button-small">Comment</form:button></td>
													</tr>
												</table>
												<form:hidden path="ticketId" value="${ticket.ticketID }" />
												<form:hidden path="author" value="${ticket.username }" />

											</form:form>
										</div>
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
			<!-- Create ticket display -->
			<li><form:form
					action="${pageContext.request.contextPath}/createTicket/?redirectUrl=UserHomePage"
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
									<form:textarea class="uk-input uk-form-width-large required"
										id="description" rows="4" cols="50" path="description"
										required="true" />
								</div>
							</div>
						</div>

						<div class="uk-margin-large-bottom">
							<label class="uk-form-label" for="category">Category: </label>
							<div class="uk-form-controls">
								<div class="uk-inline">
									<form:select class="uk-input uk-form-width-large required"
										id="category" path="category" required="true">
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
							<button class="uk-button uk-button-primary" type="submit"
								onclick="checkRequiredInputs(this)">Create Ticket</button>
						</div>
					</div>
				</form:form></li>
		</ul>



	</div>
</body>
</html>
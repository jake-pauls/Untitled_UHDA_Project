<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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


		<ul class="uk-tab" uk-tab>
			<li class="uk-active"><a href="#">Assigned Tickets</a></li>
			<li><a href="#">Available Tickets</a></li>
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
										<div>
											<h3>${ticket.title }</h3>
											<h4>${ticket.username }</h4>
											<p>Status: ${ticket.status }</p>
											<p>Priority: ${ticket.priority }</p>
											<p>
												Opened:<br> ${ticket.formDateOpen }
											</p>
										</div> <a class="uk-button uk-button-default"
										href="#my${ticket.ticketID }" uk-toggle>Open</a>

										<div id="my${ticket.ticketID }" uk-modal>
											<div class="uk-modal-dialog">
												<button class="uk-modal-close-default" type="button"
													uk-close></button>
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
										<div>
											<h3>${ticket.title }</h3>
											<h4>${ticket.username }</h4>
											<p>Status: ${ticket.status }</p>
											<p>Priority: ${ticket.priority }</p>
											<p>
												Opened:<br> ${ticket.formDateOpen }
											</p>
										</div> <a class="uk-button uk-button-default"
										href="#my${ticket.ticketID }" uk-toggle>Open</a>

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
		</ul>

	</div>


</body>
</html>
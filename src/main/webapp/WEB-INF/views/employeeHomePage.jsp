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
		<nav class="uk-navbar-container" uk-navbar>
			<div class="uk-navbar-left">
				<ul class="uk-navbar-nav">
					<li class="uk-active"><a href="">My Tickets</a></li>
					<li class="uk-nav"><a href="">Active Tickets</a></li>
					<li class="uk-nav"><a href="">Create Ticket</a></li>
				</ul>
			</div>
		</nav>

		<div uk-slider="finite: true">
			<div class="uk-slider-container">
				<div class="uk-position-relative">

					<ul
						class="uk-slider-items uk-child-width-1-5@s uk-child-width-1-4@ uk-grid">
						<c:forEach items="${assignedTickets }" var="ticket">
							<li>
								<div>
									<h3>Title: ${ticket.title }</h3>
									<p>Status: ${ticket.status }</p>
									<p>Priority: ${ticket.priority }</p>
								</div> <a class="uk-button uk-button-default" href="#my${ticket.ticketID }"
								uk-toggle>Open</a>

								<div id="my${ticket.ticketID }" uk-modal>
									<div class="uk-modal-dialog">
										<button class="uk-modal-close-default" type="button" uk-close></button>
										<div class="uk-modal-header">
											<h2 class="uk-modal-title">${ticket.title }</h2>
										</div>
										<div class="uk-modal-body">
											<p>${ticket.description }</p>
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
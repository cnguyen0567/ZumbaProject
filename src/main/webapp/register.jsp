<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="f" uri="jakarta.tags.fmt"%>
<%-- <%@ include file="relativeURL" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link href="css/styles.css" rel="stylesheet"> -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<title>Register</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Your Custom Script for Tooltips -->
<script defer>
        const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]');
        const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl));
    </script>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #F4F4F4;
	color: #333;
	margin: 0;
	padding: 0;
}

.container {
	width: 80%;
	margin: auto;
	padding: 20px;
	background: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
}

h2 {
	color: #007BFF;
	text-align: center;
}
/* Basic styling for the tooltip-triggering words */
.tooltip-trigger {
	color: #007BFF;
	text-decoration: none;
	transition: color 0.3s ease, text-shadow 0.3s ease;
	cursor: pointer;
}
/* Light up effect on hover */
.tooltip-trigger:hover {
	color: #0056B3;
	text-shadow: 0 0 5px rgba(0, 123, 255, 0.7);
}

.section {
	margin-bottom: 50px;
	padding: 15px;
	border: 1px solid #ddd;
	border-radius: 8px;
	background: #FAFAFA;
}

.section h3 {
	color: #333;
	margin-top: 0;
}

form button {
	padding: 10px 20px;
	border: none;
	border-radius: 4px;
	color: #fff;
	background-color: #007BFF;
	cursor: pointer;
}

form button:hover {
	background-color: #0056B3;
}

.messages {
	text-align: center;
}

.messages p {
	padding: 10px;
	border-radius: 4px;
	font-size: 16px;
	font-weight: bold;
}

.error {
	color: red;
	background-color: #fdd;
}

.success {
	color: green;
	background-color: #dfd;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Rohit Zumba</h2>

		<div class="section">
			<h3>Register a new account</h3>
			<c:if test="${not empty successCreateParticipantMessage}">
				<div class="alert alert-secondary" role="alert">${successCreateParticipantMessage}</div>
				<div> <p> <a href="${pageContext.request.contextPath}/index.jsp">Go to sign in</a></p></div>
			</c:if>
			<form action="FrontController" method="post">
				<input type="hidden" name="type" value="registerParticipant">
				<input type="hidden" name="sourcePage" value="register.jsp">
				<div class="form-floating mb-3">
				<input type="text" class="form-control" id="participantName"
							name="participantName" value="${param.participantName}" required>
							
					 <label
						for="participantName" class="form-label">Name</label>
				</div>
				<div class="form-floating mb-3">
					<input type="email" class="form-control" id="participantEmail"
						placeholder="name@example.com" name="participantEmail"
						value="${param.participantEmail}" required> <label
						for="participantEmail" class="form-label">Email address</label>
				</div>
				<div class="form-floating">
					<input type="password" class="form-control"
						id="participantPassword" placeholder="password"
						name="participantPassword" value="${param.participantPassword}"
						required> <label for="participantPassword" class="form-label">Password</label>
				</div>
				<br>
				<button type="submit" class="btn btn-primary">Register</button>

			</form>
		</div>

	</div>
</body>
</html>
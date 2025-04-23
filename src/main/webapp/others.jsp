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
<title>AdminDashboard</title>
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
		<h2>Others</h2>

		<div class="section">
			<h3>Your Batches</h3>


		</div>

		<div class="section">
			<h3>Update Batch</h3>
			<c:if test="${not empty successUpdateBatchMessage}">
				<div class="alert alert-secondary" role="alert">${successUpdateBatchMessage}</div>
				<%-- <p class="success">${successMessage}</p> --%>
			</c:if>
			<form action="FrontController" method="post">
				<input type="hidden" name="type" value="updateBatch">
				<!-- ID -->
				<div class="row mb-3">
					<label for="batcId" class="col-sm-2 col-form-label">ID</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="batchId"
							name="batchId" value="${param.batchId}" required>
					</div>
				</div>
				<!-- Name -->
				<div class="row mb-3">
					<label for="batchName" class="col-sm-2 col-form-label">Name</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="batchName"
							name="batchName" value="${param.batchName}">
					</div>
				</div>
				<!-- batchStartDate -->
				<div class="row mb-3">
					<label for="batchStartDate" class="col-sm-2 col-form-label">Start
						Date</label>
					<div class="col-sm-10">
						<input type="date" class="form-control" id="batchStartDate"
							name="batchStartDate" value="${param.batchStartDate}">
					</div>
				</div>
				<!-- batchEndDate -->
				<div class="row mb-3">
					<label for="batchEndDate" class="col-sm-2 col-form-label">End
						Date</label>
					<div class="col-sm-10">
						<input type="date" class="form-control" id="batchEndDate"
							name="batchEndDate" value="${param.batchEndDate}">
					</div>
				</div>
				<!-- batchStartTime -->
				<div class="row mb-3">
					<label for="batchStartTime" class="col-sm-2 col-form-label">Start
						Time</label>
					<div class="col-sm-10">
						<input type="time" class="form-control" id="batchStartTime"
							name="batchStartTime" value="${param.batchStartTime}">
					</div>
				</div>
				<!-- batchDuration -->
				<div class="row mb-3">
					<label for="batchDuration" class="col-sm-2 col-form-label">Duration
						(hours)</label>
					<div class="col-sm-10">
						<input type="number" min="1" max="24" step=".25"
							class="form-control" id="batchDuration" name="batchDuration"
							value="${param.batchDuration}">
					</div>
				</div>
				<!-- batchSizeLimit -->
				<div class="row mb-3">
					<label for="batchSizeLimit" class="col-sm-2 col-form-label">Participations Limit</label>
					<div class="col-sm-10">
						<input type="number" min="1" max="200"
							class="form-control" id="batchSizeLimit" name="batchSizeLimit"
							value="${param.batchSizeLimit}">
					</div>
				</div>
				<!-- SubmitButton -->
				<button type="submit" class="btn btn-primary">Update Batch</button>
			</form>
		</div>
		<div class="section">
			<h3>Delete Batch + All Enrollment for Batch</h3>
			<c:if test="${not empty successDeleteBatchMessage}">
				<div class="alert alert-secondary" role="alert">${successDeleteBatchMessage}</div>
				<%-- <p class="success">${successMessage}</p> --%>
			</c:if>
			<form action="FrontController" method="post">
				<input type="hidden" name="type" value="deleteBatch">
				<!-- ID -->
				<div class="row mb-3">
					<label for="batcId" class="col-sm-2 col-form-label">ID</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="batchId"
							name="batchId" value="${param.batchId}" required>
					</div>
				</div>
				<!-- SubmitButton -->
				<button type="submit" class="btn btn-primary">Delete Batch</button>
			</form>
		</div>
		<div class="section">
			<h3>Update Participant</h3>
			<c:if test="${not empty successMessage}">
				<div class="alert alert-secondary" role="alert">${successMessage}</div>
				<%-- <p class="success">${successMessage}</p> --%>
			</c:if>
			<form action="FrontController" method="post">
				<input type="hidden" name="type" value="updateParticipant">
				<!-- Name -->
				<div class="row mb-3">
					<label for="participantName" class="col-sm-2 col-form-label">Name</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="participantName"
							name="participantName" value="${param.participantName}" required>
					</div>
				</div>
				<!-- participantEmail -->
				<div class="row mb-3">
					<label for="participantEmail" class="col-sm-2 col-form-label">Email</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" id="participantEmail"
							name="participantEmail" value="${param.participantEmail}">
					</div>
				</div>
				<!-- participantPassword -->
				<div class="row mb-3">
					<label for="participantPassword" class="col-sm-2 col-form-label">Password</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="participantPassword"
							name="participantPassword" value="${param.participantPassword}">
					</div>
				</div>
				<!-- isAdmin -->
				<div class="row mb-3">
					<label for="isAdmin" class="col-sm-2 col-form-label">Is Admin</label>
					<div class="col-sm-10">
						<input type="checkbox" id="isAdmin" name="isAdmin" value="${param.isAdmin}">
					</div>
				</div>
				<!-- UpdateParticipantButton -->
				<button type="submit" class="btn btn-primary">Update Participant</button>
			</form>

		</div>
	</div>
</body>
</html>
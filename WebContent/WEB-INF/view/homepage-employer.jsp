<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home page</title>
<style>
.error {
	color: red
}

.success {
	color: green
}
</style>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-customer-style.css" />
</head>
<body>
	Welcome ${firstname}
	<c:url var="updateProfile" value="../employer/showUpdateProfile">
		<c:param name="empId" value="${id}" />
	</c:url>
	<a href="${updateProfile}">My Profile</a>
    <a href="../employer/logout">Logout</a>
	<br>
	<br>
	<p class="success">${successmessage}</p>
	<br>
	<div id="wrapper">
		<div id="header">
			<h2>List of users</h2>
		</div>
		<div id="container">
			<div id="content">
	<c:url var="manageJobs" value="../employer/listJobs">
		<c:param name="empId" value="${id}" />
	</c:url>
	<a href="${manageJobs}">Create and manage jobs</a>
				<!-- put the new add job button -->
		
			</div>
		</div>
	</div>



</body>
</html>
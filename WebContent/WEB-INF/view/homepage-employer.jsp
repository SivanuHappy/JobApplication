<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Employer Home page</title>
<style>
</style>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/jobapp-style.css" />
</head>
<body>
	<header>
		<h1>Welcome to job portal</h1>
	</header>
	<div id="profilehead">
		Welcome ${firstname}
		<c:url var="updateProfile" value="../employer/showUpdateProfile">
			<c:param name="empId" value="${id}" />
		</c:url>
		<a href="${updateProfile}">My Profile</a> 
		<a href="../employer/logout">Logout</a>
	</div>
	<br>
	<p class="success">${successmessage}</p>
	<div id="container">
		<div id="content" align="center">
			<h3>Create and manage jobs using the below link</h3>
			<c:url var="manageJobs" value="../employer/listJobs">
				<c:param name="empId" value="${id}" />
			</c:url>
			<a href="${manageJobs}">Click here</a>
		</div>
	</div>
</body>
</html>
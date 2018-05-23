<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Manage jobs</title>
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
		<c:url var="homepage" value="../applicant/getHomePage">
			<c:param name="appId" value="${id}" />
			<c:param name="firstname" value="${firstname}" />
		</c:url>
		<a href="${homepage}">Home page</a> <a href="../applicant/logout">Logout</a>
	</div>
	<p class="success">${message}</p>
	<h2 id="jobdetails">${jobdetails}</h2>
	<c:forEach items="${theJob}" var="job">
Title: <c:out value="${job.title}" />
		<br>
Company: <c:out value="${job.company}" />
		<br>
Skills: <c:out value="${job.skills}" />
		<br>
Salary: <c:out value="${job.salary}" />
		<br>
City: <c:out value="${job.city}" />
		<br>
State: <c:out value="${job.state}" />
		<br>
		<form method="get" action="../applicant/applyJob">
			<input type="hidden" id="text1" name="appId" value="${id}"> 
			<input type="hidden" id="text2" name="firstname" value="${firstname}">
			<input type="hidden" id="text3" name="jobid" value="${jobId}">
			<button id="button1" type="submit">Apply</button>
		</form>
	</c:forEach>
</body>
</html>
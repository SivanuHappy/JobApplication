<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Applicant Home page</title>
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
		<c:url var="updateProfile" value="../applicant/showUpdateProfile">
			<c:param name="appId" value="${id}" />
		</c:url>
		<a href="${updateProfile}">My Profile</a> 
		<a href="../applicant/logout">Logout</a>
	</div>
	<p class="success">${successmessage}</p>
	<div id="container">
		<div id="content" align="left">
			<div id="searchjobs"><h3>Search for jobs</h3></div>
			<form method="get" action="../applicant/searchJobs">
				<input type="text" id="text1" name="searchString">
				<input type="hidden" id="text2" name="appId" value="${id}">
				<button id="button1" type="submit">Search</button>
			</form>
		</div>
	</div>
	<br>
	<p class="error">${message}</p>
	<br>
	<table>
		<tr>
			<td><c:if test="${theJobs == null}">
					<c:out value=""/>
				</c:if></td>
		</tr>
		<c:if test="${theJobs != null}">
			<tr>
				<th>Title</th>
				<th>Company</th>
				<th>Skills</th>
				<th>Salary</th>
				<th>City</th>
				<th>State</th>
			</tr>
			<td><c:forEach var="tempJob" items="${theJobs}">
					<tr>
						<td><c:url var="jobTitle" value="../applicant/showJobPage">
								<c:param name="id" value="${tempJob.id}" />
								<c:param name="appId" value="${id}" />
							</c:url> <a href="${jobTitle}">${tempJob.title}</a></td>
						<td>${tempJob.company}</td>
						<td>${tempJob.skills}</td>
						<td>${tempJob.salary}</td>
						<td>${tempJob.city}</td>
						<td>${tempJob.state}</td>
					</tr>
				</c:forEach>
		</c:if>
	</table>
</body>
</html>
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
	<c:url var="homepage" value="../employer/getHomePage">
		<c:param name="empId" value="${id}" />
		<c:param name="firstname" value="${firstname}" />
	</c:url>
	<a href="${homepage}">Home page</a>
	&nbsp;
	<a href="../employer/logout">Logout</a>
	</div>
	<br/>
	<input type="button" value="Add Jobs"
		onclick="window.location.href='../employer/showFormForAddJob?empId=' + ${id} ; return false;"
		class="jobs"/>
	<br/>
	<br/>
	<table>
		<tr>
			<th>Title</th>
			<th>Company</th>
			<th>Skills</th>
			<th>Salary</th>
			<th>City</th>
			<th>State</th>
		</tr>
		<tr>
			<td><c:if test="${job == null}">
					<c:out value="No jobs are created yet" />
				</c:if></td>
		</tr>
		<c:if test="${job != null}">
			<c:forEach var="tempJob" items="${job}">
				<tr>
					<td>${tempJob.title}</td>
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
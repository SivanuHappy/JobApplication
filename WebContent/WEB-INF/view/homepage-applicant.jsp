<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home page</title>
</head>
<body>
	Welcome ${firstname}
	<c:url var="updateProfile" value="../applicant/showUpdateProfile">
		<c:param name="appId" value="${id}" />
	</c:url>
	<a href="${updateProfile}">My Profile</a>
	<a href="../applicant/logout">Logout</a>
	<br>
	<p class="success">${successmessage}</p>
	<br>
	<h2>Search for jobs</h2>
	<form method="get" action="../applicant/searchJobs">
		<input type="text" id="text1" name="searchString">
		<button id="button1" type="submit">Search</button>
	</form>
	<br>
	<p>${message}</p>
	<br>
	<table>

		<tr>
			<td><c:if test="${theJobs == null}">
					<c:out value="" />
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
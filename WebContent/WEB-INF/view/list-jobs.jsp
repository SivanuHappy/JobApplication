
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Manage jobs</title>
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
<input type="button" value="Add Jobs"
	onclick="window.location.href='../employer/showFormForAddJob?empId=' + ${id} ; return false;"
	class="add-button" />

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
			</c:if>
		</td>
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
</html>
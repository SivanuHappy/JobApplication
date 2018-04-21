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
<body>
	<p>${message}</p>
	<p>${jobdetails}</p>
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
		<input type="button" value="Apply" onclick="window.location.href='../applicant/applyJob' ; return false;" />
	</c:forEach>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>List all users from Restful web service</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-customer-style.css" />
</head>
<table>
	<tr>
		<th>User Name</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Role</th>
	</tr>
	<tr>
		<td><c:if test="${users == null}">
				<c:out value="List is empty" />
			</c:if>
		</td>
	</tr>
	<c:if test="${users != null}">
		<c:forEach var="tempUsers" items="${users}">
			<tr>
				<td>${tempUsers.username}</td>
				<td>${tempUsers.firstName}</td>
				<td>${tempUsers.lastName}</td>
				<td>${tempUsers.role}</td>
			</tr>
		</c:forEach>
	</c:if>
</table>
</html>
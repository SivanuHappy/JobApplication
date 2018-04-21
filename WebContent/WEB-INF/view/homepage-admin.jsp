<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Admin Home Page</title>
</head>
<body>
	Welcome ${firstname}
	<br>
	<a href="../wsclient/listUsers">Get All Users</a>
	<br>
	<h2>Search users by id</h2>
	<form method="get" action="../wsclient/getUserById">
		<input type="text" id="id" name="searchString">
		<button id="button1" type="submit">Search</button>
	</form>
	<table>
		<tr>
			<th>User Name</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Role</th>
		</tr>
		<tr>
		</tr>
		<tr>
			<td>${user.username}</td>
			<td>${user.firstName}</td>
			<td>${user.lastName}</td>
			<td>${user.role}</td>
		</tr>
	</table>
</body>

</html>
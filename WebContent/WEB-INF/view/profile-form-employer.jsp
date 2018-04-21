<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
    <a href="../employer/logout">Logout</a>
	<p>Provide your details below.</p>
	<form:form action="updateProfile" modelAttribute="employer" method="POST">
		<!--  associate data with employer id -->
		<form:hidden path="id"/>
		<p class="success">${successmessage}</p>
		<p>${empmessage}</p>
		<table>
			<tbody>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Update Profile" class="save"></td>
				</tr>
				<tr>
					<td><form:input type="hidden" path="username" value="${userName}"/></td>
				</tr>
				<tr>
					<td><form:input type="hidden" path="user.id" value="${userId}"/></td>
				</tr>
				<tr>
					<td><label>First name:</label></td>
					<td><form:input path="firstName" /></td>
				</tr>
				<tr>
					<td><label>Last name:</label></td>
					<td><form:input path="lastName" /></td>
				</tr>
				<tr>
					<td><label>Email address:</label></td>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<td><label>Phone number:</label></td>
					<td><form:input path="phone" /></td>
				</tr>
				<tr>
					<td><label>Company:</label></td>
					<td><form:input path="company" /></td>
				</tr>
				<tr>
					<td><label>Position:</label></td>
					<td><form:input path="position" /></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</body>
</html>

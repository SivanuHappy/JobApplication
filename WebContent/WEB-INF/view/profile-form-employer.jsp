<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Employer profile page</title>
<style></style>
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
		<a href="${homepage}">Home page</a> &nbsp; <a
			href="../employer/logout">Logout</a>
	</div>
	<div id="profile">
		<h3>Provide your details below.</h3>
	</div>
	<form:form action="updateProfile" modelAttribute="employer" method="POST">
		<form:hidden path="id" />
		<p class="success">${empmessage}</p>
		<table>
			<tbody>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Update Profile" class="save"></td>
				</tr>
				<tr>
					<td><form:input type="hidden" path="username"
							value="${userName}" /></td>
				</tr>
				<tr>
					<td><form:input type="hidden" path="user.id" value="${userId}" /></td>
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
					<td><form:errors path="email" cssClass="error" /></td>
				</tr>
				<tr>
					<td><label>Phone number:</label></td>
					<td><form:input path="phone" /></td>
					<td><form:errors path="phone" cssClass="error" /></td>
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

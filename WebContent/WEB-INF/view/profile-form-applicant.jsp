<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>
Applicant profile page</title>
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
	<a href="${homepage}">Home page</a>
	<a href="../applicant/logout">Logout</a>
	</div>
	<div id="profile">
	<h3>Provide your details below.</h3>
	</div>
	<form:form action="updateProfile" modelAttribute="applicant" method="POST">
		<form:hidden path="id" />
		<p class="success">${appmessage}</p>
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
				<tr><td><b>Personal details</b></td></tr>
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
					<td><label>Address Line 1:</label></td>
					<td><form:input path="address1" /></td>
				</tr>
				<tr>
					<td><label>Address Line 2:</label></td>
					<td><form:input path="address2" /></td>
				</tr>
				<tr>
					<td><label>City :</label></td>
					<td><form:input path="city" /></td>
				</tr>
				<tr>
					<td><label>State :</label></td>
					<td><form:input path="state" /></td>
				</tr>
				<tr>
					<td><label>Zipcode:</label></td>
					<td><form:input path="zip" /></td>
				</tr>
				<tr>
					<td><label>Country:</label></td>
					<td><form:input path="country" /></td>
				</tr>
				<tr><td><b>Education details</b></td></tr>
				<tr>
					<td><label>Institution:</label></td>
					<td><form:input path="institution" /></td>
				</tr>
				<tr>
					<td><label>Degree:</label></td>
					<td><form:input path="degree" /></td>
				</tr>
				<tr>
					<td><label>Major:</label></td>
					<td><form:input path="major" /></td>
				</tr>
				<tr><td><b>Experience</b></td></tr>
				<tr>
					<td><label>Company:</label></td>
					<td><form:input path="company" /></td>
				</tr>
				<tr>
					<td><label>Designation:</label></td>
					<td><form:input path="designation" /></td>
				</tr>
				<tr>
					<td><label>Skills:</label></td>
					<td><form:textarea path="skills" /></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</body>
</html>

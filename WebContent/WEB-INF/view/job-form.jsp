<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Add job</title>
<style>
</style>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/jobapp-style.css" />
</head>
<body>
	<header>
		<h1>Welcome to job portal</h1>
	</header>
	<div>
		<h2>Add Jobs</h2>
	</div>
	<div id="container">
		<form:form action="addJob" modelAttribute="job" method="POST">
			<form:hidden path="employer.id" value="${empId}" />
			<p class="success">${successmessage}</p>
			<table>
				<tbody>
					<tr>
						<td><form:input type="hidden" path="employer.id"
								value="${empId}" /></td>
					</tr>
					<tr>
						<td><label>Job title: </label></td>
						<td><form:input path="title" /></td>
						<td><form:errors path="title" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Company:</label></td>
						<td><form:input path="company" /></td>
						<td><form:errors path="company" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Skills:</label></td>
						<td><form:textarea path="skills" /></td>
						<td><form:errors path="skills" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Annual Salary(in dollars):</label></td>
						<td><form:input path="salary" /></td>
						<td><form:errors path="salary" cssClass="error" />
					</tr>
					<tr>
						<td><label>City:</label></td>
						<td><form:input path="city" /></td>
						<td><form:errors path="city" cssClass="error" />
					</tr>
					<tr>
						<td><label>State:</label></td>
						<td><form:input path="state" /></td>
						<td><form:errors path="state" cssClass="error" />
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Add" class="save"></td>
					</tr>
				</tbody>
			</table>
		</form:form>
	</div>
</body>
</html>
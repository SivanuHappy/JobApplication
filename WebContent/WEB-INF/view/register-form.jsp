<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html.
<html>
<head>
<title>Registration page</title>
<style>
</style>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/jobapp-style.css"/>
</head>
<body>
<header>
   <h1>Welcome to job portal</h1>
</header>
	<div id="wrapper">
		<div id="header">
			<h2 align="center">New applicant/employer? Register here</h2>
		</div>
	</div>
	<div id="container">
		<form:form action="registerUser" modelAttribute="user" method="POST">
			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><form:input path="firstName" /></td>
						<td><form:errors path="firstName" cssClass="error" /></td>
					<tr>
						<td><label>Last name:</label></td>
						<td><form:input path="lastName" /></td>
						<td><form:errors path="lastName" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>User Name:</label></td>
						<td><form:input path="username" /></td>
						<td><form:errors path="username" cssClass="error" /></td>
					    <td><p class="error">${message}</p><td>
					</tr>

					<tr>
						<td><label>Role:</label></td>
						<td><form:select path="role">
								<form:option value="Applicant" label="Applicant" />
								<form:option value="Employer" label="Employer" />
							</form:select></td>
						<td><form:errors path="role" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Password:</label></td>
						<td><form:password path="password" /></td>
						<td><form:errors path="password" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Confirm Password:</label></td>
						<td><form:password path="confirmpassword" /></td>
						<td><form:errors path="confirmpassword" cssClass="error" />
							<form:errors path="" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Register" class="save"></td>
					</tr>
				</tbody>
			</table>
		</form:form>
	</div>
</body>

</html>
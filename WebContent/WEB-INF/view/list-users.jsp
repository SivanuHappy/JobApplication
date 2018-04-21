<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>List of users</h2>
		</div>
		<div id="container">
			<div id="content">
				
				<table>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>User Name</th>
						<th>Role</th>
					</tr>
					<tr>
					<td>
					<c:if test="${users == null}">
					<c:out value="No users are created yet"/>
					</c:if>
					</td>
					</tr>		
					<c:if test="${users != null}">
					<c:forEach var="tempUser" items="${user}">
						<tr>
							<td>${tempUser.firstName}</td>
							<td>${tempUser.lastName}</td>
							<td>${tempUser.username}</td>
							<td>${tempUser.role}</td>
						</tr>
					</c:forEach>
					</c:if>
				</table>
			</div>
		</div>
	</div>


</body>
</html>
<html>
<head>
<title>Index Page</title>
<style>
</style>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/jobapp-style.css"/>
</head>
<body>
<header>
   <h1>Welcome to job portal</h1>
</header>
	<div id="row">
		<div class="lcolumn">
		<div id="header">
			<h2>New applicant/employer? Register here</h2>
		</div>
			<input type="button" value="Register"
				onclick="window.location.href='user/showFormForRegister' ; return false;"
				class="save" />
		</div>
		<div class="rcolumn">
		<div id="header">
			<h2>Already registered user?Login here</h2>
		</div>
			<input type="button" value="Login"
				onclick="window.location.href='login/showFormForLogin' ; return false;"
				class="save" />
		</div>		
	</div>	
</body>
</html>

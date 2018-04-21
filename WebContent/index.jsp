<html>
<head>
<title>Home Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
/* Create two equal columns that floats next to each other */
html, body{
	margin-left:15px; margin-right:15px; 
	padding:0px; 
	font-family:Verdana, Arial, Helvetica, sans-serif;
}
.lcolumn {
	height: 150px;
	width: 50%;
	float: left;
	border: 2px solid #CDCDCD;
    border-radius: 8px;
}
.rcolumn {
	height: 150px;
	width: 49.1%;
	float: right;
	border: 2px solid #CDCDCD;
    border-radius: 8px;
}
header, footer {
    padding: 1em;
    background-color: #72B752;
    clear: left;
    text-align: center;
}
.save {
	font-weight: bold;
	width: 130px; 
	padding: 5px 10px; 
	margin-top: 30px;
	background: #cccccc;
}
input {
	width: 250px;
	border: 1px solid #666; 
	border-radius: 5px; 
	padding: 4px; 
	margin-left: 80px;
	font-size: 16px;
}
label {
	font-size: 16px; 
	width: 100px; 
	display: block; 
	text-align: right;
	margin-left: 10px;
	margin-top: 8px;
	margin-bottom: 8px;
}
#header h2 {background-color: #ACDFF4;}
.lcolumn {background-color: #FFFFFF;}
.rcolumn {background-color: #FFFFFF;}

</style>
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

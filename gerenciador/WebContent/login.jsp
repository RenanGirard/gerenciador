<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body class="well">
	<div align="center">
		<h1>Login</h1>

		<form action="LoginController" method="post">
			<div class="input-group">
				<input type="text" name="user" class="form-control" /> 
				<input type="password" name="password" class="form-control"/>
				<button type="submit" class="btn btn-default">
				Login
				<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
			</button>
			</div>
		</form>
	</div>
</body>
</html>
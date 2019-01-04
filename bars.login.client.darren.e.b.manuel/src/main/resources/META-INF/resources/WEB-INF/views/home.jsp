<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Homepage</title>
</head>
<body style = "background-image: url(http://getwallpapers.com/wallpaper/full/c/f/d/293534.jpg)">


<!-- Header -->
<header class="masthead text-center">
	<div class="jumbotron">

		<h1 class="text-uppercase">User Account Management System</h1>
		<hr class="star-light">
		<h2 class="font-weight-light">Accenture Technology - Microservices Authentication Service</h2>
	</div>
</header>
	<br>
	<div class="container" style="margin-left: 35%">
	<c:if test = "${admin==true}">
		<a href="/users" class = "btn btn-lg btn-success" style="height: 100px; width: 450px; font-size: 50px " >Manage Accounts</a><br><br><br>
	</c:if>

		<a href="http://localhost:4200" class = "btn btn-lg btn-warning" style="height: 100px; width: 450px; font-size: 50px " >Open BARS</a><br><br><br>

		<c:if test = "${admin==false}">
			<a href="/users/${id}/edit" class = "btn btn-lg btn-success" style="height: 100px; width: 450px; font-size: 50px " >Edit My Account</a><br><br><br>
		</c:if>
		<a href="/logout" class = "btn btn-lg btn-primary" style="height: 100px; width: 450px; font-size: 50px " >Logout</a>
	</div>

	<br><br><br><br>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<!-- Footer -->
<style>
#footer {
    background-color:white;
    color:black;
    clear:both;
    text-align:center;
    padding:5px;
    border:1px solid #999999
}
</style>
</body>
</html>
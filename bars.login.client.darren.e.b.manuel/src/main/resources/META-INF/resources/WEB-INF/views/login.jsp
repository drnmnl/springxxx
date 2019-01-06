<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Homepage</title>
</head>
<body style = "background-image: url(http://getwallpapers.com/wallpaper/full/c/f/d/293534.jpg)">

<table id="content"
	style="min-height: 80px; height: 80px; width: 100%; border: 0px; margin: 0; padding: 0; bordercollapse: collapse; background-color: #6B8E23;">
	<tr style="height: 60px;">
		<td><img src="/accenture-technology.png" alt="Banner Image" width="300px" height = "50px"></td>
		<td align=right>
			<p style="font-family: Calibri, Garamond, Serif;">
				<font color="white"; ><B> Accenture Philippines Delivery Center </B></font>
			</p>
			<p style="font-family: Calibri, Garamond, Serif;">
				<font color="white";>Technology  Capability  Fundamentals for Java</font>
			</p>
		</td>
	</tr>
</table>
<br><br>

		<div class="container" style="margin: 0% 0% 0% 30%; width: 40%">
			<form class="form-signin" method="POST" action="/login-client-service/login-success">
				<h2 class="form-signin-heading" style="font-size: 40px; font-weight: bold">Authentication Service Account Management</h2>
				<h3>Please sign in</h3>
				<p>
					<label for="username" class="sr-only">Username</label>
					<input type="text" id="username" name="username" class="form-control" placeholder="Username" required autofocus>
				</p>
				<p>
					<label for="password" class="sr-only">Password</label>
					<input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
				</p>
				<button class="btn btn-lg btn-danger btn-block" type="submit">Sign in</button>

			</form>
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
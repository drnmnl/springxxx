<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- saved from url=(0053)https://getbootstrap.com/docs/3.3/examples/dashboard/ -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

  <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
  <link href="css/bootstrap.min.css" rel="stylesheet">

  <title>User Administration</title>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body style = "background-image: url(http://getwallpapers.com/wallpaper/full/c/f/d/293534.jpg)">

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/login-client-service/">ATCP Authentication Service Demo</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="/login-client-service/users/new"><strong>Add new user</strong></a></li>
            <li><a href="/login-client-service/logout">Log out</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <br><br>
    <div class="container-fluid" style="margin: 0% 15% 0% 15%">
      <div class="row">
        <div>
          <h1 class="page-header" align="center" style="font-size:45px; font-family: Verdana; color:lemonchiffon ">Dashboard for Administrator <strong>${admin}</strong></h1>
          <div class="table-responsive panel panel-primary">
            <table class="table table-striped">
              <thead style="font-size:22px">
                <tr>
                  <th style="text-align:center">Username</th>
                  <th style="text-align:center">First Name</th>
                  <th style="text-align:center">Last Name</th>
                  <th style="text-align:center">Role</th>
                  <th style="text-align:center" colspan="2">Admin Actions</th>
                </tr>
              </thead>
              <tbody style="font-size:20px; text-align: center">

              <c:forEach items="${users}" var="userObject">
                <tr>
                  <td>${userObject.userName}</td>
                  <td>${userObject.firstName}</td>
                  <td>${userObject.lastName}</td>
                  <td>${userObject.role}</td>
                    <td><a href="/login-client-service/users/${userObject.userId}/edit" class = "btn btn-warning btn-lg">Edit</a>
                    </td>
                  <td>
                      <form method="POST" action="/login-client-service/users/delete">
                    <input type = "hidden" name="_method" value = "DELETE" >
                    <input type = "hidden" name="deleteMe" value="${userObject.userId}">
                    <input type = "submit" class = "btn btn-danger btn-lg" value="DELETE">
                  </form></td>
                </tr>
              </c:forEach>


              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>


</body></html>
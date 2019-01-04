<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
  <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

  <title>User Administration</title>
  </head>

  <body style = "background-image: url(http://getwallpapers.com/wallpaper/full/c/f/d/293534.jpg)">

    <div class="container" style="color:whitesmoke; text-shadow: 1px 1px 1px black">
      <br>
      <div class="row" style=" width: 1000px; margin-left: auto; margin-right: auto;  ">
        <div class="col-md-4 mx-auto">
          <div class="myform form ">
            <div class=" mx-auto text-center">
              <h1 style="color:whitesmoke">
                Create New User
              </h1>
            </div>
            <form action="/users/new" method="POST">
              <div class="form-group">
                <label name="username" style="color:whitesmoke">Username</label>
                <input type="text" name="username"  class="form-control my-input" id="username"  placeholder="Username">
              </div>
              <div class="form-group">
                <label name="firstname" style="color:whitesmoke">First Name</label>
                <input type="text" name="firstname"  class="form-control my-input" id="firstname" placeholder="First Name">

              </div>
              <div class="form-group">
                <label name="lastname" style="color:whitesmoke">Last Name</label>
                <input type="text" name="lastname"  class="form-control my-input" id="lastname" placeholder="Last Name">
              </div>
              <div class="form-group">
                <label name="role" style="color:whitesmoke">Role</label>
                <input type="text" name="role"  class="form-control my-input" id="role" placeholder="ADMIN or USER only">
              </div>
              <div class="form-group">
                <label name="password"style="color:whitesmoke">Password</label>
                <input type="password" name="password"  class="form-control my-input" id="password">
              </div>

              <div class="text-center ">
                <button type="submit" class=" btn btn-block send-button btn-success tx-tfm">CREATE USER</button><br>
              </div>
            </form>
            <form method="GET" action="/users">
              <div class="text-center ">
                <button type="submit" class=" btn btn-block send-button btn-warning tx-tfm">Cancel</button>
              </div>

            </form>
          </div>
        </div>
      </div>
    </div>

</body></html>
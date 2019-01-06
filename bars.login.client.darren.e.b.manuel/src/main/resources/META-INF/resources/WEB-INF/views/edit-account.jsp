<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<body style="background-image: url(https://visme.co/blog/wp-content/uploads/2017/07/50-Beautiful-and-Minimalist-Presentation-Backgrounds-013.jpg)">
<div class="container">

    <br>
    <div class="row" style=" width: 1000px; margin-left: auto; margin-right: auto;  ">
        <div class="col-md-4 mx-auto">
            <div class="myform form ">
                <div class=" mx-auto text-center">
                        <h1 style="color:whitesmoke">
                            Edit User
                        </h1>
                </div>
                <form action="/login-client-service/users/${userObject.userId}/edit" method="POST" name="login">
                    <div class="form-group">
                        <label name="username" style="color:whitesmoke">Username</label>
                        <input type = "hidden" name="_method" value = "PUT" >
                        <input type="text" name="username"  class="form-control my-input" id="username"  value="${userObject.userName}">
                    </div>
                    <div class="form-group">
                        <label name="firstname" style="color:whitesmoke">First Name</label>
                        <input type = "hidden" name="_method" value = "PUT" >
                        <input type="text" name="firstname"  class="form-control my-input" id="firstname" value="${userObject.firstName}">

                    </div>
                    <div class="form-group">
                        <label name="lastname" style="color:whitesmoke">Last Name</label>
                        <input type = "hidden" name="_method" value = "PUT" >
                        <input type="text" name="lastname"  class="form-control my-input" id="lastname" value="${userObject.lastName}">
                    </div>
                    <div class="form-group">
                        <label name="role" style="color:whitesmoke">Role</label>
                        <input type = "hidden" name="_method" value = "PUT" >
                        <input type="text" name="role"  class="form-control my-input" id="role" value="${userObject.role}">

                    </div>
                    <div class="form-group">
                        <label name="password"style="color:whitesmoke">Password</label>
                        <input type = "hidden" name="_method" value = "PUT" >
                        <input type="password" name="password"  class="form-control my-input" id="password" value="${userObject.password}">
                    </div>

                    <div class="text-center ">
                        <button type="submit" class=" btn btn-block send-button btn-primary tx-tfm">Submit changes</button>
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
</body>
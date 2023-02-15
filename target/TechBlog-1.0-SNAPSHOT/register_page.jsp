<%--
  Created by IntelliJ IDEA.
  User: amrita
  Date: 05/02/23
  Time: 10:29 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>

    <%--css--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>

<%@include file="normal_navbar.jsp"%>
<main class="d-flex align-items-center banner-background" style="height: 90vh; padding-bottom: 60px;">
    <div class="container">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <div class="card">
                    <div class="card-header text-center bg-dark text-white">
                        <span class="fa fa-user-plus fa-2x"></span>
                        <h3 class="fw-bold mb-2 text-uppercase">Register</h3>
                    </div>

                    <div class="card-body" style="margin-top: 10px;">
                        <form action="registerServlet" method="post" id="reg-form">
                            <div class="form-group">
                                <label for="user_name">User name</label>
                                <input name="user_name" type="text" class="form-control" id="user_name" aria-describedby="emailHelp" placeholder="Enter name">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Email address</label>
                                <input name="user_email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Password</label>
                                <input name="user_password" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                            </div>
                            <div class="form-group">
                                <label for="gender">Select Gender</label><br>
                                <input type="radio" id="gender" name="gender" value="male"> Male
                                <input type="radio" id="gender" name="gender" style="margin-left: 10px;" value="female"> Female
                            </div>
                            <div class="form-group">
                                <textarea name="about" rows="1" class="form-control" placeholder="Enter something about yourself...."></textarea>
                            </div>
                            <div class="form-check">
                                <input name="check" type="checkbox" class="form-check-input" id="exampleCheck1" required>
                                <label class="form-check-label" for="exampleCheck1">agree to terms and conditions</label>
                            </div>
                            <div class="text-center" id="loader" style="display: none;">
                                <span class="fa fa-refresh fa-spin fa-2x "></span><br>
                                <h4>Please wait........</h4>
                            </div>
                            <div id="submitbtn" class="text-center" style="margin-top: 20px;">
                                <button type="submit" class="btn btn-dark">Register</button>
                            </div>

                        </form>
<%--                    </div>--%>

<%--                    <div class="card-footer"></div>--%>
                </div>
            </div>
        </div>
    </div>
    </div>
</main>

<%--javascript--%>
<%--<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>--%>
<%--<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>--%>
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
<%--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>--%>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
<script>
    $(document).ready(function ()
    {
        console.log("loaded..........")
        $('#reg-form').on('submit', function (event)
        {
            event.preventDefault();
            let form=new FormData(this);

            $("#submitbtn").hide();
            $("#loader").show();

            // send register servlet:
            $.ajax({
                url: "registerServlet",
                type: 'POST',
                data: form,
                success: function (data, textStatus, jqXHR)
                {
                    console.log(data)
                    $("#submitbtn").show();
                    $("#loader").hide();
                    if(data.trim()=="done")
                    {
                        swal("Registered successfully!", "Redirecting to login page....", "success")
                            .then((value) => {
                                window.location="login_page.jsp"
                            });
                    }
                    else
                    {
                        $("#submitbtn").show();
                        $("#loader").hide();
                        swal("Something went wrong!", "Please try again......", "error")
                    }


                },
                error: function (jqXHR, textStatus, errorThrown)
                {
                    $("#submitbtn").show();
                    $("#loader").hide();
                    swal("Something went wrong!", "Please try again......", "error")
                },
                processData: false,
                contentType: false
            });
        });
    });
</script>

</body>
</html>

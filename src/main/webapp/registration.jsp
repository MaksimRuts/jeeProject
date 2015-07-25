<%@ page import="by.gsu.epamlab.controller.ControllerConst" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <form action="registration" method="post" role="form" class="form-horizontal">
            <div class="form-group text-center">
                <div class="col-sm-offset-4 col-sm-4">
                    <h3>Register new user</h3>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <input class="form-control" type="text" value="" required="required" placeholder="Login" name="<%= ControllerConst.Fields.LOGIN %>">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <input class="form-control" type="password" value="" required="required" placeholder="Password" name="<%= ControllerConst.Fields.PASSWORD %>">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <input class="form-control" type="text" value="" required="required"  placeholder="Name" name="<%= ControllerConst.Fields.NAME %>">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <button class="btn btn-default col-sm-12" name="<%= ControllerConst.Actions.ACTION %>" value="<%= ControllerConst.Actions.REGISTER %>">Register</button>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <a class="btn btn-default col-sm-12" href="<%= ControllerConst.Pages.LOGIN %>">Back to login page</a>
                </div>
            </div>
        </form>
    </div>

</body>
</html>
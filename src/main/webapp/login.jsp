<%@ page import="by.gsu.epamlab.controller.ControllerConst" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <form action="login" method="post" role="form" class="form-horizontal">
            <div class="col-sm-offset-4 col-sm-4">
                <div class="form-group text-center">
                    <h3>Login to system</h3>
                </div>
            </div>
            <div class="col-sm-offset-4 col-sm-4">
                <div class="form-group">
                    <input class="form-control" type="text" id="login_field" value="" required="required" placeholder="Login" name="<%= ControllerConst.Fields.LOGIN %>">
                </div>
            </div>
            <div class="col-sm-offset-4 col-sm-4">
                <div class="form-group">
                    <input class="form-control" type="password" id="password_field" value="" required="required" placeholder="Password" name="<%= ControllerConst.Fields.PASSWORD %>">
                </div>
            </div>
            <div class="col-sm-offset-4 col-sm-4">
                <div class="form-group">
                    <button class="btn btn-default col-sm-12" name="<%= ControllerConst.Actions.ACTION %>" value="<%= ControllerConst.Actions.LOGIN %>">Login</button>
                </div>
            </div>
            <div class="col-sm-offset-4 col-sm-4">
                <div class="form-group">
                    <a class="btn btn-default col-sm-12" href="<%= ControllerConst.Pages.REGISTRATION %>">Register new user</a>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
<%@ page import="by.gsu.epamlab.controller.ControllerConst" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <style>
        <%@ include file="resources/login.css"%>
    </style>
</head>
<body>
<div class="registration_panel">
    <h3>Registration form</h3>
    <div class="registration_field">
        <form action="registration" method="post">
            <table>
                <tr>
                    <td><label for="login_field">Login:</label></td>
                    <td><input class="login_input" type="text" name="<%= ControllerConst.Fields.LOGIN %>" id="login_field" value="" required="required"><br></td>
                </tr>
                <tr>
                    <td><label for="password_field">Password:</label></td>
                    <td><input class="login_input" type="password" name="<%= ControllerConst.Fields.PASSWORD %>" id="password_field" value="" required="required"><br></td>
                </tr>
                <tr>
                    <td><label for="name_field">Name:</label></td>
                    <td><input class="login_input" type="text" name="<%= ControllerConst.Fields.NAME %>" id="name_field" value="" required="required"><br></td>
                </tr>
                <tr>
                    <td colspan="2"><button name="<%= ControllerConst.Actions.ACTION %>" value="<%= ControllerConst.Actions.REGISTER %>">Register</button></td>
                </tr>
                <tr>
                    <td colspan="2"><a href="<%= ControllerConst.Pages.LOGIN %>">Back to login page</a></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
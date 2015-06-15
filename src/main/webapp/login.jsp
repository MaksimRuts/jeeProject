<%@ page import="by.gsu.epamlab.controller.ControllerConst" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <style>
        <%@ include file="login.css"%>
    </style>
</head>
<body>
    <div class="registration_panel">
        <p><b>Login to system</b></p>
        <div class="registration_field">
            <form action="login" method="post">
                <table>
                    <tr>
                        <td><label for="login_field">Login:</label></td>
                        <td><input type="text" name="<%= ControllerConst.PAGE_FIELD_LOGIN %>" id="login_field" value="" required="required"><br></td>
                    </tr>
                    <tr>
                        <td><label for="password_field">Password:</label></td>
                        <td><input type="password" name="<%= ControllerConst.PAGE_FIELD_PASSWORD %>" id="password_field" value="" required="required"><br></td>
                    </tr>
                    <tr>
                        <td colspan="2"><button name="<%= ControllerConst.PAGE_FIELD_ACTION %>" value="<%= ControllerConst.PAGE_FIELD_ACTION_LOGIN %>">Login</button></td>
                    </tr>
                    <tr>
                        <td colspan="2"><button name="<%= ControllerConst.PAGE_FIELD_ACTION %>" value="<%= ControllerConst.PAGE_FIELD_ACTION_REGISTER %>">Register</button></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>
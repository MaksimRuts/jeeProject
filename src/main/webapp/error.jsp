<%@ page import="by.gsu.epamlab.controller.ControllerConst" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error page</title>
    <style>
        <%@ include file="login.css"%>
    </style>
</head>
<body>
    <div class="registration_panel">
        <h3>Error</h3>
        <div class="registration_field">
            <c:choose>
                <c:when test="${not empty errorMessage}">
                    <c:out value="${errorMessage}"/>
                </c:when>
                <c:otherwise>
                    <c:out value="<%= ControllerConst.Errors.UNSUPPORTED %>"/>
                </c:otherwise>
            </c:choose>
            <br/>
            <c:choose>
                <c:when test="${not empty backPage}">
                    <a href="${backPage}">Back</a>
                </c:when>
                <c:otherwise>
                    <a href="logout">Back to login page</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>

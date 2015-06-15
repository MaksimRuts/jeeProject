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
        <p><b>Error</b></p>
        <div class="registration_field">
            <c:choose>
                <c:when test="${not empty errorMessage}">
                    <c:out value="${errorMessage}"/>
                </c:when>
                <c:otherwise>
                    <c:out value="<%= ControllerConst.ERROR_UNSUPPORTED %>"/>
                </c:otherwise>
            </c:choose>
            <br/>
            <a href="login.jsp">Return to login page</a>
        </div>
    </div>
</body>
</html>

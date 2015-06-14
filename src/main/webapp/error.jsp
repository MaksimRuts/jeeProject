<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error page</title>
    <style>
        <%@ include file="main.css"%>
    </style>
</head>
<body>
    <div class="information">
        <h3>Error</h3>
        <c:out value="${errorMessage}"/>
        <br/>
        <a href="login.jsp">Return to login page</a>
    </div>
</body>
</html>

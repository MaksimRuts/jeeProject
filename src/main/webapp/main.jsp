<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main page</title>
    <style>
        <%@ include file="login.css"%>
    </style>
</head>
<body>
    <div class="registration_panel">
        <p><b>Main page</b></p>
        <div class="registration_field">
            <c:if test="${not empty infoMessage}">
                <c:out value="${infoMessage}"/><br/>
            </c:if>
            <c:out value="Hello, ${username}"/> <br/>
            <a href="login.jsp">Logout</a>
        </div>
    </div>
</body>
</html>

<%@ page import="by.gsu.epamlab.controller.ControllerConst" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error page</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="col-sm-offset-4 col-sm-4 panel panel-warning">
            <div class="text-danger text-center ">
                <h3>Error</h3>
            </div>
            <div class="text-danger text-center">
                <c:choose>
                    <c:when test="${not empty errorMessage}">
                        <c:out value="${errorMessage}"/>
                    </c:when>
                    <c:otherwise>
                        <c:out value="<%= ControllerConst.Errors.UNSUPPORTED %>"/>
                    </c:otherwise>
                </c:choose>

            </div>
            <br/>
            <c:if test="${not empty backPage}">
                <a class="btn btn-default col-sm-8 col-sm-offset-2" href="${backPage}">Back</a>
            </c:if>
            <a class="btn btn-default col-sm-8 col-sm-offset-2" href="logout">Back to login page</a>
        </div>
    </div>
</body>
</html>

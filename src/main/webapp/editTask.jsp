<%@ page import="by.gsu.epamlab.controller.ControllerConst" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="taskType" scope="session" value="${sessionScope.taskType}"/>
<c:set var="user" scope="session" value="${sessionScope.user}"/>
<jsp:useBean id="task" class="by.gsu.epamlab.model.beans.Task" scope="request"/>

<html>
<head>
    <title>Edit task</title>
    <script type="text/javascript" src="js/script.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form name="taskForm" role="form" method="post" action="editTask" enctype="multipart/form-data">
        <input type="hidden" name="action" value="">
        <input type="hidden" id="taskId" name="taskId" value="<jsp:getProperty name="task" property="id"/>">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Tasks</a>
                </div>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">You enter as: ${user.name}</a></li>
                    <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>
            </div>
        </nav>
        <div class="panel panel-heading">
            <h4>Add task for <c:out value="${taskType.value}"/></h4>
        </div>
        <div class="panel panel-body">
            <div class="col-sm-4">
                <div class="form-group">
                    <div class="">
                        <label for="taskNameId">Name</label>
                        <input name="taskName" class="form-control" id="taskNameId" value="<jsp:getProperty name="task" property="name"/>">
                    </div>
                </div>
                <div class="form-group">
                    <label for="taskDateExId">Expiration date</label>
                    <input name="taskDate" class="form-control" id="taskDateExId" value="<jsp:getProperty name="task" property="dateEnding"/>">
                </div>
                <div class="form-group">
                    <label for="taskFileId">File: </label>
                    <c:choose>
                        <c:when test="${not empty task.filename}">
                            <a class="btn btn-link" href="<%= ControllerConst.Fields.FILE_PATH %><jsp:getProperty name="task" property="filename"/>"><jsp:getProperty name="task" property="filename"/></a>
                            <a class="btn btn-sm btn-info" href="JavaScript:sendFormWithTaskId('<%= ControllerConst.Actions.REMOVE_FILE %>', '<jsp:getProperty name="task" property="id"/>')">Delete</a>
                        </c:when>
                        <c:otherwise>
                            <input type="file" name="file" id="taskFileId"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="form-group">
                    <label for="taskDescriptionId">Description</label>
                    <textarea name="taskDescription" class="form-control" rows="8" id="taskDescriptionId"><jsp:getProperty name="task" property="description"/></textarea>
                </div>
            </div>
        </div>
        <div class="btn-group">
            <a class="btn btn-default" href="JavaScript:sendForm('<%= ControllerConst.Actions.CONFIRM %>')">Confirm</a>&nbsp;
            <a class="btn btn-default" href="JavaScript:sendForm('<%= ControllerConst.Actions.REMOVE %>')">Remove</a>&nbsp;
            <a class="btn btn-default" href="tasks">Back</a>
        </div>
    </form>
</div>
</body>
</html>
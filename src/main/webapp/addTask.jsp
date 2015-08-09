<%@ page import="by.gsu.epamlab.controller.ControllerConst" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="taskType" scope="session" value="${sessionScope.taskType}"/>
<c:set var="user" scope="session" value="${sessionScope.user}"/>

<html>
<head>
    <title>Add task</title>
    <script type="text/javascript" src="js/script.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script>
        $(function() {
            $("#taskDateExId").datepicker({
                changeMonth: true,
                changeYear: true,
                yearRange: "0:+2",
                minDate: "+2",
                defaultDate: "+2",
                dateFormat: 'yy-mm-dd'
            })
        });
    </script>
</head>
<body>
<div class="container">
    <form name="taskForm" role="form" method="post" action="addTask" enctype="multipart/form-data">
        <input type=hidden name="action" value="">
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
                        <input name="taskName" class="form-control" id="taskNameId" value="">
                    </div>
                </div>
                <div class="form-group">
                    <c:if test="${taskType.dateShow}">
                        <label for="taskDateExId">Expiration date</label>
                        <input name="taskDate" class="form-control" id="taskDateExId" value="" readonly>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="taskFileId">File</label>
                    <input type="file" name="file" id="taskFileId"/>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="form-group">
                    <label for="taskDescriptionId">Description</label>
                    <textarea name="taskDescription" class="form-control" rows="8" id="taskDescriptionId"></textarea>
                </div>
            </div>
        </div>
        <div class="btn-group">
            <a class="btn btn-default" href="JavaScript:sendForm('<%= ControllerConst.Actions.ADD %>')">Add</a>&nbsp;
            <a class="btn btn-default" href="tasks">Back</a>
        </div>
    </form>
</div>
</body>
</html>

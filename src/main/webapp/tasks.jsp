<%@ page import="by.gsu.epamlab.controller.ControllerConst" %>
<%@ page import="by.gsu.epamlab.controller.TaskTypesWrapper" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="task" class="by.gsu.epamlab.model.beans.Task" scope="page"/>

<html>
<head>
    <title>Tasks</title>
    <script type="text/javascript" src="js/script.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body onload="addClass('<c:out value="${taskType.value}"/>', 'active');">
<div class="container">
    <form name="taskForm" action="action" method="post" role="form">
        <input type=hidden name="action" value="">
        <input type=hidden name = "taskId" id="taskId" value="">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="action">Tasks</a>
                </div>
                <div>
                    <ul class="nav navbar-nav">
                        <li id="<%= TaskTypesWrapper.TODAY.getValue() %>">
                            <a href="JavaScript:sendForm('<%= TaskTypesWrapper.TODAY %>')" >
                                <%= TaskTypesWrapper.TODAY.getValue() %>
                            </a>
                        </li>
                        <li id="<%= TaskTypesWrapper.TOMORROW.getValue() %>">
                            <a href="JavaScript:sendForm('<%= TaskTypesWrapper.TOMORROW %>')">
                                <%= TaskTypesWrapper.TOMORROW.getValue() %>
                            </a>
                        </li>
                        <li id="<%= TaskTypesWrapper.SOMEDAY.getValue() %>">
                            <a href="JavaScript:sendForm('<%= TaskTypesWrapper.SOMEDAY %>')">
                                <%= TaskTypesWrapper.SOMEDAY.getValue() %>
                            </a>
                        </li>
                        <li id="<%= TaskTypesWrapper.COMPLETE.getValue() %>">
                            <a href="JavaScript:sendForm('<%= TaskTypesWrapper.COMPLETE %>')">
                                <%= TaskTypesWrapper.COMPLETE.getValue() %>
                            </a>
                        </li>
                        <li id="<%= TaskTypesWrapper.RECYCLE_BIN.getValue() %>">
                            <a href="JavaScript:sendForm('<%= TaskTypesWrapper.RECYCLE_BIN %>')">
                                <%= TaskTypesWrapper.RECYCLE_BIN.getValue() %>
                            </a>
                        </li>
                    </ul>
                </div>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="action">You enter as: ${user.name}</a></li>
                    <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>
            </div>
        </nav>
        <div class="panel panel-heading">
            <h4><c:out value="${taskType.value}"/>
                <c:if test="${not empty taskType.date}">
                        <c:out value=" (${taskType.date})"/>
                </c:if>
            </h4>
        </div>
        <div class="panel panel-body">
            <c:choose>
                <c:when test="${empty tasksList}">
                    <c:out value="${taskType.emptyMessage}"/>
                </c:when>
                <c:otherwise>
                    <table class="table table-hover">
                        <tr>
                            <th class="col-md-2">Name</th>
                            <c:choose>
                                <c:when test="${taskType.dateShow}">
                                    <th class="col-md-5">Description</th>
                                    <th class="col-md-2">Date</th>
                                </c:when>
                                <c:otherwise>
                                    <th class="col-md-7">Description</th>
                                </c:otherwise>
                            </c:choose>
                            <th class="col-md-3">File</th>

                        </tr>
                        <c:forEach items="${tasksList}" var="task">
                            <tr>
                                <td>
                                    <input type="checkbox" id="select<jsp:getProperty name="task" property="id"/>" name="select" value="<jsp:getProperty name="task" property="id"/>">
                                    <c:choose>
                                        <c:when test="${taskType.buttonEdit}">
                                            <a class="btn btn-link" href="JavaScript:sendFormWithTaskId('<%= ControllerConst.Actions.EDIT %>', '<jsp:getProperty name="task" property="id"/>')"><jsp:getProperty name="task" property="name"/></a>
                                        </c:when>
                                        <c:otherwise>
                                            <label class="btn" for="select<jsp:getProperty name="task" property="id"/>"><jsp:getProperty name="task" property="name"/></label>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><jsp:getProperty name="task" property="description"/></td>
                                <c:if test="${taskType.dateShow}">
                                    <td><jsp:getProperty name="task" property="dateEnding"/></td>
                                </c:if>
                                <td>
                                    <c:if test="${not empty task.filename}">
                                        <a class="btn btn-link" href="JavaScript:sendFormWithTaskId('<%= ControllerConst.Actions.DOWNLOAD_FILE %>', '<jsp:getProperty name="task" property="id"/>')"><jsp:getProperty name="task" property="filename"/></a>
                                        <a class="btn btn-sm btn-info" href="JavaScript:sendFormWithTaskId('<%= ControllerConst.Actions.REMOVE_FILE %>', '<jsp:getProperty name="task" property="id"/>')">Delete</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="btn-group">
            <c:if test="${taskType.buttonAdd}">
                <a class="btn btn-default" href="JavaScript:sendForm('<%= ControllerConst.Actions.ADD %>')">Add</a>&nbsp;
            </c:if>
            <c:if test="${not empty tasksList}">
                <c:if test="${taskType.buttonComplete}">
                    <a class="btn btn-default" href="JavaScript:sendForm('<%= ControllerConst.Actions.COMPLETE %>')">Complete</a>&nbsp;
                </c:if>
                <c:if test="${taskType.buttonRestore}">
                    <a class="btn btn-default" href="JavaScript:sendForm('<%= ControllerConst.Actions.RESTORE %>')">Restore</a>&nbsp;
                </c:if>
                <a class="btn btn-default" href="JavaScript:sendForm('<%= ControllerConst.Actions.REMOVE %>')">Remove</a>&nbsp;
                <c:if test="${taskType.buttonRemoveAll}">
                    <a class="btn btn-default" href="JavaScript:sendForm('<%= ControllerConst.Actions.REMOVE_ALL %>')">Remove all</a>&nbsp;
                </c:if>
            </c:if>
        </div>
    </form>
</div>

</body>
</html>

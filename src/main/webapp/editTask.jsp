<%@ page import="by.gsu.epamlab.controller.ControllerConst" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="taskType" scope="session" value="${sessionScope.taskType}"/>
<c:set var="user" scope="session" value="${sessionScope.user}"/>
<jsp:useBean id="task" class="by.gsu.epamlab.model.beans.Task" scope="request"/>


<html>
<head>
    <title>Add task</title>
    <script type="text/javascript" src="resources/script.js"></script>
</head>
<body>
    <form name="taskForm" method="post" action="editTask" enctype="multipart/form-data">
        <input type=hidden name="action" value="">
        <%@ include file="header.jsp" %>
        <h3>Edit task</h3>
        <input type="hidden" name="select" value="<jsp:getProperty name="task" property="id"/>">
        <table>
            <tr>
                <td><label for="taskNameId">Name</label></td>
                <td><input name="taskName" id="taskNameId" value="<jsp:getProperty name="task" property="name"/>"></td>
            </tr>
            <tr>
                <td><label for="taskDateExId">Expiration date</label></td>
                <td><input name="taskDate" id="taskDateExId" value="<jsp:getProperty name="task" property="dateEnding"/>"></td>
            </tr>
            <tr>
                <td><label for="taskDescriptionId">Description</label></td>
                <td><textarea name="taskDescription" id="taskDescriptionId"><jsp:getProperty name="task" property="description"/></textarea></td>
            </tr>
        </table>
        <a href="JavaScript:sendForm('<%= ControllerConst.Actions.CONFIRM %>')">Confirm</a>&nbsp;
        <a href="JavaScript:sendForm('<%= ControllerConst.Actions.REMOVE %>')">Remove</a>&nbsp;
        <a href="tasks">Return to tasks page</a>
        <%@ include file="footer.jsp"%>
    </form>
</body>
</html>

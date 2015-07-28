<%@ page import="by.gsu.epamlab.controller.ControllerConst" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="taskType" scope="session" value="${sessionScope.taskType}"/>
<c:set var="user" scope="session" value="${sessionScope.user}"/>

<html>
<head>
    <title>Add task</title>
    <script type="text/javascript" src="js/script.js"></script>
</head>
<body>
    <form name="taskForm" method="post" action="addTask" enctype="multipart/form-data">
        <input type=hidden name="action" value="">
        <%@ include file="header.jsp" %>
        <h3>Add new task for <c:out value="${taskType.value}"/></h3>
        <table>
            <tr>
                <td><label for="taskNameId">Name</label></td>
                <td><input name="taskName" id="taskNameId" value=""></td>
            </tr>
            <c:if test="${taskType.dateShow}">
                <tr>
                    <td><label for="taskDateExId">Expiration date</label></td>
                    <td><input name="taskDate" id="taskDateExId" value=""></td>
                </tr>
            </c:if>
            <tr>
                <td><label for="taskDescriptionId">Description</label></td>
                <td><textarea name="taskDescription" id="taskDescriptionId"></textarea></td>
            </tr>
            <tr>
                <td><label for="taskFileId">File</label></td>
                <td><input type="file" name="file" id="taskFileId"/></td>
            </tr>
        </table>
        <a href="JavaScript:sendForm('<%= ControllerConst.Actions.ADD %>')">Add</a>&nbsp;
        <a href="tasks">Return to tasks page</a>
        <%@ include file="footer.jsp"%>
    </form>

</body>
</html>

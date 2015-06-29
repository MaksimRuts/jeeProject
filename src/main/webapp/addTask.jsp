<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Add task</title>
</head>
<body>
    <form method="post" action="addTask">
        <h3>Add new task</h3>
        <table>
            <tr>
                <td><label for="taskNameId">Name</label></td>
                <td><input name="taskName" id="taskNameId" value=""></td>
            </tr>
            <%--<c:if test="${withDate}">--%>
                <tr>
                    <td><label for="taskDateExId">Expiration date</label></td>
                    <td><input name="taskDate" id="taskDateExId" value=""></td>
                </tr>
            <%--</c:if>--%>
            <tr>
                <td><label for="taskDescriptionId">Description</label></td>
                <td><textarea name="taskDescription" id="taskDescriptionId"></textarea></td>
            </tr>
        </table>
        <input type="submit" name="action" value="Add">
        <a href="tasks">Return to tasks page</a>
    </form>

</body>
</html>

<%@ page import="by.gsu.epamlab.controller.ControllerConst" %>
<%@ page import="by.gsu.epamlab.controller.TaskTypes" %>
<%@ page import="by.gsu.epamlab.model.beans.Task" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="task" class="by.gsu.epamlab.model.beans.Task" scope="page"/>

<html>
<head>
    <title>Notes</title>
    <style>
        <%@ include file="login.css"%>
    </style>
    <script type="text/javascript" src="script.js"></script>
</head>
<body>
    <form name="taskForm" action="tasks" method="post">
        <h3>${username} notes</h3>
        <br/>
        <a href="JavaScript:sendForm('<%= TaskTypes.TODAY %>')" >
            <%= TaskTypes.TODAY.getValue() %>
        </a>&nbsp;
        <a href="JavaScript:sendForm('<%= TaskTypes.TOMORROW %>')">
            <%= TaskTypes.TOMORROW.getValue() %>
        </a>&nbsp;
        <a href="JavaScript:sendForm('<%= TaskTypes.SOMEDAY %>')">
            <%= TaskTypes.SOMEDAY.getValue() %>
        </a>&nbsp;
        <a href="JavaScript:sendForm('<%= TaskTypes.FIXED %>')">
            <%= TaskTypes.FIXED.getValue() %>
        </a>&nbsp;
        <a href="JavaScript:sendForm('<%= TaskTypes.RECYCLE_BIN %>')">
            <%= TaskTypes.RECYCLE_BIN.getValue() %>
        </a>&nbsp;
        <input type=hidden name="action" value="">
        <br/>
        <br/>

        <h4>${taskType}</h4>
        <c:choose>
            <c:when test="${tasksIsEmpty}">
                <%= ControllerConst.Messages.NOTES_LIST_EMPTY %><br/>
            </c:when>
            <c:otherwise>
                <table border="1">
                    <tr>
                        <th>Select</th>
                        <th>Name</th>
                        <th>Description</th>
                        <c:if test="${withDate}">
                            <th>Expiration date</th>
                        </c:if>
                        <th>File</th>
                    </tr>
                    <c:forEach items="${tasksList}" var="task">
                        <td><input type="checkbox" name="select" value="<jsp:getProperty name="task" property="id"/>"></td>
                        <td><jsp:getProperty name="task" property="name"/></td>
                        <td><jsp:getProperty name="task" property="description"/></td>
                        <c:if test="${withDate}">
                            <td><jsp:getProperty name="task" property="dateEnding"/></td>
                        </c:if>
                        <td></td>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
        <br/>
        <input type="submit" name="actionn" value="add" />
        <input type="submit" name="actionn" value="remove" />
        <c:if test="${buttonFix}">
            <input type="submit" name="actionn" value="fix" />
        </c:if>
        <br/>
        <br/>
        <a href="logout">Logout</a>
    </form>
</body>
</html>

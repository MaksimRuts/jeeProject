<%@ page import="by.gsu.epamlab.controller.ControllerConst" %>
<%@ page import="by.gsu.epamlab.controller.TaskTypesWrapper" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="task" class="by.gsu.epamlab.model.beans.Task" scope="page"/>
<c:set var="taskType" scope="session" value="${taskType}"/>
<c:set var="withDate" scope="session" value="${withDate}"/>

<html>
<head>
    <title>Notes</title>
    <style>
        <%@ include file="login.css"%>
    </style>
    <script type="text/javascript" src="script.js"></script>
</head>
<body>
    <form name="taskForm" action="action" method="post">
        <h3>${username} notes</h3>
        <br/>
        <a href="JavaScript:sendForm('<%= TaskTypesWrapper.TODAY %>')" >
            <%= TaskTypesWrapper.TODAY.getValue() %>
        </a>&nbsp;
        <a href="JavaScript:sendForm('<%= TaskTypesWrapper.TOMORROW %>')">
            <%= TaskTypesWrapper.TOMORROW.getValue() %>
        </a>&nbsp;
        <a href="JavaScript:sendForm('<%= TaskTypesWrapper.SOMEDAY %>')">
            <%= TaskTypesWrapper.SOMEDAY.getValue() %>
        </a>&nbsp;
        <a href="JavaScript:sendForm('<%= TaskTypesWrapper.COMPLETE %>')">
            <%= TaskTypesWrapper.COMPLETE.getValue() %>
        </a>&nbsp;
        <a href="JavaScript:sendForm('<%= TaskTypesWrapper.RECYCLE_BIN %>')">
            <%= TaskTypesWrapper.RECYCLE_BIN.getValue() %>
        </a>&nbsp;
        <input type=hidden name="action" value="">
        <br/>
        <br/>

        <h4><c:out value="${taskType}"/></h4>
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
                        </tr>
                            <td><input type="checkbox" name="select" value="<jsp:getProperty name="task" property="id"/>"></td>
                            <td><jsp:getProperty name="task" property="name"/></td>
                            <td><jsp:getProperty name="task" property="description"/></td>
                            <c:if test="${withDate}">
                                <td><jsp:getProperty name="task" property="dateEnding"/></td>
                            </c:if>
                            <td></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
        <br/>
        <a href="JavaScript:sendForm('<%= ControllerConst.Actions.ADD %>')">Add</a>&nbsp;
        <a href="JavaScript:sendForm('<%= ControllerConst.Actions.REMOVE %>')">Remove</a>&nbsp;
        <c:if test="${buttonComplete}">
            <a href="JavaScript:sendForm('<%= ControllerConst.Actions.COMPLETE %>')">Complete</a>&nbsp;
        </c:if>
        <br/>
        <br/>
        <a href="logout">Logout</a>
    </form>
</body>
</html>

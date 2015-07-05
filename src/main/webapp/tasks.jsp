<%@ page import="by.gsu.epamlab.controller.ControllerConst" %>
<%@ page import="by.gsu.epamlab.controller.TaskTypesWrapper" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="task" class="by.gsu.epamlab.model.beans.Task" scope="page"/>
<c:set var="taskType" scope="session" value="${sessionScope.taskType}"/>
<c:set var="user" scope="session" value="${sessionScope.user}"/>

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
        <input type=hidden name="action" value="">
        <%@ include file="header.jsp" %>
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
        <br/>
        <br/>

        <h4><c:out value="${taskType.value}"/>
            <c:if test="${not empty taskType.date}">
                    <c:out value=" (${taskType.date})"/>
            </c:if>
        </h4>
        <c:choose>
            <c:when test="${empty tasksList}">
                <%= ControllerConst.Messages.NOTES_LIST_EMPTY %><br/>
            </c:when>
            <c:otherwise>
                <table border="1">
                    <tr>
                        <th>Select</th>
                        <th>Name</th>
                        <th>Description</th>
                        <c:if test="${taskType.dateShow}">
                            <th>Expiration date</th>
                        </c:if>
                        <th>File</th>
                    </tr>
                    <c:forEach items="${tasksList}" var="task">
                        <tr>
                            <td><input type="checkbox" name="select" value="<jsp:getProperty name="task" property="id"/>"></td>
                            <td><jsp:getProperty name="task" property="name"/></td>
                            <td><jsp:getProperty name="task" property="description"/></td>
                            <c:if test="${taskType.dateShow}">
                                <td><jsp:getProperty name="task" property="dateEnding"/></td>
                            </c:if>
                            <td></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
        <br/>
        <c:if test="${taskType.buttonAdd}">
            <a href="JavaScript:sendForm('<%= ControllerConst.Actions.ADD %>')">Add</a>&nbsp;
        </c:if>
        <c:if test="${taskType.buttonEdit}">
            <a href="JavaScript:sendForm('<%= ControllerConst.Actions.EDIT %>')">Edit</a>&nbsp;
        </c:if>
        <c:if test="${taskType.buttonComplete}">
            <a href="JavaScript:sendForm('<%= ControllerConst.Actions.COMPLETE %>')">Complete</a>&nbsp;
        </c:if>
        <a href="JavaScript:sendForm('<%= ControllerConst.Actions.REMOVE %>')">Remove</a>&nbsp;
        <c:if test="${taskType.buttonRestore}">
            <a href="JavaScript:sendForm('<%= ControllerConst.Actions.RESTORE %>')">Restore</a>&nbsp;
        </c:if>
        <c:if test="${taskType.buttonRemoveAll}">
            <a href="JavaScript:sendForm('<%= ControllerConst.Actions.REMOVE_ALL %>')">Remove all</a>&nbsp;
        </c:if>
        <br/>
        <%@ include file="footer.jsp"%>
    </form>
</body>
</html>

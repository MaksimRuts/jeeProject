<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Add task</title>
</head>
<body>
    <form method="post" action="">
        <h3>Add new task</h3>
        <table>
            <tr>
                <td><label for="taskNameId">Name</label></td>
                <td><input name="taskName" id="taskNameId" value=""></td>
            </tr>
            <tr>
                <td><label for="taskDateExId">Expiration date</label></td>
                <td><textarea name="taskDateEx" id="taskDateExId" value=""></td>
            </tr>
            <tr>
                <td><label>Description</label></td>
                <td><textarea name="taskDescription" id="taskDescriptionId" value=""></textarea></td>
            </tr>
        </table>
        <input type="submit" name="action" value="Add">
    </form>

</body>
</html>

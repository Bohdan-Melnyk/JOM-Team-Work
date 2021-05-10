<%@ page import="com.softserve.itacademy.model.Priority" %>
<%@ page import="com.softserve.itacademy.model.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new Task</title>

    <style>
        <%@include file="../styles/main.css"%>
    </style>

</head>
<body>
<%@include file="header.html" %>

<h2>Create new Task</h2>

<form action="/create-task" method="post">

    <%
        String msg = (String) request.getAttribute("error");
    %>
    <p style="color:red"><%=msg != null ? msg : ""%>
    </p>
    <table>
        <tr>
            <td><label for="task">Name: </label></td>
            <td><input type="text" id="task" name="title"></td>

        </tr>
        <tr>
            <td><label for="priority"></label>Priority:</td>
            <td><select id="priority" name="priority">
                <option value="low">Low</option>
                <option value="medium">Medium</option>
                <option value="high">High</option>
            </select></td>

        </tr>
        <tr>
            <td><input type="submit" value="Create"></td>
            <td><input type="reset" value="Reset"></td>
        </tr>
    </table>
</form>
</body>
</html>

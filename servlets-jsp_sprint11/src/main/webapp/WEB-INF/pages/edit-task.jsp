<%@ page import="com.softserve.itacademy.model.Task" %>
<%@ page import="com.softserve.itacademy.model.Priority" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit existing Task</title>

        <style>
            <%@include file="../styles/main.css" %>
        </style>

    </head>
    <body>
        <%@include file="header.html" %>

        <form action="/edit-task" method="post">

            <% Task task = (Task) request.getAttribute("task");
            %>

            <table>
                <tr>
                    <td>
                        <label for="id">Id:</label>
                    </td>
                    <td>
                        <input type="text" id="id" name="id" value="<%=task.getId()%>" disabled>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="task">Name:</label>
                    </td>
                    <td>
                        <input type="text" id="task" name="title" value="<%=task.getTitle()%>">
                    </td>
                </tr>
                <tr>
                    <td><label for="priority"></label>Priority:
                    </td>
                    <td>
                        <select id="priority" name="priority">
                            <option selected><%=task.getPriority().name()%>
                            </option>
                            <option value="low">Low</option>
                            <option value="medium">Medium</option>
                            <option value="high">High</option>

                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Update">
                    </td>
                    <td>
                        <input type="reset" value="Clear">
                    </td>
                </tr>
            </table>
        </form>


    </body>
</html>

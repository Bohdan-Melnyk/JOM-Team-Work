<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>

    <style>
        <%@include file="../styles/main.css"%>
    </style>


</head>
<body>

<h2>Task with ID '<%=request.getAttribute("id")%>' not found in To-Do List!</h2>


</body>
</html>

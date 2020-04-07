<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<html>
<head>
    <title>Users</title>
</head>
<body>
<form method="post" action="/add">
    <label for="name">Name:</label>
    <input id="name" type="text" name="name">
    <input type="submit" value="Create">
</form>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Action</th>
    </tr>
    <c:forEach var='user' items="${users}">
        <tr>
            <form method="post">
            <td style="text-align: center">${user.getId()}</td>
            <td style="text-align: center"><input type="text" name="name" value="${user.getName()}"></td>
            <td style="text-align: center">
            <input type="hidden" name="id" value="${user.getId()}">
            <input type="submit" value="Delete" formaction="/del"> /
            <input type="submit" value="Update" formaction="/updt"></form>
            </td>
        </tr>
    </c:forEach></table>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Пользователи</title>
    </head>
    <body>
        <h1 style="text-align: center">Список пользователей</h1>
        <hr>
        <table style="margin-left: auto;margin-right: auto" >
            <tr>
                <th>ID</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Action</th>
            </tr>
            <c:forEach var='user' items="${users}">
                <tr>
                    <td style="text-align: center">${user.getId()}</td>
                    <td style="text-align: center">${user.getFirstName()}</td>
                    <td style="text-align: center">${user.getLastName()}</td>
                    <td style="text-align: center">
                        <form>
                            <input type="hidden" name="id" value="${user.getId()}">
                            <input type="submit" value="Удалить" formmethod="post" formaction="/del">
                            <input type="submit" value="Редактировать" formmethod="get" formaction="/updt">
                        </form>
                    </td>
                </tr>
            </c:forEach></table>
        <form style="text-align: center" method="get" action="/add">
            <input type="submit" value="Добавить">
        </form>
    </body>
</html>
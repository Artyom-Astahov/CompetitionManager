<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: astah
  Date: 25.03.2024
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<form action="registration" method="post">
    <label for="name">Имя:
        <input type="text" id="name" name="name">
    </label><br>

    <label for="birthday">Дата рождения:
        <input type="date" id="birthday" name="birthday">
    </label><br>


    <label for="email">Email:
        <input type="email" id="email" name="email">
    </label><br>


    <label for="password">Пароль:
        <input type="password" id="password" name="password">
    </label><br>


    <label for="role">Роль:</label><br>
    <select id="role" name="role">
        <c:forEach var="role" items="${requestScope.roles}">
            <option label="${role}">${role}</option>
            <br/>
        </c:forEach>
    </select><br><br>


    <input type="submit" value="Зарегистрироваться">
</form>
</body>
</html>

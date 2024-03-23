<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Список соревнований</title>
</head>
<body>
<h1>Список соревнований:</h1>
<ul>
    <c:forEach var="competition" items="${requestScope.competitions}">
        <li>${competition.dateEvent()} ${competition.description()}</li>
    </c:forEach>
</ul>
</body>
</html>

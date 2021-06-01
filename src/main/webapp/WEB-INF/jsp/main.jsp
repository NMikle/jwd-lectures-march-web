<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user}</p>
</c:if>
<p>Please click below to see all users:</p>
<br>
<a href="${pageContext.request.contextPath}/controller?command=show_users">Users page</a>
<br>
<c:choose>
    <c:when test="${empty sessionScope.user}">
        <a href="${pageContext.request.contextPath}/controller?command=show_login">Login page</a>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/controller?command=logout">Logout</a>
    </c:otherwise>
</c:choose>
</body>
</html>

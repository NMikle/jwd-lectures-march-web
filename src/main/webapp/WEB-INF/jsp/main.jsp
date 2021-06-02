<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.epam.jwd.web.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
<c:if test="${not empty sessionScope.userName}">
    <p>Hello, ${sessionScope.userName}</p>
</c:if>
<br>
<c:choose>
    <c:when test="${empty sessionScope.userName}">
        <a href="${pageContext.request.contextPath}/controller?command=show_login">Login page</a>
    </c:when>
    <c:otherwise>
        <c:if test="${sessionScope.userRole eq Role.ADMIN}">
            <p>Please click below to see all users:</p>
            <a href="${pageContext.request.contextPath}/controller?command=show_users">Users page</a>
            <br>
        </c:if>
        <a href="${pageContext.request.contextPath}/controller?command=logout">Logout</a>
    </c:otherwise>
</c:choose>
</body>
</html>

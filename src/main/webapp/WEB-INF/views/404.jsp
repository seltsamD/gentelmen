<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="page.notfound"/></title>
<html>
<head>
    <meta name="google-site-verification" content="-MD2HrSDLr7JKWM9xnqx5OiPG7Uio20xytJhg4iICqc" />
    <meta name="robots" content="noindex">
</head>
<body>
<div class="container">
    <jsp:include page="header.jsp" />
    <div class="div404">
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>

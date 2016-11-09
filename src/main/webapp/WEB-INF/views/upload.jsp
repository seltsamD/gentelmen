<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 01.10.2016
  Time: 16:50
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<html>
<head>
    <spring:url value="/resources/js/app.js" var="appJS" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="${appJS}"></script>

    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <script src="<c:url value="${pageContext.request.contextPath}/resources/js/app.js" />" type="text/javascript"></script>

    <title><spring:message code="page.title"/></title></head>
<body>
<h1>Single File Upload</h1>
<form method="post" enctype="multipart/form-data" action="multipleSave">
    Upload File 1: <input type="file" name="file" multiple="true"> <br/>
    <br /><br /><input type="submit" value="Upload">
</form>

</body>
</html>
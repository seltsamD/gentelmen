<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 03.10.2016
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<html>
<head>
    <title>Джентльмен.in.ua</title>

    <link href="https://fonts.googleapis.com/css?family=Merriweather|Pattaya|Playfair+Display+SC" rel="stylesheet">
        <link href="<c:url value="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="${pageContext.request.contextPath}/resources/css/main.css" />" rel="stylesheet">
        <link href="<c:url value="${pageContext.request.contextPath}/resources/css/jquery.fancybox.css" />" rel="stylesheet">

        <script src="<c:url value="${pageContext.request.contextPath}/resources/js/jquery-2.1.4.min.js" />" type="text/javascript"></script>
        <script src="<c:url value="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" />" type="text/javascript"></script>
        <script src="<c:url value="${pageContext.request.contextPath}/resources/js/jquery.fancybox.pack.js" />" type="text/javascript"></script>
    <script src="<c:url value="${pageContext.request.contextPath}/resources/js/app.js" />" type="text/javascript"></script>

</head>
<body>

<%--<div class="main">--%>
<%--<a href="?mylocale=uk">Ukrainian </a> | <a href="?mylocale=ru">Russian </a>--%>
<%--<h3> <spring:message code="page.title"/></h3>--%>
<%--</div>--%>

<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Джентельмен.in.ua</span>
            </button>
            <a class="navbar-brand" href="Джентльмен.in.ua">Джентльмен.in.ua</a>
        </div>
        <div class="navbar-collapse collapse navbar-right">
            <ul class="nav navbar-nav">
                <li><div id="msg"></div></li>
                <li class="active"><a href="<c:url value="/index"/>"><spring:message code="page.title"/></a></li>
                <li class="active"><a href="<c:url value="/catalogue"/>"><spring:message code="page.catalogue"/></a></li>
                <li class="active"><a href="<c:url value="/goodform"/>"><spring:message code="page.good"/></a></li>
                <li class="active"><a href="<c:url value="/colors"/>"><spring:message code="text.color"/></a></li>
                <li class="active"><a href="<c:url value="/categories"/>"><spring:message code="text.category"/></a></li>
                <li class="active">
                   <a href="<c:url value="/shopping-cart"/>"><span  id="cartDiv" class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></a>
                </li>
                <li><a href="?mylocale=uk"><img height="35px" width="35px" src="<c:url value="resources/images/ukraine-flag.png"/>" ></a></li>
                <li><a href="?mylocale=ru"><img height="35px" width="35px" src="<c:url value="resources/images/rus-flag.png"/>" ></a></li>
            </ul>
        </div>
    </div>
</div>


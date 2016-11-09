<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>--%>
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


                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <li class="active"><a href="<c:url value="/admin/goodform"/>"><spring:message code="page.good"/></a></li>
                    <li class="active"><a href="<c:url value="/admin/colors"/>"><spring:message code="text.color"/></a></li>
                    <li class="active"><a href="<c:url value="/admin/categories"/>"><spring:message code="text.category"/></a></li>
                    <li class="active"><a href="<c:url value="/admin/orders"/>"><spring:message code="text.orders"/></a></li>
                </c:if>
                <c:if test="${pageContext.request.userPrincipal.name == null}">
                <li class="active">
                   <a href="<c:url value="/shopping-cart"/>"><span  id="cartDiv" class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></a>
                </li>
                </c:if>
                <form class="navbar-form navbar-left" role="search">
                    <form:form  action="/search" id="formSearch" method="post" commandName="search">
                        <div class="form-group">
                            <input id="search_str" name="search_str" type="text" class="form-control" placeholder="<spring:message code="search"/>">
                        </div>
                        <button type="submit" class="btn btn-default"><spring:message code="search"/></button>
                    </form:form>
                </form>
                <li><a href="?mylocale=uk"><img class="flag" src="<c:url value="/resources/images/ukraine-flag.png"/>" ></a></li>
                <li><a href="?mylocale=ru"><img class="flag" src="<c:url value="/resources/images/rus-flag.png"/>" ></a></li>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <li class="active"><a href="<c:url value="/logout"/>"><spring:message code="logout"/>, ${pageContext.request.userPrincipal.name}</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</div>

<div id="snackbar"><spring:message code="basket.IsAdded"/></div>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 26.10.2016
  Time: 12:41
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="${pageContext.request.contextPath}/resources/css/main.css" />" rel="stylesheet">
    <title><spring:message code="login.msg"/></title>
</head>
<body>
<div class="container">
    <div class="main">
    <div class="row">



        <div class="Absolute-Center is-Responsive">
            <div class="col-sm-12 col-md-10 col-md-offset-1">

    <c:url value="/j_spring_security_check" var="loginUrl" />
    <form action="${loginUrl}" method="post" class="form-horizontal opacity-back">
        <h1><spring:message code="login.msg"/></h1>
        <div class="form-group input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <input class="form-control" type="text" id="j_username" name='j_username' placeholder="<spring:message code="username"/>"/>
        </div>
        <div class="form-group input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
            <input class="form-control" type="password" id='j_password' name='j_password' placeholder="<spring:message code="password"/>"/>
        </div>

        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}">

            <div class="form-group">
            <button class="btn btn-success width100" type="submit"><spring:message code="login"/></button>

        </div>


</form>
            </div>
            </div>
        </div>
</div>
</div>

</body>

</html>

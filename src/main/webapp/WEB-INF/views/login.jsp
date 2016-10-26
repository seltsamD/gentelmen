<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 26.10.2016
  Time: 12:41
  To change this template use File | Settings | File Templates.
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
        <%--<div class="form-group">--%>
            <%--<label for="j_username" class="col-sm-2 control-label"><spring:message code="username"/></label>--%>
            <%--<input type="text" class="form-control" name="j_username" required autofocus >--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
            <%--<label for="j_username" class="col-sm-2 control-label"><spring:message code="password"/></label>--%>
            <%--<input  type="password" class="form-control" name="j_password"  required >--%>
        <%--</div>--%>
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
<%--<form:form cssClass="form-horizontal" id="f" action="/j_spring_security_check" method="POST" >--%>

    <%--<div class="form-group">--%>
        <%--<label for="j_username" class="col-sm-2 control-label"><spring:message code="username"/></label>--%>
        <%--<div class="col-sm-10">--%>
            <%--<form:input path="j_username" id="j_username" cssClass="form-control"/><form:errors path="j_username" cssClass="error-msg"/>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="form-group">--%>
        <%--<label for="j_password" class="col-sm-2 control-label"><spring:message code="password"/></label>--%>
        <%--<div class="col-sm-10">--%>
            <%--<form:password path="j_password" id="j_password" cssClass="form-control"/><form:errors path="j_password" cssClass="error-msg"/>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<input type="hidden" name="${_csrf.parameterName}"--%>
           <%--value="${_csrf.token}" />--%>
    <%--<input type="submit" value="<spring:message code="login"/>" id="btn-add"  class="btn btn-info">--%>
    <%--</form:form>--%>
</body>


<%--<div tiles:fragment="content">--%>
    <%--<form name="f" th:th:action="@{/login}" method="post">--%>
        <%--<fieldset>--%>
            <%--<legend>Please Login</legend>--%>
            <%--<div th:if="${param.error}" class="alert alert-error">--%>
                <%--Invalid username and password.--%>
            <%--</div>--%>
            <%--<div th:if="${param.logout}" class="alert alert-success">--%>
                <%--You have been logged out.--%>
            <%--</div>--%>
            <%--<label for="username">Username</label>--%>
            <%--<input type="text" id="username" name="username"/>--%>
            <%--<label for="password">Password</label>--%>
            <%--<input type="password" id="password" name="password"/>--%>
            <%--<div class="form-actions">--%>
                <%--<button type="submit" class="btn">Log in</button>--%>
            <%--</div>--%>
        <%--</fieldset>--%>
    <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
</html>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 08.10.2016
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Чоловічий одяг та аксесуари купити у інтернет-магазині джентльмен.in.ua</title>
    <script  src="<c:url value="${pageContext.request.contextPath}/resources/js/jquery-2.1.4.min.js" />" type="text/javascript"></script>
    <script  src="<c:url value="${pageContext.request.contextPath}/resources/js/app.js" />" type="text/javascript"></script>

    <script>
        (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
                    (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
                m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
        })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

        ga('create', 'UA-87631623-1', 'auto');
        ga('send', 'pageview');

    </script>
</head>
<body>
<div class="container">

    <jsp:include page="header.jsp" />
    <div class="mainContent">

        <div class="topBox">
            <div class="whiteBack">
                <h1><spring:message code="color"/></h1>

                <c:if test="${color.id==0}">
                    <h3><spring:message code="color.add"/></h3>
                </c:if>
                <c:if test="${color.id!=0}">
                    <h3><spring:message code="color.update"/> <c:out value="${good.id}"/></h3>
                </c:if>
            </div>
            <form:form cssClass="form-horizontal" id="formColor" action="/admin/addColor" method="POST" commandName="color" enctype="multipart/form-data">
            <form:hidden path="id"/>
                <div class="form-group">
                    <label for="uaText" class="col-sm-2 control-label">Українською</label>
                    <div class="col-sm-10">
                        <form:input path="uaText"  cssClass="form-control"/>
                        <form:errors path="uaText" cssClass="error-msg"/>
                    </div>
                </div>
                <div class="form-group">
                 <label for="ruText" class="col-sm-2 control-label">На русском</label>
                    <div class="col-sm-10">
                        <form:input path="ruText"  cssClass="form-control"/>
                        <form:errors path="ruText" cssClass="error-msg"/></div>
                </div>
                <div class="form-group">
                    <div class="col-sm-12">
                        <c:if test="${color.id==0}">
                            <input type="button" value="<spring:message code="form.add"/>" id="btn-add-color"  class="btn btn-success">
                        </c:if>
                        <c:if test="${color.id!=0}">
                            <input type="button" value="<spring:message code="form.edit"/>" id="btn-update-color"  class="btn btn-warning">
                        </c:if>
                    </div>
                </div>
                <div class="form-group col-sm-12">
                    <c:out value="${msg}"/>
                </div>
            </form:form>

        </div>




        <table id="box-table-b">
            <tr>
                <th> ID </th>
                <th> Українською </th>
                <th> На русском </th>
                <th></th>
            </tr>
            <c:forEach var="obj" items="${allData}">
                <tr>
                     <td> <c:out value="${obj.id}"/> </td>
                    <td> <c:out value="${obj.uaText}"/> </td>
                    <td> <c:out value="${obj.ruText}"/> </td>

                    <td>
                        <a href="${pageContext.request.contextPath}/admin/deleteColor?id=${obj.id}"><spring:message code="form.delete"/></a> |
                        <a href="${pageContext.request.contextPath}/admin/colorById?id=${obj.id}"><spring:message code="form.edit"/></a>
                    </td>
                </tr>
                <tr><td colspan="4"><hr></td> </tr>
            </c:forEach>
        </table>

    </div>
</div>
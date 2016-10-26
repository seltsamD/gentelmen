<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 08.10.2016
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<jsp:include page="footer.jsp" />
<script src="<c:url value="${pageContext.request.contextPath}/resources/js/app.js" />" type="text/javascript"></script>


<div class="container">
    <div class="main">

        <div class="topBox">
            <div class="whiteBack">
                <h1><spring:message code="category"/></h1>

                <c:if test="${category.id==0}">
                    <h3><spring:message code="category.add"/></h3>
                </c:if>
                <c:if test="${category.id!=0}">
                    <h3><spring:message code="category.update"/> <c:out value="${good.id}"/></h3>
                </c:if>
            </div>
            <form:form cssClass="form-horizontal" id="formCategory" action="/admin/addCategory" method="POST" commandName="category" enctype="multipart/form-data">
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
                        <c:if test="${category.id==0}">
                            <input type="button" value="<spring:message code="form.add"/>" id="btn-add-category"  class="btn btn-success">
                        </c:if>
                        <c:if test="${category.id!=0}">
                            <input type="button" value="<spring:message code="form.edit"/>" id="btn-update-category"  class="btn btn-warning">
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
                        <a href="${pageContext.request.contextPath}/admin/deleteCategory?id=${obj.id}"><spring:message code="form.delete"/></a> |
                        <a href="${pageContext.request.contextPath}/admin/categoryById?id=${obj.id}"><spring:message code="form.edit"/></a>
                    </td>
                </tr>
                <tr><td colspan="4"><hr></td> </tr>
            </c:forEach>
        </table>

    </div>
</div>
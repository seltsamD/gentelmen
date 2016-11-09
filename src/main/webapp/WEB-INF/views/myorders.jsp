<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 09.11.2016
  Time: 18:59
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<jsp:include page="footer.jsp" />
<script src="<c:url value="/resources/js/app.js" />" type="text/javascript"></script>
<script src="<c:url value="${pageContext.request.contextPath}/resources/js/jquery.maskedinput.min.js" />" type="text/javascript"></script>

<body>

<div class="container">
    <div class="main">
        <div class="topBox">
            <div class="whiteBack">
        <h1><spring:message code="searchByPhone"/></h1>
        <form:form cssClass="form-horizontal" action="/getByPhone" id="formNewOrder" method="post" commandName="phone">
            <div class="form-group">
                <label for="phone" class="col-sm-2 control-label"><spring:message code="order.phone"/></label>
                <div class="col-sm-10">
                    <input class="form-control" type="text" id="phone" name="phone" placeholder="<spring:message code="order.phone"/>">
                    <form:errors path="phone" cssClass="error-msg"/>
                </div>
            </div>
            <input type="submit" value="<spring:message code="search"/>"  class="btn btn-success">
        </form:form>
</div>
            <c:if test="${count <= 0}">
                <h3><spring:message code="order.notFound"/></h3>
            </c:if>
            <c:if test="${count > 0}">
        <div class="panel-group" id="accordion">
            <c:forEach var="order" items="${ordersData}">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title" data-toggle="collapse" data-parent="#accordion" data-target="#collapse${order.id}">
                                <%--<a class="accordion-toggle">--%>
                            <table id="box-table-b">
                                <tr>
                                    <th> <spring:message code="order.id"/> </th>
                                    <th> <spring:message code="order.date"/>  </th>
                                    <th> <spring:message code="order.name"/> </th>
                                    <th> <spring:message code="order.phone"/>  </th>
                                    <th> <spring:message code="order.info"/>  </th>
                                </tr>
                                <tr>
                                    <td>${order.id}</td>
                                    <td><fmt:formatDate type="both"  value="${order.date}" /></td>
                                    <td>${order.name}</td>
                                    <td>${order.phone}</td>
                                    <td>${order.info}</td>
                                </tr>
                            </table>
                                <%--</a>--%>
                        </h4>
                    </div>
                    <div id="collapse${order.id}" class="panel-collapse collapse">
                        <div class="panel-body">
                            <table class="sub-info">
                                <thead>
                                <tr> <th>  </th>
                                    <th> <spring:message code="good.id"/> </th>
                                    <th> <spring:message code="good.firm"/> </th>
                                    <th> <spring:message code="good.color"/> </th>
                                    <th> <spring:message code="good.type"/> </th>
                                    <th> <spring:message code="good.price"/> </th>
                                    <th> <spring:message code="good.size"/> </th>
                                    <th> <spring:message code="good.about"/> </th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="obj" items="${order.listGood}">

                                    <tr>
                                        <td>
                                            <a class="single_image" href="<c:url value="/images/${obj.id}_0.jpg"/>"><img  class="miniImg"  src="<c:url value="/images/${obj.id}_0.jpg"/>" alt="${obj.firm} ${obj.category.uaText} "${obj.color.uaText}"/></a>

                                        </td>
                                        <td> <c:out value="${obj.id}"/> </td>
                                        <td> <c:out value="${obj.firm}"/> </td>
                                        <c:if test="${lang_code == 'uaText'}">
                                            <td> <c:out value="${obj.color.uaText}"/> </td>
                                            <td> <c:out value="${obj.category.uaText}"/> </td>
                                        </c:if>
                                        <c:if test="${lang_code == 'ruText'}">
                                            <td> <c:out value="${obj.color.ruText}"/> </td>
                                            <td> <c:out value="${obj.category.ruText}"/> </td>
                                        </c:if>
                                        <td> <c:out value="${obj.price}"/> </td>
                                        <td> <c:out value="${obj.size}"/> </td>
                                        <c:if test="${lang_code == 'uaText'}">
                                            <td> <c:out value="${obj.description.uaText}"/> </td>
                                        </c:if>
                                        <c:if test="${lang_code == 'ruText'}">
                                            <td> <c:out value="${obj.description.ruText}"/> </td>
                                        </c:if>
                                    </tr>
                                    <tr><td colspan="9"><hr></td> </tr>

                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
            </c:if>
        </div>
        </div>
    </div>
<script type="text/javascript">
    jQuery(function($){
        $("#phone").mask("(999) 999-99-99");
    });
</script>
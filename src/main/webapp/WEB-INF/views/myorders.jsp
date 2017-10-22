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
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
<div class="container">
    <jsp:include page="header.jsp"/>
    <div class="mainContent">
        <div class="topBox">
            <h2>${msg}</h2>
            <div class="whiteBack">
                <h1><spring:message code="searchByPhone"/></h1>
                <form:form cssClass="form-horizontal" action="getByPhone" id="formNewOrder" method="post"
                           commandName="phone">
                    <div class="form-group">
                        <label for="phone" class="col-sm-2 control-label"><spring:message code="order.phone"/></label>
                        <div class="col-sm-10">
                            <input class="form-control" id="phone" name="phone">
                            <form:errors path="phone" cssClass="error-msg"/>
                        </div>
                    </div>
                    <input type="submit" value="<spring:message code="search"/>" class="btn btn-success">
                </form:form>
            </div>
        </div>
            <c:if test="${count <= 0}">
                <h3><spring:message code="order.notFound"/></h3>
            </c:if>
            <c:if test="${count > 0}">
                <div class="panel-group" id="accordion">
                    <c:forEach var="order" items="${ordersData}">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title" data-toggle="collapse" data-parent="#accordion"
                                    data-target="#collapse${order.id}">
                                        <%--<a class="accordion-toggle">--%>
                                    <table id="box-table-b">
                                        <tr>
                                            <th><spring:message code="order.id"/></th>
                                            <th><spring:message code="order.date"/></th>
                                            <th><spring:message code="order.name"/></th>
                                            <th><spring:message code="order.phone"/></th>
                                            <th><spring:message code="order.info"/></th>
                                        </tr>
                                        <tr>
                                            <td>${order.id}</td>
                                            <td><fmt:formatDate type="both" value="${order.date}"/></td>
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
                                    <c:forEach var="info" items="${order.listGood}">
                                        <div class="col-xs-12 col-md-4 col-lg-4 cart">
                                            <table id="table-info">
                                                <tr>
                                                    <td>
                                                        <a class="single_image"
                                                           href="<c:url value="/images/${info.id}_0.jpg"/>">
                                                            <img
                                                                    class="miniImg"
                                                                    src="<c:url value="/images/${info.id}_0_mini.jpg"/>"
                                                                    alt="${info.firm} ${info.category.uaText} ${info.color.uaText}"/>
                                                        </a>
                                                    </td>
                                                    <td>
                                                        <ul type="none">
                                                            <li>
                                                                <span class="spanTh">Код:</span>
                                                                <span><c:out value="${info.id}"/></span>
                                                            </li>
                                                            <li>
                                                                <span class="spanTh"><spring:message code="good.price"/>:</span>
                                                                <span><c:out value="${info.price}"/>грн</span>
                                                            </li>
                                                            <li>
                                                                <span class="spanTh"><spring:message
                                                                        code="good.firm"/>:</span>
                                                                <span><c:out value="${info.firm}"/></span>
                                                            </li>
                                                            <li>
                                                                <span class="spanTh"><spring:message code="good.color"/>: </span>
                                                                <c:if test="${lang == 'uk'}">
                                                                    <span><c:out value="${info.color.uaText}"/></span>
                                                                </c:if>
                                                                <c:if test="${lang == 'ru'}">
                                                                    <span><c:out value="${info.color.ruText}"/></span>
                                                                </c:if>
                                                            </li>
                                                            <li>
                                                                <span class="spanTh"><spring:message
                                                                        code="good.type"/>: </span>
                                                                <c:if test="${lang == 'uk'}">
                                                                    <span><c:out
                                                                            value="${info.category.uaText}"/></span>
                                                                </c:if>
                                                                <c:if test="${lang == 'ru'}">
                                                                    <span><c:out
                                                                            value="${info.category.ruText}"/></span>
                                                                </c:if>
                                                            </li>
                                                            <li><span class="spanTh"><spring:message
                                                                    code="good.size"/>:</span>
                                                                <span><c:out value="${info.size}"/></span>
                                                            </li>
                                                        </ul>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2">
                                                        <hr>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <c:if test="${lang == 'uk'}">
                                                        <td colspan="2" id="text-info" class="bottomTd">
                                                            <p>
                                                                <xml><c:out value="${info.description.uaText}"/></xml>
                                                            </p>
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${lang == 'ru'}">
                                                        <td colspan="2" id="text-info" class="bottomTd">
                                                            <p>
                                                                <xml><c:out value="${info.description.ruText}"/></xml>
                                                            </p>
                                                        </td>
                                                    </c:if>
                                                </tr>
                                            </table>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>

    </div>
    <jsp:include page="footer.jsp"/>
    <script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />" type="text/javascript"></script>
    <script type="text/javascript">
        jQuery(function ($) {
            $("#phone").mask("(999) 999-99-99");
        });
    </script>

</div>
</body>
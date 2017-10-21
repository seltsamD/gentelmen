<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 19.10.2016
  Time: 13:39
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div class="container">
    <jsp:include page="header.jsp"/>
    <div class="mainContent">
        <div class="row">
        <div class="whiteBack col-xs-12 col-md-4 col-lg-4 ">
            <h1><spring:message code="newOrder"/></h1>
        </div>
        <c:if test="${countInBasket <= 0}">
            <div class="whiteBack"><h3><spring:message code="basketEmpty"/></h3></div>
        </c:if>
        <c:if test="${countInBasket > 0}">

            <div class="topBox col-xs-12 col-md-4 col-lg-4 ">
                <form:form cssClass="form-horizontal" action="newOrder" id="formNewOrder" method="post"
                           commandName="order">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label"><spring:message code="order.name"/></label>
                        <div class="col-sm-10">
                            <form:input path="name" cssClass="form-control"/><form:errors path="name"
                                                                                          cssClass="error-msg"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phone" class="col-sm-2 control-label"><spring:message code="order.phone"/></label>
                        <div class="col-sm-10">
                            <input class="form-control" type="text" id="phone" name="phone">
                            <form:errors path="phone" cssClass="error-msg"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="info" class="col-sm-2 control-label"><spring:message code="order.info"/> </label>
                        <div class="col-sm-10">
                            <form:textarea path="info" cssClass="form-control"/>
                            <form:errors path="info" cssClass="error-msg"/>
                        </div>
                    </div>
                    <input type="submit" value="<spring:message code="newOrder"/>" class="btn btn-success">
                </form:form>
            </div>

            <c:forEach var="info" items="${listBasket}">
                <div class="col-xs-12 col-md-4 col-lg-4 cart">
                    <table id="table-info">

                    <tr>
                        <td><a class="single_image" href="<c:url value="/images/${info.id}_0.jpg"/>"><img
                                class="miniImg" src="<c:url value="/images/${info.id}_0_mini.jpg"/>"
                                alt="${info.firm} ${info.categoryName} ${info.colorName}"/></a>
                        </td>
                        <td>
                            <ul type="none">
                                <li><span class="spanTh">Код:</span><span><c:out value="${info.id}"/></span></li>
                                <li><span class="spanTh"><spring:message code="good.price"/>:</span><span><c:out value="${info.price}"/>грн</span></li>
                                <li><span class="spanTh"><spring:message code="good.firm"/>:</span><span><c:out value="${info.firm}"/></span></li>
                                <li><span class="spanTh"><spring:message code="good.color"/>: </span>
                                    <span><c:out value="${info.colorName}"/></span>
                                </li>
                                <li><span class="spanTh"><spring:message code="good.type"/>: </span>
                                    <span><c:out value="${info.categoryName}"/></span>
                                </li>
                                <li><span class="spanTh"><spring:message code="good.size"/>:</span><span><c:out value="${info.size}"/></span></li>


                            </ul>
                    </td>
                    </tr>
                        <tr>
                            <td colspan="2"><a href="${pageContext.request.contextPath}/${lang}/shopping-cart/deleteFromBasket?id=${info.id}"><spring:message code="basket.delete"/>
                            </td>
                        </tr>
                    <tr>
                        <td colspan="2">
                            <hr>
                        </td>
                    </tr>

                    <tr>
                            <td colspan="2" id="text-info" class="bottomTd">
                                <p>
                                    <xml><c:out value="${info.description}"/></xml>
                                </p>
                            </td>
                    </tr>
                </table>
                    </div>
            </c:forEach>

        </c:if>
    </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
<script src="<c:url value="/resources/js/jquery-2.1.4.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />" type="text/javascript"></script>
<script type="text/javascript">
    jQuery(function($){
        $("#phone").mask("(999) 999-99-99");
    });
</script>
</body>


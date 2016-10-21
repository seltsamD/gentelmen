<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 19.10.2016
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<jsp:include page="footer.jsp" />
<script src="<c:url value="${pageContext.request.contextPath}/resources/js/app.js" />" type="text/javascript"></script>

<body>

<div class="container">
    <div class="main">
        <c:if test="${countInBasket <= 0}">
            <div class="whiteBack">  <h3><spring:message code="basketEmpty"/></h3> </div>
        </c:if>
        <c:if test="${countInBasket > 0}">


<table id="box-table-b">
    <tr> <th>  </th>
        <th> ID </th>
        <th> <spring:message code="good.firm"/> </th>
        <th> <spring:message code="good.color"/> </th>
        <th> <spring:message code="good.type"/> </th>
        <th> <spring:message code="good.price"/> </th>
        <th> <spring:message code="good.size"/> </th>
        <th> <spring:message code="good.about"/> </th>
        <th></th>
    </tr>
    <%--<c:set var="uk_lang" scope="session" value="uk"/>--%>
    <%--<c:set var="ru_lang" scope="session" value="ru"/>--%>



    <c:forEach var="obj" items="${listBasket}">
        <tr>
            <td>  <img class="miniImg" src = "<c:url value="images/${obj.id}_0.jpg"/>" alt = "${obj.firm} ${obj.category.uaText} ${obj.color.uaText}"/></td>
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


            <td class="right_table"> <a href="${pageContext.request.contextPath}/deleteFromBasket?id=${obj.id}"><spring:message code="form.delete"/> </a>
            </td>
        </tr>
        <tr><td colspan="9"><hr></td> </tr>
    </c:forEach>
</table>
        <div class="whiteBack">
        <form:form action="/newOrder" id="formNewOrder" method="post" commandName="order">
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label"><spring:message code="order.name"/></label>
                    <div class="col-sm-10">
                        <form:input path="name" cssClass="form-control"/><form:errors path="name" cssClass="error-msg"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="phone" class="col-sm-2 control-label"><spring:message code="order.phone"/></label>
                    <div class="col-sm-10">
                        <form:input path="phone" cssClass="form-control"/><form:errors path="phone" cssClass="error-msg"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="info" class="col-sm-2 control-label"><spring:message code="order.info"/> </label>
                    <div class="col-sm-10">
                        <form:textarea path="info"  cssClass="form-control"/>
                        <form:errors path="info" cssClass="error-msg"/>
                    </div>
                </div>
                <input type="submit" value="<spring:message code="newOrder"/>"  class="btn btn-success">
            </form:form>
 </div>


            </c:if>
</div>
</div>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 20.10.2016
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<jsp:include page="footer.jsp" />
<script src="<c:url value="${pageContext.request.contextPath}/resources/js/app.js" />" type="text/javascript"></script>

<body>

<div class="container">
    <div class="main">
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
                                    <%--<td>${order.date}</td>--%>
                                        <%--<td><javatime:parseLocalDateTime value="${order.date}" style="MS" /></td>--%>
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
        <%--<table id="box-table-b">--%>
    <%--<c:forEach var="order" items="${ordersData}">--%>
            <%--<tr>--%>
                <%--<th> <spring:message code="order.id"/> </th>--%>
                <%--<th> <spring:message code="order.date"/>  </th>--%>
                <%--<th> <spring:message code="order.name"/> </th>--%>
                <%--<th> <spring:message code="order.phone"/>  </th>--%>
                <%--<th> <spring:message code="order.info"/>  </th>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>${order.id}</td>--%>
                <%--<td>${order.date}</td>--%>
                <%--&lt;%&ndash;<td><javatime:parseLocalDateTime value="${order.date}" style="MS" /></td>&ndash;%&gt;--%>
                <%--<td>${order.name}</td>--%>
                <%--<td>${order.phone}</td>--%>
                <%--<td>${order.info}</td>--%>
            <%--</tr>--%>
        <%--<tr>--%>
        <%--<td colspan="5">--%>
            <%--<div>--%>
                <%--<c:forEach var="obj" items="${order.listGood}">--%>
                    <%--<table class="sub-info">--%>
                        <%--<tr>--%>
                            <%--<td>  <img class="miniImg" src = "<c:url value="images/${obj.id}_0.jpg"/>" alt = "${obj.firm} ${obj.category.uaText} ${obj.color.uaText}"/></td>--%>
                            <%--<td> <c:out value="${obj.id}"/> </td>--%>
                            <%--<td> <c:out value="${obj.firm}"/> </td>--%>
                            <%--<c:if test="${lang_code == 'uaText'}">--%>
                                <%--<td> <c:out value="${obj.color.uaText}"/> </td>--%>
                                <%--<td> <c:out value="${obj.category.uaText}"/> </td>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${lang_code == 'ruText'}">--%>
                                <%--<td> <c:out value="${obj.color.ruText}"/> </td>--%>
                                <%--<td> <c:out value="${obj.category.ruText}"/> </td>--%>
                            <%--</c:if>--%>


                            <%--<td> <c:out value="${obj.price}"/> </td>--%>
                            <%--<td> <c:out value="${obj.size}"/> </td>--%>

                            <%--<c:if test="${lang_code == 'uaText'}">--%>
                                <%--<td> <c:out value="${obj.description.uaText}"/> </td>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${lang_code == 'ruText'}">--%>
                                <%--<td> <c:out value="${obj.description.ruText}"/> </td>--%>
                            <%--</c:if>--%>
                        <%--</tr>--%>
                    <%--</table>--%>
                <%--</c:forEach>--%>
            <%--</div>--%>
        <%--</td>--%>
        <%--</tr>--%>
   <%--</c:forEach>--%>
    <%--</table>--%>
    </div>

    </div>


</body>
</html>

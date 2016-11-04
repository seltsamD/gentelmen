<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 02.11.2016
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table id="box-table-b" class="good-info">
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
    </tr></thead>
    <%--<c:set var="uk_lang" scope="session" value="uk"/>--%>
    <%--<c:set var="ru_lang" scope="session" value="ru"/>--%>
    <tbody>
    <c:forEach var="obj" items="${allData}">
        <tr>
            <td> <a class="single_image" href="<c:url value="/images/${obj.id}_0.jpg"/>"><img class="miniImg" src="<c:url value="/images/${obj.id}_0.jpg"/>" alt="${obj.firm} ${obj.category.uaText} "${obj.color.uaText}"/></a>
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


            <td class="right_table"> <a href="${pageContext.request.contextPath}/admin/deleteGood?id=${obj.id}"><spring:message code="form.delete"/> </a> |
                <a href="${pageContext.request.contextPath}/admin/goodById?id=${obj.id}"><spring:message code="form.edit"/></a> |
                <a href="${pageContext.request.contextPath}/admin/goodInfo?id=${obj.id}"><spring:message code="form.info"/></a>
            </td>
        </tr>
        <tr><td colspan="9"><hr></td> </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

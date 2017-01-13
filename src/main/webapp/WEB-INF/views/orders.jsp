<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 20.10.2016
  Time: 20:31
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/resources/css/jquery-ui.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/jquery.fancybox.css" />" rel="stylesheet">
    <script  src="<c:url value="/resources/js/jquery-2.1.4.min.js" />" type="text/javascript"></script>

    <script   src="<c:url value="/resources/js/bootstrap.min.js" />" type="text/javascript"></script>

    <script   src="<c:url value="/resources/js/jquery.fancybox.pack.js" />" type="text/javascript"></script>
    <script   src="<c:url value="/resources/js/jquery-ui.js" />" type="text/javascript"></script>
    <script   src="<c:url value="/resources/js/app.js" />" type="text/javascript"></script>
</head>

<body>
<jsp:include page="header.jsp" />
<div class="container">
    <div class="main">
        <div class="panel-group" id="accordion">
        <c:forEach var="order" items="${ordersData}">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title" data-toggle="collapse" data-parent="#accordion" data-target="#collapse${order.id}">
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
                                    <td> <c:out value="${obj.uaText}"/> </td>
                                </c:if>
                                <c:if test="${lang_code == 'ruText'}">
                                    <td> <c:out value="${obj.ruText}"/> </td>
                                </c:if>
                            </tr>
                            <tr><td colspan="9"><hr></td> </tr>

                </c:forEach>
                        </tbody>
                        </table>
                        <div id="text${order.id}">
                            <button class="btn-success" onclick="changeStatus(${order.id}) ">Виконано</button>
                        </div>

                    </div>
                </div>
            </div>
        </c:forEach>
        </div>

    </div>

    </div>

<script type="text/javascript">
    function changeStatus (id) {
        $.ajax({
            contentType : 'application/json; charset=utf-8',
            type : "GET",
            url : "/order/api/changeStatus",
            dataType : 'json',

            success : function(data) {
                $('#text'.id).text("Статус змінено");

            },
            error: function (xhr, ajaxOptions, thrownError) {alert("ERROR:" + xhr.responseText+" - "+thrownError)},

            done : function(e) {
                console.log("DONE");
            }
        });
    }

</script>

</body>
</html>

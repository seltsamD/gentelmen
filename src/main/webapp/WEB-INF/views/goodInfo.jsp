<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 30.09.2016
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<jsp:include page="footer.jsp" />
<script src="<c:url value="${pageContext.request.contextPath}/resources/js/app.js" />" type="text/javascript"></script>

<div class="container">
    <div class="main">

        <div class="topBox">
            <div class="whiteBack">
                <h1><spring:message code="good"/></h1>
            </div>

            <div class="row">

                <div class="col-xs-4">
            <div id="carousel-example-generic" class="carousel slide back" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <c:forEach var="i" begin="1" end="${info.countImg -1}">
                        <li data-target="#carousel-example-generic" data-slide-to="${i}"></li>
                    </c:forEach>
                </ol>
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <a class="single_image" href="<c:url value="/images/${info.id}_0.jpg"/>"><img  class="maxiImg " src="<c:url value="/images/${info.id}_0.jpg"/>" alt="${info.firm} ${info.category.uaText} "${info.color.uaText}"/></a>
                    </div>
                    <c:forEach var="i" begin="1" end="${info.countImg -1}">
                        <div class="item">
                            <a class="single_image" href="<c:url value="/images/${info.id}_${i}.jpg"/>"><img  class="maxiImg " src="<c:url value="/images/${info.id}_${i}.jpg"/>" alt="${info.firm} ${info.category.uaText} "${info.color.uaText}"/></a></td>
                        </div>
                    </c:forEach>

                    </div>



                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
                </div>




                 <%--<table>--%>
                 <%--<tr>--%>
                     <%--<td>--%>
                         <%--<a id="single_image" href="<c:url value="images/${info.id}_0.jpg"/>"><img  class="maxiImg " src="<c:url value="images/${info.id}_0.jpg"/>" alt="${info.firm} ${info.category.uaText} "${info.color.uaText}"/></a>--%>
                         <%--&lt;%&ndash;<img class="maxiImg left" src = "<c:url value="images/${info.id}_0.jpg"/>" alt = "${info.firm} ${info.category.uaText} "${info.color.uaText}">&ndash;%&gt;--%>

                  <%--<td>--%>
                <div class="col-xs-8">
                <table id="table-info">
                    <tr>
                        <td colspan="2"><h2><c:out value="${info.firm}"/></h2></td>
                    </tr>

                    <tr>
                        <td class="price" colspan="2"><h3><c:out value="${info.price}"/> грн.</h3></td>
                    </tr>

                    <tr><td colspan="2"><hr></td> </tr>
                    <tr>
                        <th> Код: </th>
                        <td><c:out value="${info.id}"/> </td>
                    </tr>

                    <tr>
                        <th> <spring:message code="good.color"/>: </th>
                        <c:if test="${lang_code == 'uaText'}">
                            <td><c:out value="${info.color.uaText}"/> </td>
                        </c:if>
                        <c:if test="${lang_code == 'ruText'}">
                            <td> <c:out value="${info.color.ruText}"/> </td>
                        </c:if>
                    </tr>
                    <tr>
                        <th> <spring:message code="good.type"/>: </th>
                            <c:if test="${lang_code == 'uaText'}">
                                <td><c:out value="${info.category.uaText}"/> </td>
                            </c:if>
                            <c:if test="${lang_code == 'ruText'}">
                                <td> <c:out value="${info.category.ruText}"/> </td>
                            </c:if>
                    </tr>

                    <tr>
                        <th> <spring:message code="good.size"/>: </th>
                        <td> <c:out value="${info.size}"/> </td>
                    </tr>
                    <tr><td colspan="2"><hr></td> </tr>
                    <tr>

                        <c:if test="${lang_code == 'uaText'}">
                            <td colspan="2"> <c:out value="${info.description.uaText}"/> </td>
                        </c:if>
                        <c:if test="${lang_code == 'ruText'}">
                            <td colspan="2"> <c:out value="${info.description.ruText}"/> </td>
                        </c:if>
                    </tr>
                </table>
            </div>
                </div>
        <%--<table>--%>
            <%--<tr>--%>

                <%--<c:forEach var="i" begin="1" end="${info.countImg -1}">--%>
                  <%--<td><a id="single_image2" href="<c:url value="images/${info.id}_${i}.jpg"/>"><img  class="maxiImg " src="<c:url value="images/${info.id}_${i}.jpg"/>" alt="${info.firm} ${info.category.uaText} "${info.color.uaText}"/></a></td>--%>
                <%--</c:forEach>--%>
            <%--</tr>--%>
        <%--</table>--%>
    <%--</div>--%>
    </div>
</div>
</div>


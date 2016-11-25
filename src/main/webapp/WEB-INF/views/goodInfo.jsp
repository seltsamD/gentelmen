<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 30.09.2016
  Time: 22:20
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<meta name="viewport" content="width=device-width, initial-scale=1">

<html>
<head>
    <meta name="google-site-verification" content="-MD2HrSDLr7JKWM9xnqx5OiPG7Uio20xytJhg4iICqc" />
    <c:if test="${lang == 'ua'}">
        <title>Купити <c:out value="${info.category.uaText}"/> <c:out value="${info.firm}"/> <c:out value="${info.color.uaText}"/> у інтернет-магазині джентльмен.in.ua</title>
    </c:if>
    <c:if test="${lang == 'ru'}">
        <title>Купить <c:out value="${info.category.ruText}"/> <c:out value="${info.firm}"/> <c:out value="${info.color.ruText}"/> в интернет-магазине джентльмен.in.ua</title>
    </c:if>

    <script  src="<c:url value="${pageContext.request.contextPath}/resources/js/jquery-2.1.4.min.js" />" type="text/javascript"></script>

    <script   src="<c:url value="/resources/js/app.js" />" type="text/javascript"></script>
    <link href="<c:url value="/resources/css/jquery.fancybox.css" />" rel="stylesheet">
    <script   src="<c:url value="/resources/js/bootstrap.min.js" />" type="text/javascript"></script>
    <script   src="<c:url value="/resources/js/jquery.fancybox.pack.js" />" type="text/javascript"></script>
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

    <article>



            <div class="row infoGood">

                <div class="col-xs-4 top">
                    <div id="carousel-example-generic" class="carousel slide maxiImg" data-ride="carousel">
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

                <div class="col-xs-6">

                    <div class="whiteBack">
                        <c:if test="${lang == 'ua'}">
                            <h1><c:out value="${info.category.uaText}"/> <c:out value="${info.firm}"/></h1>
                        </c:if>
                        <c:if test="${lang == 'ru'}">
                            <h1><c:out value="${info.category.ruText}"/> <c:out value="${info.firm}"/>></h1>
                        </c:if>

                    </div>
                    <table id="table-info">


                        <tr>
                            <td class="price" colspan="2"> <h3><spring:message code="good.price"/>: <c:out value="${info.price}"/> грн.</h3></td>
                        </tr>
                        <tr>
                            <td>
                                <form:form id="baskForm${info.id}" action="addToBasket" method="POST" cssStyle="margin-left: 20px;">
                                    <input type="hidden" name="goodId" value="${info.id}">
                                    <input type="button" onclick="tobasket(${info.id})" value="<spring:message code="basket.add"/>" id="btn-basket-add" class="btn btn-success">
                                </form:form>
                            </td>
                        </tr>

                        <tr><td colspan="2"><hr></td> </tr>
                        <tr>
                            <th><spring:message code="good.firm"/></th>
                            <td><c:out value="${info.firm}"/></td>
                        </tr>
                        <tr>
                            <th> Код: </th>
                            <td><c:out value="${info.id}"/> </td>
                        </tr>

                        <tr>
                            <th> <spring:message code="good.color"/>: </th>
                            <c:if test="${lang == 'ua'}">
                                <td><c:out value="${info.color.uaText}"/> </td>
                            </c:if>
                            <c:if test="${lang == 'ru'}">
                                <td> <c:out value="${info.color.ruText}"/> </td>
                            </c:if>
                        </tr>
                        <tr>
                            <th> <spring:message code="good.type"/>: </th>
                                <c:if test="${lang == 'ua'}">
                                    <td><c:out value="${info.category.uaText}"/> </td>
                                </c:if>
                                <c:if test="${lang == 'ru'}">
                                    <td> <c:out value="${info.category.ruText}"/> </td>
                                </c:if>
                        </tr>

                        <tr>
                            <th> <spring:message code="good.size"/>: </th>
                            <td> <c:out value="${info.size}"/> </td>
                        </tr>
                        <tr><td colspan="2"><hr></td> </tr>
                        <tr>

                            <c:if test="${lang == 'ua'}">
                                <td colspan="2" class="bottomTd"> <c:out value="${info.uaText}"/> </td>
                            </c:if>
                            <c:if test="${lang == 'ru'}">
                                <td colspan="2" class="bottomTd"> <c:out value="${info.ruText}"/> </td>
                            </c:if>
                        </tr>
                    </table>
            </div>
                </div>

    </article>

</div>

<jsp:include page="footer.jsp" />
</body>
</html>


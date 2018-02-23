<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 30.09.2016
  Time: 22:20
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>


<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="google-site-verification" content="-MD2HrSDLr7JKWM9xnqx5OiPG7Uio20xytJhg4iICqc"/>
    <title>${title}</title>
    <meta property="og:title" content="${title}"/>
    <meta property="og:type" content="website"/>
    <meta property="og:url" content="${href}"/>
    <meta property="og:image" content="http://xn--d1acac0agfd5bxg.in.ua/images/${info.id}_0.jpg"/>
    <meta property="og:site_name" content="джентльмен.in.ua"/>
    <meta name="description" content="${description}"/>
    <link rel="alternate" hreflang="${alternativeLang}" href="${alternativeHref}"/>

    <c:if test="${info.status == 0}">
        <meta name="robots" content="noindex">
    </c:if>


</head>
<body>
<div class="container">

    <jsp:include page="header.jsp"/>
    <div class="mainContent">

        <c:forEach var="info" items="${allData}">
            <article class="product">
                <div class="row">
                    <div itemscope itemtype="http://schema.org/Product">
                        <div class="whiteBack">
                            <a href="<c:out value="${info.href}"/>"><p itemprop="name"><c:out value="${info.name}"/></p>
                            </a>
                        </div>
                        <div class="item">
                            <img
                                    class="miniImg"
                                    src="<c:url value="http://xn--d1acac0agfd5bxg.in.ua/images/${info.id}_0_mini.jpg"/>"
                                    alt="${info.name} ${info.firm}"/>
                        </div>
                        <div class="col-xs-12 col-md-5 col-lg-6">

                            <table id="table-info">
                                <tr class="form-inline">
                                    <td id="infoTab" class="price" colspan="2">
                                        <h3 itemprop="offers" itemscope
                                            itemtype="http://schema.org/Offer">
                                            <spring:message code="good.price"/>:

                        <span itemprop="price" content="<c:out value="${info.price}"/>"><c:out
                                value="${info.price}"/></span>
                                            <span itemprop="priceCurrency" content="UAH">грн</span>
                                            <link itemprop="availability" href="http://schema.org/InStock">
                                        </h3>
                                    </td>
                                </tr>
                                <c:if test="${info.status == 1}">
                                    <tr class="form-inline">
                                        <td id="infoTab">
                                            <form:form id="baskForm${info.id}" action="addToBasket" method="POST">
                                                <input type="hidden" id="goodId" name="goodId" value="${info.id}">
                                                <input type="button" onclick="tobasket(${info.id})"
                                                       value="<spring:message code="basket.add"/>" id="btn-basket-add"
                                                       class="btn btn-success">
                                            </form:form>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${info.status == 0}">
                                    <tr class="form-inline">
                                        <td>
                                            <img src="<c:url value="/resources/images/sold.png"/>">
                                        </td>
                                    </tr>
                                </c:if>

                                <tr>
                                    <td colspan="2">
                                        <hr>
                                    </td>
                                </tr>
                                <tr>
                                    <th><spring:message code="good.firm"/></th>
                                    <td itemprop="brand"><c:out value="${info.firm}"/></td>
                                </tr>
                                <tr>
                                    <th> Код:</th>
                                    <td><c:out value="${info.id}"/></td>
                                </tr>

                                <tr>
                                    <th><spring:message code="good.color"/>:</th>
                                    <td itemprop="color"><c:out value="${info.colorName}"/></td>
                                </tr>
                                <tr>
                                    <th><spring:message code="good.type"/>:</th>
                                    <td><c:out value="${info.categoryName}"/></td>
                                </tr>

                                <tr>
                                    <th><spring:message code="good.size"/>:</th>
                                    <td><c:out value="${info.size}"/></td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <hr>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" id="text-info" class="bottomTd">
                                        <p>
                                        <p itemprop="description" style="white-space: pre-line;">
                                            <c:out value="${info.description}"/>
                                        </p>
                                        </p>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </article>
        </c:forEach>
    </div>
</div>

</div>
<jsp:include page="footer.jsp"/>
</body>

<script>
    (function (i, s, o, g, r, a, m) {
        i['GoogleAnalyticsObject'] = r;
        i[r] = i[r] || function () {
                    (i[r].q = i[r].q || []).push(arguments)
                }, i[r].l = 1 * new Date();
        a = s.createElement(o),
                m = s.getElementsByTagName(o)[0];
        a.async = 1;
        a.src = g;
        m.parentNode.insertBefore(a, m)
    })(window, document, 'script', 'https://www.google-analytics.com/analytics.js', 'ga');

    ga('create', 'UA-87631623-1', 'auto');
    ga('send', 'pageview');

</script>
</html>


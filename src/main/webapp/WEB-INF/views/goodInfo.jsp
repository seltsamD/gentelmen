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
    <title>${title}</title>
    <meta property="og:title" content="${title}"/>
    <meta property="og:type" content="website" />
    <meta property="og:url" content="${href}" />
    <meta property="og:image" content="http://xn--d1acac0agfd5bxg.in.ua/images/${info.id}_0.jpg" />
    <meta property="og:site_name" content="джентльмен.in.ua" />
    <meta name="description" content="${description}"/>
    <link rel="alternate" hreflang="${alternativeLang}" href="${alternativeHref}"/>

    <c:if test="${info.status == 0}">
        <meta name="robots" content="noindex">
    </c:if>


</head>
<body>
<div class="container">

    <jsp:include page="header.jsp" />
    <div class="mainContent">


    <article class="product">
     <div class="row">
                <div  itemscope itemtype="http://schema.org/Product">
                    <div class="whiteBack">
                        <c:if test="${lang == 'uk'}">
                            <h1 itemprop="name"><c:out value="${info.category.uaText}"/> <c:out value="${info.firm}"/></h1>
                        </c:if>
                        <c:if test="${lang == 'ru'}">
                            <h1 itemprop="name"><c:out value="${info.category.ruText}"/> <c:out value="${info.firm}"/></h1>
                        </c:if>

                    </div>
                <div class="col-xs-12  col-md-7 col-lg-6 ">
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
                </div>

                        <%--<div class="col-xs-12 col-md-5 col-lg-6 infoGood">--%>
                        <%--<h3 itemprop="offers" itemscope itemtype="http://schema.org/Offer">--%>
                            <%--<spring:message code="good.price"/>--%>
                             <%--<span itemprop="price" content="<c:out value="${info.price}"/>"><c:out value="${info.price}"/></span>--%>
                            <%--<span itemprop="priceCurrency" content="UAH">грн</span>--%>
                         <%--</h3>--%>
                        <%--<c:if test="${info.status == 1}">--%>
                            <%--<form:form id="baskForm${info.id}" action="addToBasket" method="POST">--%>
                                <%--<input type="hidden" name="goodId" value="${info.id}">--%>
                                <%--<input type="button" onclick="tobasket(${info.id})" value="<spring:message code="basket.add"/>" id="btn-basket-add" class="btn btn-success">--%>
                            <%--</form:form>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${info.status == 0}">&ndash;%&gt;--%>
                            <%--<img src="<c:url value="/resources/images/sold.png"/>" >--%>
                        <%--</c:if>--%>
                        <%--<ul class="goodInfoList">--%>
                            <%--<li><span class="desc">ID</span><c:out value="${info.id}"/></li>--%>
                            <%--<li itemprop="brand" itemscope itemtype="http://schema.org/Brand">--%>
                                <%--<span class="desc"><spring:message code="good.firm"/></span>--%>
                                <%--<span class="desc" itemprop="name"><c:out value="${info.firm}"/></span>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<span class="desc"><spring:message code="good.color"/></span>--%>
                                <%--<c:if test="${lang == 'uk'}">--%>
                                <%--<span  class="desc" itemprop="color"><c:out value="${info.color.uaText}"/> </span>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${lang == 'ru'}">--%>
                                <%--<span  class="desc" itemprop="color"> <c:out value="${info.color.ruText}"/> </span>--%>
                                <%--</c:if>--%>
                            <%--</li>--%>
                        <%--</ul>--%>
                    <%--</div>--%>

         <%--<div class="col-xs-12 col-md-5 col-lg-6 infoGood">--%>
                <div  class="col-xs-12 col-md-5 col-lg-6" itemscope itemtype="http://schema.org/Product" class="col-xs-6">


                    <table id="table-info">


                        <tr>
                            <td id="infoTab" class="price" colspan="2"> <h3 itemprop="offers" itemscope itemtype="http://schema.org/Offer">
                                <spring:message code="good.price"/>:

                                <span itemprop="price" content="<c:out value="${info.price}"/>"><c:out value="${info.price}"/></span>
                                <span itemprop="priceCurrency" content="UAH">грн</span>
                            </h3></td>
                        </tr>
                        <c:if test="${info.status == 1}">
                            <tr>
                                <td  id="infoTab">
                                    <form:form id="baskForm${info.id}" action="addToBasket" method="POST">
                                        <input type="hidden" name="goodId" value="${info.id}">
                                        <input type="button" onclick="tobasket(${info.id})" value="<spring:message code="basket.add"/>" id="btn-basket-add" class="btn btn-success">
                                    </form:form>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${info.status == 0}">
                            <tr>
                                <td>
                                    <img src="<c:url value="/resources/images/sold.png"/>" >
                                </td>
                            </tr>
                        </c:if>

                        <tr><td colspan="2"><hr></td> </tr>
                        <tr itemprop="brand" itemscope itemtype="http://schema.org/Brand">
                            <th><spring:message code="good.firm"/></th>
                            <td itemprop="name"><c:out value="${info.firm}"/></td>
                        </tr>
                        <tr>
                            <th> Код: </th>
                            <td><c:out value="${info.id}"/> </td>
                        </tr>

                        <tr>
                            <th> <spring:message code="good.color"/>: </th>
                            <c:if test="${lang == 'uk'}">
                                <td itemprop="color"><c:out value="${info.color.uaText}"/> </td>
                            </c:if>
                            <c:if test="${lang == 'ru'}">
                                <td itemprop="color"> <c:out value="${info.color.ruText}"/> </td>
                            </c:if>
                        </tr>
                        <tr>
                            <th> <spring:message code="good.type"/>: </th>
                                <c:if test="${lang == 'uk'}">
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

                            <c:if test="${lang == 'uk'}">
                                <td colspan="2" id="text-info" class="bottomTd"><p itemprop="description"><xml><c:out value="${info.uaText}"/></xml></p></td>
                            </c:if>
                            <c:if test="${lang == 'ru'}"><p>
                               <td colspan="2" id="text-info" class="bottomTd"><p itemprop="description"> <xml><c:out value="${info.ruText}"/></xml></p</td>
                            </c:if>
                        </tr>
                    </table>
            </div>


    </article>

</div>

</div>
<jsp:include page="footer.jsp" />


</body>
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
</html>


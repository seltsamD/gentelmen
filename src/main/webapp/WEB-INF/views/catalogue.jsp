<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 22.10.2016
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%--<c:if test="${lang == 'uk'}">--%>
        <%--<title> Каталог чоловічого одягу та аксесуарів у інтернет-магазині джентльмен.in.ua</title>--%>
    <%--</c:if>--%>
    <%--<c:if test="${lang == 'ru'}">--%>
        <%--<title>Каталог мужской одежды и аксессуаров в интернет-магазине джентльмен.in.ua</title>--%>
    <%--</c:if>--%>
        <title>${title}</title>
        <meta name="description" content="${description}">
    <link rel="alternate" hreflang="${alternativeLang}" href="${alternativeHref}"/>




    <script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
                (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-87631623-1', 'auto');
    ga('send', 'pageview');

</script>
    <script type="text/javascript">

        $(function() {
            $( "#slider-range" ).slider({
                range: true,
                min: ${minPrice},
                max: ${maxPrice},
                values: [ ${minPrice}, ${maxPrice} ],
                slide: function( event, ui ) {
                    $( "#amount" ).html(ui.values[ 0 ] + "грн. - " + ui.values[ 1 ]+ "грн." );
                    $( "#amount1" ).val(ui.values[ 0 ]);
                    $( "#amount2" ).val(ui.values[ 1 ]);
                }
            });
            $( "#amount" ).html( $( "#slider-range" ).slider( "values", 0 ) +
                    "грн. - " + $( "#slider-range" ).slider( "values", 1 ) )+"грн.";
        });
    </script>
</head>
<body>
<jsp:include page="header.jsp" />
<div class="container">


        <div class="row">
            <div class="main">
            <div class="col-sm-3">
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingNull">
                            <h4 class="panel-title">
                                <a href="${pageContext.request.contextPath}/${lang}/каталог">   <spring:message code="good.all"/>
                                    </a>

                            </h4>
                        </div>

                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingOne">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    <spring:message code="category"/>
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                            <div class="panel-body">
                                    <ul>
                                        <c:forEach var="cat" items="${firstLevel}">

                                            <c:if test="${lang == 'uk'}">
                                                <li>
                                                    <c:out value="${cat.uaText}"/>
                                                    <ul>
                                                        <c:forEach var="secLevel" items="${secondLevel}">
                                                            <c:if test="${secLevel.parent == cat.id}">
                                                                <li><a href="${pageContext.request.contextPath}/${lang}/каталог/${secLevel.uaText}/${secLevel.id}">
                                                                    <c:out value="${secLevel.uaText}"/></a></li>
                                                                <%--<li> <a href="#" onclick="setParam('cat_id', ${secLevel.id});"><c:out value="${secLevel.uaText}"/></a></li>--%>
                                                            </c:if>
                                                        </c:forEach>
                                                    </ul>
                                                </li>
                                            </c:if>
                                            <c:if test="${lang == 'ru'}">
                                                <li>
                                                    <c:out value="${cat.ruText}"/>
                                                    <ul>
                                                        <c:forEach var="secLevel" items="${secondLevel}">
                                                            <c:if test="${secLevel.parent == cat.id}">
                                                                <li><a href="${pageContext.request.contextPath}/${lang}/каталог/${secLevel.ruText}/${secLevel.id}">
                                                                    <c:out value="${secLevel.ruText}"/></a></li>
                                                                <%--<li> <a href="#" onclick="setParam('cat_id', ${secLevel.id});"><c:out value="${secLevel.ruText}"/></a></li>--%>
                                                            </c:if>
                                                        </c:forEach>
                                                    </ul>
                                                </li>
                                            </c:if>


                                        </c:forEach>
                                    </ul>


                             </div>
                        </div>
                    </div>
                <c:if test="${countSize > 0}">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="heading5">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse5" aria-expanded="false" aria-controls="collapse5">
                                    <spring:message code="size"/>
                                </a>
                            </h4>
                        </div>
                        <div id="collapse5" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading5">
                            <div class="panel-body">
                                <ul>
                                    <c:forEach var="size" items="${listSize}">
                                        <li>
                                                <a href="#" onclick="setParam('size', '${size}');">
                                                    <c:out value="${size}"/></a>
                                        </li>

                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                    </c:if>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingTwo">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    <spring:message code="color"/>
                                </a>
                            </h4>
                        </div>
                        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                            <div class="panel-body">
                                <ul>
                                    <c:forEach var="color" items="${colors}">

                                            <c:if test="${lang == 'uk'}">
                                                <li>
                                               <a href="#" onclick="setParam('color_id', ${color.id});">
                                                <c:out value="${color.uaText}"/></a>
                                                </li>
                                            </c:if>
                                            <c:if test="${lang == 'ru'}">
                                                <li> <a href="#" onclick="setParam('color_id', ${color.id});">
                                                <c:out value="${color.ruText}"/>
                                                </a> </li>   </c:if>

                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingFour">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                    <spring:message code="price_range"/>
                                </a>
                            </h4>
                        </div>
                        <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                            <div class="panel-body">
                                <p id="amount"></p>
                                <div id="slider-range"></div>

                                <%--<form:form id="priceRange" action="/${lang}/catalogue/priceRange" method="POST">--%>
                                    <input type="hidden" id="amount1" name="amount1" />
                                    <input type="hidden" id="amount2" name="amount2" />
                                    <input type="button" onclick="setParamPrice();" name="submit_range" value="<spring:message code="search"/>"/>
                               <%--</form:form>--%>

                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <div class="col-sm-9">

                    <div class="whiteBack">
                        <h1><spring:message code="catalog"/></h1>

                    </div>
            <c:if test="${count <= 0}">
                <h3><spring:message code="good.notFound"/></h3>
            </c:if>
            <c:if test="${count > 0}">
                <section>
                    <div class="row">
                        <c:forEach var="obj" items="${allData}">
                            <article>
                            <div class="col-xs-6 col-sm-3 back" >
                                <div class="infoBox">
                                    <table id="table-index">
                                        <tbody itemscope itemtype="http://schema.org/Product">
                                        <tr>
                                            <th>
                                                <c:if test="${lang == 'uk'}">

                                                    <a href="${pageContext.request.contextPath}/${lang}/good/${obj.category.uaText}-${obj.firm}-${obj.color.uaText}/${obj.id}"><img itemprop="image"  src="<c:url value="/images/${obj.id}_0.jpg"/>" alt="${obj.firm} ${obj.category.uaText} ${obj.color.uaText}"/></a>
                                                </c:if>
                                                <c:if test="${lang == 'ru'}">
                                                    <a href="${pageContext.request.contextPath}/${lang}/good/${obj.category.ruText}-${obj.firm}-${obj.color.ruText}/${obj.id}"><img itemprop="image"  src="<c:url value="/images/${obj.id}_0.jpg"/>" alt="${obj.firm} ${obj.category.ruText} ${obj.color.ruText}"/></a>
                                                </c:if>


                                            </th>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="priceInfo">
                                                    <p>
                                                        <c:if test="${lang == 'uk'}">

                                                            <a href="${pageContext.request.contextPath}/${lang}/good/${obj.category.uaText}-${obj.firm}-${obj.color.uaText}/${obj.id}">
                                                                <span itemprop="name"><c:out value="${obj.category.uaText}"/></span>
                                                <span itemprop="brand" itemscope itemtype="http://schema.org/Brand">
                                                    <span itemprop="name"><c:out value="${obj.firm}"/> </span>
                                                </span>
                                                            </a>
                                                        </c:if>
                                                        <c:if test="${lang == 'ru'}">
                                                            <a href="${pageContext.request.contextPath}/${lang}/good/${obj.category.ruText}-${obj.firm}-${obj.color.ruText}/${obj.id}">
                                                                <span itemprop="name"><c:out value="${obj.category.ruText}"/></span>
                                                <span itemprop="brand" itemscope itemtype="http://schema.org/Brand">
                                                    <span itemprop="name"><c:out value="${obj.firm}"/> </span>
                                                </span>
                                                            </a>
                                                        </c:if>
                                                    </p>
                                       <span itemprop="offers" itemscope itemtype="http://schema.org/Offer">
                                           <p itemprop="price"  class="price">${obj.price}грн.</p>
                                       </span>

                                                    <form:form id="baskForm${obj.id}" action="addToBasket" method="POST">
                                                        <input type="hidden" name="goodId" value="${obj.id}">
                                                        <input type="button" onclick="tobasket(${obj.id})" value="<spring:message code="basket.add"/>" id="btn-basket-add" class="btn btn-success">
                                                    </form:form>
                                                </div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                        <%--<div class="infoBox">--%>

                                </div>

                            </div>
                            </article>
                        </c:forEach>

                        <%--<div class="demo">--%>
                            <%--<div id="demo2">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                <%--<table id="box-table-b" class="good-info">--%>
                    <%--<thead>--%>
                    <%--<tr> <th>  </th>--%>
                        <%--<th> <spring:message code="good.id"/> </th>--%>
                        <%--<th> <spring:message code="good.firm"/> </th>--%>
                        <%--<th> <spring:message code="good.color"/> </th>--%>
                        <%--<th> <spring:message code="good.type"/> </th>--%>
                        <%--<th> <spring:message code="good.price"/> </th>--%>
                        <%--<th> <spring:message code="good.size"/> </th>--%>
                        <%--<th> <spring:message code="good.about"/> </th>--%>

                    <%--</tr></thead>--%>

                    <%--<tbody>--%>
                    <%--<c:forEach var="obj" items="${allData}">--%>
                        <%--<tr>--%>
                            <%--<td> <a class="single_image" href="<c:url value="/images/${obj.id}_0.jpg"/>"><img  class="miniImg"  src="<c:url value="/images/${obj.id}_0.jpg"/>" alt="${obj.firm} ${obj.category.uaText} "${obj.color.uaText}"/></a>--%>

                                <%--<c:if test="${pageContext.request.userPrincipal.name == null}">--%>
                                    <%--<form:form id="baskForm${obj.id}" action="addToBasket" method="POST">--%>
                                        <%--<input type="hidden" name="goodId" value="${obj.id}">--%>
                                        <%--<input type="button" onclick="tobasket(${obj.id})" value="<spring:message code="basket.add"/>" id="btn-basket-add" class="btn btn-success">--%>
                                    <%--</form:form>--%>
                                <%--</c:if>--%>
                            <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
                            <%--</td>--%>
                            <%--<td> <c:out value="${obj.id}"/> </td>--%>
                            <%--<td> <c:out value="${obj.firm}"/> </td>--%>

                            <%--<c:if test="${lang == 'uk'}">--%>
                                <%--<td> <c:out value="${obj.color.uaText}"/> </td>--%>
                                <%--<td> <c:out value="${obj.category.uaText}"/> </td>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${lang == 'ru'}">--%>
                                <%--<td> <c:out value="${obj.color.ruText}"/> </td>--%>
                                <%--<td> <c:out value="${obj.category.ruText}"/> </td>--%>
                            <%--</c:if>--%>


                            <%--<td> <c:out value="${obj.price}"/>грн.</td>--%>
                            <%--<td> <c:out value="${obj.size}"/> </td>--%>

                            <%--<c:if test="${lang == 'uk'}">--%>
                                <%--<td> <c:out value="${obj.uaText}"/> </td>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${lang == 'ru'}">--%>
                                <%--<td> <c:out value="${obj.ruText}"/> </td>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${pageContext.request.userPrincipal.name != null}">--%>
                            <%--<td class="right_table"> <a href="${pageContext.request.contextPath}/admin/deleteGood?id=${obj.id}"><spring:message code="form.delete"/> </a> |--%>
                                <%--<a href="${pageContext.request.contextPath}/admin/goodById?id=${obj.id}"><spring:message code="form.edit"/></a> |--%>
                                <%--<a href="${pageContext.request.contextPath}/admin/goodInfo?id=${obj.id}"><spring:message code="form.info"/></a>--%>
                            <%--</td>--%>
                            <%--</c:if>--%>
                        <%--</tr>--%>
                        <%--<tr><td colspan="9"><hr></td> </tr>--%>
                    <%--</c:forEach>--%>
                    <%--</tbody>--%>
                <%--</table>--%>

                </c:if>
            </div>
        </div>


</div>
</div>
</body>
<link href="<c:url value="/resources/css/jquery-ui.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/jquery.fancybox.css" />" rel="stylesheet">
<script  src="<c:url value="/resources/js/jquery-2.1.4.min.js" />" type="text/javascript"></script>

<script   src="<c:url value="/resources/js/bootstrap.min.js" />" type="text/javascript"></script>

<script   src="<c:url value="/resources/js/jquery.fancybox.pack.js" />" type="text/javascript"></script>
<script   src="<c:url value="/resources/js/jquery-ui.js" />" type="text/javascript"></script>
<script   src="<c:url value="/resources/js/app.js" />" type="text/javascript"></script>


</html>
<style>
    @media
    only screen and (max-width: 760px),
    (min-device-width: 768px) and (max-device-width: 1024px) {
    .good-info td:nth-of-type(1):before { content: ""; }
    .good-info td:nth-of-type(2):before { content: "<spring:message code="good.id"/>"; }
    .good-info td:nth-of-type(3):before { content: "<spring:message code="good.firm"/>"; }
    .good-info td:nth-of-type(4):before { content: "<spring:message code="good.color"/>"; }
    .good-info td:nth-of-type(5):before { content: "<spring:message code="good.type"/>"; }
    .good-info td:nth-of-type(6):before { content: "<spring:message code="good.price"/>"; }
    .good-info td:nth-of-type(7):before { content: "<spring:message code="good.size"/>"; }
     .good-info td:nth-of-type(8):before { content: "<spring:message code="good.about"/>"; }
    }
</style>


<script type="text/javascript">
   function setParam(type, id){
       var url = window.location.href;
       if(url.indexOf('?') == -1)
               url = url + '?'+type+'=' + id;
       else
       if(url.indexOf('?') >= 0)
           url = url + '&'+type+'=' + id;

       window.location.href = url;

   }

   function setParamPrice(){
       var url = window.location.href;
       var price1 = document.getElementById('amount1').value;
       var price2 = document.getElementById('amount2').value;
       if(url.indexOf('?') == -1)
           url = url + '?price1=' + price1+'&price2='+price2;
       else
       if(url.indexOf('?') >= 0)
           url = url + '&price1=' + price1+'&price2='+price2;

       window.location.href = url;

   }

</script>
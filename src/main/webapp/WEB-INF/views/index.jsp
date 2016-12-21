<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 30.09.2016
  Time: 11:28
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<meta name="viewport" content="width=device-width, initial-scale=1">

<html>
<head>
    <meta name="google-site-verification" content="-MD2HrSDLr7JKWM9xnqx5OiPG7Uio20xytJhg4iICqc" />
    <c:if test="${lang == 'uk'}">
        <title>Чоловічий одяг та аксесуари купити у інтернет-магазині джентльмен.in.ua</title>
        <meta name="description" content="Магазин чоловічого одягу, краваток і костюмів. Тільки відомі і перевірені бренди. Кольори, фасони та великий вибір задовольнять навіть найвишуканіший смак.">
    </c:if>
    <c:if test="${lang == 'ru'}">
        <title>Мужская одежда и аксессуары в интернет-магазине джентльмен.in.ua</title>
        <meta name="description" content="Магазин мужской одежды, галстуков и костюмов. Только известные и проверенные бренды. Цвета, фасоны и большой выбор удовлетворят даже самый изысканный вкус.">
    </c:if>

    <link rel="alternate" hreflang="${alternativeLang}" href="${alternativeHref}"/>


    <meta name="yandex-verification" content="3d522b3830a4aa99" />
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
    <div id="page">
        <h1>Свято наближається...!</h1>
    </div>
    <div class="row">
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
    <section>
        <div class="col-sm-9">
        <div class="row index">
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
                      </div>

                    </div>
                </article>
            </c:forEach>

        </div>
            </div>
    </section>
    </div>
</div>

<jsp:include page="footer.jsp" />
</body>
<link href="https://fonts.googleapis.com/css?family=Merriweather|Pattaya|Playfair+Display+SC" rel="stylesheet">
<link href="<c:url value="/resources/css/jquery-ui.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/jquery.fancybox.css" />" rel="stylesheet">

<script  src="<c:url value="/resources/js/jquery-2.1.4.min.js" />" type="text/javascript"></script>

<script   src="<c:url value="/resources/js/bootstrap.min.js" />" type="text/javascript"></script>

<script   src="<c:url value="/resources/js/jquery.fancybox.pack.js" />" type="text/javascript"></script>
<script   src="<c:url value="/resources/js/jquery-ui.js" />" type="text/javascript"></script>
<script   src="<c:url value="/resources/js/app.js" />" type="text/javascript"></script>
</html>
<script type="text/javascript">
    $(function() {

            $('.carousel').carousel({
                interval: 5000
            })
        });

  function setParam(type, id){
                var url = window.location.href;

                    if(url.indexOf('uk/index') == -1 && url.indexOf('ru/index') == -1)
                    {

                        if(url.indexOf('?') == -1)
                            url = url + '${lang}/каталог?'+type+'=' + id;
                        else
                        if(url.indexOf('?') >= 0)
                            url = url + '&'+type+'=' + id;
                    }
                    else
                    {
                        if(url.indexOf('uk') >= 0)
                        {
                            url = url.substr(0, url.indexOf('uk'));
                            if(url.indexOf('?') == -1)
                                url = url + '${lang}/каталог?'+type+'=' + id;
                            else
                            if(url.indexOf('?') >= 0)
                                url = url + '&'+type+'=' + id;
                        }

                        else
                        if(url.indexOf('ru') >= 0)
                        {
                            url = url.substr(0, url.indexOf('uk'));
                            if(url.indexOf('?') == -1)
                                url = url + '${lang}/каталог?'+type+'=' + id;
                            else
                            if(url.indexOf('?') >= 0)
                                url = url + '&'+type+'=' + id;
                        }
                    }




                window.location.href = url;

  }

    function setParamPrice(){
        var url = window.location.href;
        var price1 = document.getElementById('amount1').value;
        var price2 = document.getElementById('amount2').value;
        if(url.indexOf('uk/index') == -1 && url.indexOf('ru/index') == -1)
        {

            if(url.indexOf('?') == -1)
                url = url + '${lang}/каталог?price1=' + price1+'&price2='+price2;
            else
            if(url.indexOf('?') >= 0)
                url = url + '&price1=' + price1+'&price2='+price2;
        }
        else
        {
            if(url.indexOf('uk') >= 0)
            {
                url = url.substr(0, url.indexOf('uk'));
                if(url.indexOf('?') == -1)
                    url = url + '${lang}/каталог?price1=' + price1+'&price2='+price2;
                else
                if(url.indexOf('?') >= 0)
                    url = url + '&price1=' + price1+'&price2='+price2;
            }

            else
            if(url.indexOf('ru') >= 0)
            {
                url = url.substr(0, url.indexOf('uk'));
                if(url.indexOf('?') == -1)
                    url = url + '${lang}/каталог?price1=' + price1+'&price2='+price2;
                else
                if(url.indexOf('?') >= 0)
                    url = url + '&price1=' + price1+'&price2='+price2;
            }
        }


        window.location.href = url;

    }


</script>

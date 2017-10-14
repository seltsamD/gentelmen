<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 30.09.2016
  Time: 11:28
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>


<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="google-site-verification" content="-MD2HrSDLr7JKWM9xnqx5OiPG7Uio20xytJhg4iICqc"/>
    <c:if test="${lang == 'uk'}">
        <title>Чоловічий одяг та аксесуари купити у інтернет-магазині джентльмен.in.ua</title>
        <meta name="description"
              content="Магазин чоловічого одягу, краваток і костюмів. Тільки відомі і перевірені бренди. Кольори, фасони та великий вибір задовольнять навіть найвишуканіший смак.">
    </c:if>
    <c:if test="${lang == 'ru'}">
        <title>Мужская одежда и аксессуары в интернет-магазине джентльмен.in.ua</title>
        <meta name="description"
              content="Магазин мужской одежды, галстуков и костюмов. Только известные и проверенные бренды. Цвета, фасоны и большой выбор удовлетворят даже самый изысканный вкус.">
    </c:if>

    <link rel="alternate" hreflang="${alternativeLang}" href="${alternativeHref}"/>
</head>
<body>

<div class="container">

    <jsp:include page="header.jsp"/>
    <div class="mainContent">
        <div class="container">
            <div class="row">
                <div class="centerDisplay topIndex">
                    <div class="col-lg-2 col-md-3 col-sm-4 col-xs-6  marginImg">
                        <div class="ih-item circle colored effect17">
                            <c:if test="${lang == 'uk'}">
                                <a href="${pageContext.request.contextPath}/${lang}/каталог/чоловікам/краватка/4"/>
                            </c:if>
                            <c:if test="${lang == 'ru'}">
                                <a href="${pageContext.request.contextPath}/${lang}/каталог/мужчинам/галстук/4"/>
                            </c:if>
                            <div class="img"><img src="<c:url value="/images/images/0_mini.jpg"/>" alt="img"></div>
                            <div class="info">
                                <h3><spring:message code="ties"/></h3>
                            </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-3 col-sm-4 col-xs-6 marginImg">
                        <div class="ih-item circle colored effect17">
                            <c:if test="${lang == 'uk'}">
                                <a href="${pageContext.request.contextPath}/${lang}/каталог/чоловікам/новорічний-светр/38"/>
                            </c:if>
                            <c:if test="${lang == 'ru'}">
                                <a href="${pageContext.request.contextPath}/${lang}/каталог/мужчинам/новогодний-свитер/38"/>
                            </c:if>
                                <div class="img"><img src="<c:url value="/images/images/1_mini.jpg"/>" alt="img"></div>
                                <div class="info">
                                    <h3><spring:message code="sweaters"/></h3>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-3 col-sm-4 col-xs-6 marginImg">
                        <div class="ih-item circle colored effect17">
                            <c:if test="${lang == 'uk'}">
                                <a href="${pageContext.request.contextPath}/${lang}/каталог/чоловікам/піджак/5"/>
                            </c:if>
                            <c:if test="${lang == 'ru'}">
                                <a href="${pageContext.request.contextPath}/${lang}/каталог/мужчинам/пиджак/5"/>
                            </c:if>
                                <div class="img"><img src="<c:url value="/images/images/2_mini.jpg"/>" alt="img"></div>
                                <div class="info">
                                    <h3><spring:message code="jacket"/></h3>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-3  col-sm-4 col-xs-6  marginImg">
                        <div class="ih-item circle colored effect17">
                            <c:if test="${lang == 'uk'}">
                                <a href="${pageContext.request.contextPath}/${lang}/каталог/чоловікам/сорочка/3"/>
                            </c:if>
                            <c:if test="${lang == 'ru'}">
                                <a href="${pageContext.request.contextPath}/${lang}/каталог/мужчинам/рубашка/3"/>
                            </c:if>
                                <div class="img"><img src="<c:url value="/images/images/3_mini.jpg"/>" alt="img"></div>
                                <div class="info">
                                    <h3><spring:message code="shirts"/></h3>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-3 col-sm-4 col-xs-6 marginImg">
                        <div class="ih-item circle colored effect17">
                            <c:if test="${lang == 'uk'}">
                                <a href="${pageContext.request.contextPath}/${lang}/каталог/дітям/дитяча-піжама/35"/>
                            </c:if>
                            <c:if test="${lang == 'ru'}">
                                <a href="${pageContext.request.contextPath}/${lang}/каталог/детям/детская-пижама/35"/>
                            </c:if>
                                <div class="img"><img src="<c:url value="/images/images/4_mini.jpg"/>" alt="img"></div>
                                <div class="info">
                                    <h3><spring:message code="pajamas"/></h3>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-3 col-sm-4 col-xs-6 marginImg">
                        <div class="ih-item circle colored effect17">
                            <c:if test="${lang == 'uk'}">
                                <a href="${pageContext.request.contextPath}/${lang}/каталог/жінкам/жіночий-новорічний-светр/44"/>
                            </c:if>
                            <c:if test="${lang == 'ru'}">
                                <a href="${pageContext.request.contextPath}/${lang}/каталог/для-женщин/женский-новогодний-свитер/44"/>
                            </c:if>
                            <div class="img"><img src="<c:url value="/images/images/5_mini.jpg"/>" alt="img"></div>
                            <div class="info">
                                <h3><spring:message code="womenSweaters"/></h3>
                            </div>
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-3 col-sm-4 col-xs-6 marginImg">
                        <div class="ih-item circle colored effect17">
                            <c:if test="${lang == 'uk'}">
                                <a href="${pageContext.request.contextPath}/${lang}/каталог/жінкам/піжами/34"/>
                            </c:if>
                            <c:if test="${lang == 'ru'}">
                                <a href="${pageContext.request.contextPath}/${lang}/каталог/для-женщин/пижамы/34"/>
                            </c:if>
                            <div class="img"><img src="<c:url value="/images/images/6_mini.jpg"/>" alt="img"></div>
                            <div class="info">
                                <h3><spring:message code="womenPajamas"/></h3>
                            </div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <section>
            <div class="goodIndex">
                <h2><spring:message code="ourGoods"/></h2>
            </div>
            <div class="row centerDisplay" id="indexGoods">
            </div>
        </section>
    </div>
        <link href="<c:url value="/resources/css/hover.css" />" rel="stylesheet">
    <jsp:include page="footer.jsp"/>

</body>
<%--<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">--%>


<script src="<c:url value="/resources/js/index.js" />" type="text/javascript"></script>

</html>

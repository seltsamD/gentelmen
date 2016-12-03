<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 03.10.2016
  Time: 11:45

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


    <link href="https://fonts.googleapis.com/css?family=Merriweather|Pattaya|Playfair+Display+SC" rel="stylesheet">
        <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
        <script  src="<c:url value="/resources/js/snow.js" />" type="text/javascript"></script>
<header>
    <nav>

        <div class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Джентельмен.in.ua</span>
                    </button>
                    <a class="navbar-brand" href="<c:url value="/${lang}/index"/>">Джентльмен.in.ua</a>
                </div>
                <div class="navbar-collapse collapse navbar-right">
                    <ul class="nav navbar-nav">
                        <li><div id="msg"></div></li>
                        <li class="active"><a href="<c:url value="/${lang}/index"/>"><spring:message code="page.title"/></a></li>
                        <li class="active"><a href="<c:url value="/${lang}/каталог"/>"><spring:message code="page.catalogue"/></a></li>


                        <c:if test="${pageContext.request.userPrincipal.name != null}">
                            <li class="active"><a href="<c:url value="/${lang}/admin/goodform"/>"><spring:message code="page.good"/></a></li>
                            <li class="active"><a href="<c:url value="/${lang}/admin/colors"/>"><spring:message code="text.color"/></a></li>
                            <li class="active"><a href="<c:url value="/${lang}/admin/categories"/>"><spring:message code="text.category"/></a></li>
                            <li class="active"><a href="<c:url value="/${lang}/admin/orders"/>"><spring:message code="text.orders"/></a></li>
                        </c:if>

                        <c:if test="${pageContext.request.userPrincipal.name == null}">

                        <li class="active"><a href="<c:url value="/${lang}/about"/>"><spring:message code="page.about"/></a></li>
                        <li class="active"><a href="<c:url value="/${lang}/shopping-cart"/>"><span  id="cartDiv" class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></a></li>
                        <li class="active"><a href="<c:url value="/${lang}/myorders"/>"><spring:message code="page.myorder"/></a></li>
                        </c:if>

                        <c:if test="${pageContext.request.getParameter('id') == null}">
                        <li><a href="?lang=uk"><img class="flag" src="<c:url value="/resources/images/ukraine-flag.png"/>" ></a></li>
                            <li><a href="?lang=ru"><img class="flag" src="<c:url value="/resources/images/rus-flag.png"/>" ></a></li>

                        </c:if>
                        <c:if test="${pageContext.request.getParameter('id') != null}">
                            <li><a href="${pageContext.request.contextPath}?lang=uk&${pageContext.request.queryString}"><img class="flag" src="<c:url value="/resources/images/ukraine-flag.png"/>" ></a></li>
                            <li><a href="${pageContext.request.contextPath}?lang=ru&${pageContext.request.queryString}"><img class="flag" src="<c:url value="/resources/images/rus-flag.png"/>" ></a></li>

                        </c:if>
                         <c:if test="${pageContext.request.userPrincipal.name != null}">
                            <li class="active"><a href="<c:url value="/logout"/>"><spring:message code="logout"/>, ${pageContext.request.userPrincipal.name}</a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </nav>


</header>
<div id = "flake">&#10052;</div>
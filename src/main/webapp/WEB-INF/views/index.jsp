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
    <title>Чоловічий одяг та аксесуари купити у інтернет-магазині джентльмен.in.ua</title>
    <link href="https://fonts.googleapis.com/css?family=Merriweather|Pattaya|Playfair+Display+SC" rel="stylesheet">

    <script  src="<c:url value="/resources/js/jquery-2.1.4.min.js" />" type="text/javascript"></script>
    <%--<link href="<c:url value="/resources/css/jquery.fancybox.css" />" rel="stylesheet">--%>


    <%--<script async  src="<c:url value="/resources/js/bootstrap.min.js" />" type="text/javascript"></script>--%>
    <%--<script async  src="<c:url value="/resources/js/jquery.fancybox.pack.js" />" type="text/javascript"></script>--%>
    <script   src="<c:url value="/resources/js/app.js" />" type="text/javascript"></script>
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
<jsp:include page="header.jsp" />
<div class="container">
    <section>
        <div class="row index">
            <c:forEach var="obj" items="${allData}">
            <div class="col-xs-6 col-sm-3 back" >
                <div class="infoBox">
                <table id="table-index">
                    <tr>
                        <th>
                            <c:if test="${lang == 'ua'}">

                                <a href="${pageContext.request.contextPath}/${lang}/good/${obj.category.uaText}-${obj.firm}-${obj.color.uaText}/${obj.id}"><img  src="<c:url value="/images/${obj.id}_0.jpg"/>" alt="${obj.firm} ${obj.category.uaText} ${obj.color.uaText}"/></a>
                            </c:if>
                            <c:if test="${lang == 'ru'}">
                                <a href="${pageContext.request.contextPath}/${lang}/good/${obj.category.ruText}-${obj.firm}-${obj.color.ruText}/${obj.id}"><img  src="<c:url value="/images/${obj.id}_0.jpg"/>" alt="${obj.firm} ${obj.category.ruText} ${obj.color.ruText}"/></a>
                            </c:if>


                        </th>
                    </tr>
                    <tr>
                        <td>
                            <div class="priceInfo">
                                <c:if test="${lang == 'ua'}">

                                    <a href="${pageContext.request.contextPath}/${lang}/good/${obj.category.uaText}-${obj.firm}-${obj.color.uaText}/${obj.id}"><c:out value="${obj.firm}"/></a>
                                </c:if>
                                <c:if test="${lang == 'ru'}">
                                    <a href="${pageContext.request.contextPath}/${lang}/good/${obj.category.ruText}-${obj.firm}-${obj.color.ruText}/${obj.id}"><c:out value="${obj.firm}"/></a>
                                </c:if>
                                <p class="price">${obj.price}грн.</p>

                                <form:form id="baskForm${obj.id}" action="addToBasket" method="POST">
                                    <input type="hidden" name="goodId" value="${obj.id}">
                                    <input type="button" onclick="tobasket(${obj.id})" value="<spring:message code="basket.add"/>" id="btn-basket-add" class="btn btn-success">
                                </form:form>
                            </div>
                        </td>
                    </tr>

                </table>
                <%--<div class="infoBox">--%>

</div>

            </div>

            </c:forEach>

        </div>
    </section>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
<script type="text/javascript">
    $(function() {

            $('.carousel').carousel({
                interval: 5000
            })
        });
</script>

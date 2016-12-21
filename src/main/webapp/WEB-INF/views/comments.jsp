<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 19.12.2016
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
                (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-87631623-1', 'auto');
    ga('send', 'pageview');

</script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<meta name="viewport" content="width=device-width, initial-scale=1">

<html>
<head>
    <meta name="google-site-verification" content="-MD2HrSDLr7JKWM9xnqx5OiPG7Uio20xytJhg4iICqc" />

    <c:if test="${lang == 'uk'}">
        <title>Відгуки інтернет-магазині джентльмен.in.ua</title>
        <meta name="description" content="Думка покупців про інтернет-магазин одягу та аксесуарів джентльмен.in.ua">
    </c:if>
    <c:if test="${lang == 'ru'}">
        <title>Отзывы о интернет-магазине джентльмен.in.ua</title>
        <meta name="description" content="Мнение покупателей о интернет-магазине одежды и аксессуаров джентльмен.in.ua">
    </c:if>
    <link href="https://fonts.googleapis.com/css?family=Merriweather|Pattaya|Playfair+Display+SC" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <link rel="alternate" hreflang="${alternativeLang}" href="${alternativeHref}"/>
</head>
<body>
<jsp:include page="header.jsp" />
<div class="container">
    <div class="topBox">
        <div id="hypercomments_widget"></div>
        <script type="text/javascript">
            _hcwp = window._hcwp || [];
            _hcwp.push({widget:"Stream", widget_id: 84594});
            (function() {
                if("HC_LOAD_INIT" in window)return;
                HC_LOAD_INIT = true;
                var lang = (navigator.language || navigator.systemLanguage || navigator.userLanguage || "en").substr(0, 2).toLowerCase();
                var hcc = document.createElement("script"); hcc.type = "text/javascript"; hcc.async = true;
                hcc.src = ("https:" == document.location.protocol ? "https" : "http")+"://w.hypercomments.com/widget/hc/84594/"+lang+"/widget.js";
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(hcc, s.nextSibling);
            })();
        </script>
        <a href="http://hypercomments.com" class="hc-link" title="comments widget">comments powered by HyperComments</a>
    </div>
    </div>/
<jsp:include page="footer.jsp" />
</body>
</html>

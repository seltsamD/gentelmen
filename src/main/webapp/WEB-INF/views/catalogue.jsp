<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 22.10.2016
  Time: 14:22
  To change this template use File | Settings | File Templates.
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
    <meta property="og:site_name" content="джентльмен.in.ua"/>
    <meta name="description" content="${description}"/>
    <link rel="alternate" hreflang="${alternativeLang}" href="${alternativeHref}"/>

</head>
<body>

<div class="container">
    <jsp:include page="header.jsp"/>
    <div class="mainContent">
        <div class="row">
            <div class="menu col-lg-2 col-sm-4 col-xs-12">
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                    <div class='panel panel-default'>
                        <div class='panel-heading'>
                            <h3 class='panel-title' onclick="getAll()">
                                <a> <spring:message code="catalogue.getAll"/>
                                </a></h3>
                        </div>
                    </div>

                </div>
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

                </div>
            </div>
            <div class="menu col-lg-10 col-sm-8 col-xs-12">
                <section>
                    <div class="goodCatalogue">
                        <h1><spring:message code="page.catalogue"/></h1>
                    </div>
                    <div class="row centerDisplay" id="goods">
                    </div>
                </section>
            </div>
        </div>

    </div>
</div>
<jsp:include page="footer.jsp"/>
<script src="<c:url value="/resources/js/catalogue.js" />" type="text/javascript"></script>
</body>
</html>
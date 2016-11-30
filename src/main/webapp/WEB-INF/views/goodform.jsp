<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 30.09.2016
  Time: 22:20
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Чоловічий одяг та аксесуари купити у інтернет-магазині джентльмен.in.ua</title>
    <script  src="<c:url value="${pageContext.request.contextPath}/resources/js/jquery-2.1.4.min.js" />" type="text/javascript"></script>
    <script  src="<c:url value="${pageContext.request.contextPath}/resources/js/app.js" />" type="text/javascript"></script>

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
    <div class="main">

        <div class="topBox">
            <div class="whiteBack">
                <h1><spring:message code="catalog"/></h1>

                <c:if test="${good.id==0}">
                <h3><spring:message code="page.add"/></h3>
            </c:if>
                <c:if test="${good.id!=0}">
                    <h3><spring:message code="page.update"/> <c:out value="${good.id}"/></h3>
                </c:if>
            </div>

            <form:form cssClass="form-horizontal"  id="myform" action="/admin/addGood**?${_csrf.parameterName}=${_csrf.token}**" method="POST" commandName="good" enctype="multipart/form-data">
                <form:hidden path="id"/>
                <div class="form-group">
                    <label for="firm" class="col-sm-2 control-label"><spring:message code="good.firm"/></label>
                    <div class="col-sm-10">
                        <form:input path="firm" cssClass="form-control"/><form:errors path="firm" cssClass="error-msg"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="color.id"  class="col-sm-2 control-label"><spring:message code="good.color"/></label>
                    <div class="col-sm-10">
                        <form:select path="color.id"  cssClass="form-control">
                            <form:options items="${colors}"  itemValue="id" itemLabel="${lang_code}"/>
                        </form:select>
                        <form:errors path="color.id" cssClass="error-msg"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="category.id"  class="col-sm-2 control-label"><spring:message code="good.type"/></label>
                    <div class="col-sm-10">
                        <form:select path="category.id"  cssClass="form-control">
                            <form:options items="${categories}"  itemValue="id" itemLabel="${lang_code}"/>
                        </form:select>
                        <form:errors path="category.id" cssClass="error-msg"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="price" class="col-sm-2 control-label"><spring:message code="good.price"/></label>
                    <div class="col-sm-10">
                        <form:input path="price" cssClass="form-control"/>
                        <form:errors path="price" cssClass="error-msg"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="size" class="col-sm-2 control-label"><spring:message code="good.size"/></label>
                    <div class="col-sm-10">
                        <form:input path="size" cssClass="form-control"/>
                        <form:errors path="size" cssClass="error-msg"/></div>
                </div>
                <div class="form-group">
                    <%--<label for="countImg" class="col-sm-2 control-label"><spring:message code="countImg"/></label>--%>
                    <div class="col-sm-10">
                        <form:hidden path="countImg" id="img" cssClass="form-control"/>
                        <form:errors path="countImg" cssClass="error-msg"/> </div>
                </div>
                <div class="form-group">
                    <label for="fileId" class="col-sm-2 control-label"><spring:message code="good.img"/></label>
                    <div class="col-sm-10">
                        <input type="file" name="file" multiple="true" id="fileId" onblur="count()" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label for="uaText" class="col-sm-2 control-label"><spring:message code="good.about"/> українською</label>
                    <div class="col-sm-10">
                        <form:textarea path="uaText"  cssClass="form-control"/>
                        <form:errors path="uaText" cssClass="error-msg"/>
                    </div>
                    <label for="ruText" class="col-sm-2 control-label"><spring:message code="good.about"/> на русском</label>
                    <div class="col-sm-10">
                        <form:textarea path="ruText"  cssClass="form-control"/>
                        <form:errors path="ruText" cssClass="error-msg"/></div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2">
                        <input type="hidden" id="flagImg" name="flagImg" value="0"/>
                    </div>
                    <div class="col-sm-10">

                        <c:if test="${good.id==0}">
                            <input type="button" value="<spring:message code="form.add"/>" id="btn-add"  class="btn btn-success">
                        </c:if>
                        <c:if test="${good.id!=0}">
                            <input type="button" value="<spring:message code="form.edit"/>" id="btn-update"  class="btn btn-warning">
                        </c:if>
                     </div>
                </div>
                <div class="form-group col-sm-12">
                    <c:out value="${msg}"/>
                </div>

                </form:form>
            </div>



            <table id="box-table-b" class="good-info">
                <thead>
                <tr> <th>  </th>
                    <th> <spring:message code="good.id"/> </th>
                    <th> <spring:message code="good.firm"/> </th>
                    <th> <spring:message code="good.color"/> </th>
                    <th> <spring:message code="good.type"/> </th>
                    <th> <spring:message code="good.price"/> </th>
                    <th> <spring:message code="good.size"/> </th>
                    <th> <spring:message code="good.about"/> </th>
                    <th></th>
                </tr></thead>
                <%--<c:set var="uk_lang" scope="session" value="uk"/>--%>
                <%--<c:set var="ru_lang" scope="session" value="ru"/>--%>
            <tbody>
                <c:forEach var="obj" items="${allData}">
                    <tr>
                        <td> <a class="single_image" href="<c:url value="/images/${obj.id}_0.jpg"/>"><img class="miniImg" src="<c:url value="/images/${obj.id}_0.jpg"/>" alt="${obj.firm} ${obj.category.uaText} "${obj.color.uaText}"/></a>
                        </td>
                        <td> <c:out value="${obj.id}"/> </td>
                        <td> <c:out value="${obj.firm}"/> </td>

                        <c:if test="${lang_code == 'uaText'}">
                            <td> <c:out value="${obj.color.uaText}"/> </td>
                            <td> <c:out value="${obj.category.uaText}"/> </td>
                        </c:if>
                        <c:if test="${lang_code == 'ruText'}">
                            <td> <c:out value="${obj.color.ruText}"/> </td>
                            <td> <c:out value="${obj.category.ruText}"/> </td>
                        </c:if>


                        <td> <c:out value="${obj.price}"/> </td>
                        <td> <c:out value="${obj.size}"/> </td>

                        <c:if test="${lang_code == 'uaText'}">
                            <td><c:out value="${obj.uaText}"/></td>
                        </c:if>
                        <c:if test="${lang_code == 'ruText'}">
                            <td> <c:out value="${obj.ruText}"/> </td>
                        </c:if>


                        <td class="right_table"> <a href="${pageContext.request.contextPath}/${lang}//admin/deleteGood?id=${obj.id}"><spring:message code="form.delete"/> </a> |
                            <a href="${pageContext.request.contextPath}/${lang}//admin/goodById?id=${obj.id}"><spring:message code="form.edit"/></a> |
                            <a href="${pageContext.request.contextPath}/${lang}//admin/goodInfo?id=${obj.id}"><spring:message code="form.info"/></a>
                        </td>
                    </tr>
                    <tr><td colspan="9"><hr></td> </tr>
                </c:forEach>
            </tbody>
            </table>

    </div>
    </div>
<jsp:include page="footer.jsp" />
</body>
</html>
<script type="text/javascript">
    function count() {
        var numFiles = $("input:file")[0].files.length;
        if(numFiles > 0)
                flagImg.setAttribute("value", "1");
        img.setAttribute("value", numFiles);


    }


</script>
<style>
    @media
    only screen and (max-width: 760px),
    (min-device-width: 768px) and (max-device-width: 1024px) {
        .good-info td:nth-of-type(1):before {
            content: "";
        }

        .good-info td:nth-of-type(2):before {
            content: "<spring:message code="good.id"/>";
        }

        .good-info td:nth-of-type(3):before {
            content: "<spring:message code="good.firm"/>";
        }

        .good-info td:nth-of-type(4):before {
            content: "<spring:message code="good.color"/>";
        }

        .good-info td:nth-of-type(5):before {
            content: "<spring:message code="good.type"/>";
        }

        .good-info td:nth-of-type(6):before {
            content: "<spring:message code="good.price"/>";
        }

        .good-info td:nth-of-type(7):before {
            content: "<spring:message code="good.size"/>";
        }

        .good-info td:nth-of-type(8):before {
            content: "<spring:message code="good.about"/>";
        }
    }
</style>
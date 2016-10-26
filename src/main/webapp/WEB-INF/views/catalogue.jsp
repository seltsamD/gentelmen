<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 22.10.2016
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<jsp:include page="footer.jsp" />

<div class="container">
    <div class="main">
    <div class="topBox">
        <div class="row">
            <div class="col-sm-3">
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingNull">
                            <h4 class="panel-title">
                                <a href="${pageContext.request.contextPath}/catalogue">   <spring:message code="good.all"/>
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
                                        <c:forEach var="cat" items="${categories}">
                                            <li><a href="${pageContext.request.contextPath}/category?id=${cat.id}">
                                            <c:if test="${lang_code == 'uaText'}">
                                                <c:out value="${cat.uaText}"/>
                                            </c:if>
                                            <c:if test="${lang_code == 'ruText'}">
                                                <c:out value="${cat.ruText}"/>
                                            </c:if>

                                            </a></li>
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
                                        <li><a href="${pageContext.request.contextPath}/color?id=${color.id}">
                                            <c:if test="${lang_code == 'uaText'}">
                                                <c:out value="${color.uaText}"/>
                                            </c:if>
                                            <c:if test="${lang_code == 'ruText'}">
                                                <c:out value="${color.ruText}"/>
                                            </c:if>
                                       </a> </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>

                </div>
                <%--<div class="sidebar-nav">--%>
                    <%--<div class="navbar navbar-default" role="navigation">--%>
                        <%--<div class="navbar-header">--%>
                            <%--<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-navbar-collapse">--%>
                                <%--<span class="sr-only">Toggle navigation</span>--%>
                                <%--<span class="icon-bar"></span>--%>
                                <%--<span class="icon-bar"></span>--%>
                                <%--<span class="icon-bar"></span>--%>
                            <%--</button>--%>
                            <%--<span class="visible-xs navbar-brand">Sidebar menu</span>--%>
                        <%--</div>--%>
                        <%--<div class="navbar-collapse collapse sidebar-navbar-collapse">--%>
                            <%--<ul class="nav navbar-nav">--%>
                                <%--<li class="active"><a href="#">Menu Item 1</a></li>--%>
                                <%--<li><a href="#">Menu Item 2</a></li>--%>
                                <%--<li class="dropdown">--%>
                                    <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>--%>
                                    <%--<ul class="dropdown-menu">--%>
                                        <%--<li><a href="#">Action</a></li>--%>
                                        <%--<li><a href="#">Another action</a></li>--%>
                                        <%--<li><a href="#">Something else here</a></li>--%>
                                        <%--<li class="divider"></li>--%>
                                        <%--<li class="dropdown-header">Nav header</li>--%>
                                        <%--<li><a href="#">Separated link</a></li>--%>
                                        <%--<li><a href="#">One more separated link</a></li>--%>
                                    <%--</ul>--%>
                                <%--</li>--%>
                                <%--<li><a href="#">Menu Item 4</a></li>--%>
                                <%--<li><a href="#">Reviews <span class="badge">1,118</span></a></li>--%>
                            <%--</ul>--%>
                        <%--</div><!--/.nav-collapse -->--%>
                    <%--</div>--%>
                <%--</div>--%>
            </div>
            <div class="col-sm-9">

                    <div class="whiteBack">
                        <h1><spring:message code="catalog"/></h1>

                    </div>
            <c:if test="${count <= 0}">
                <h3><spring:message code="good.notFound"/></h3>
            </c:if>
            <c:if test="${count > 0}">
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
                            <td> <a class="single_image" href="<c:url value="/images/${obj.id}_0.jpg"/>"><img  class="miniImg"  src="<c:url value="/images/${obj.id}_0.jpg"/>" alt="${obj.firm} ${obj.category.uaText} "${obj.color.uaText}"/></a>

                            <%--<td class="right_table">--%>
                                <form:form id="baskForm${obj.id}" action="addToBasket" method="POST">
                                    <input type="hidden" name="goodId" value="${obj.id}">
                                    <input type="button" onclick="tobasket(${obj.id})" value="<spring:message code="basket.add"/>" id="btn-basket-add" class="btn btn-success">
                                </form:form>
                            <%--</td>--%>
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
                                <td> <c:out value="${obj.description.uaText}"/> </td>
                            </c:if>
                            <c:if test="${lang_code == 'ruText'}">
                                <td> <c:out value="${obj.description.ruText}"/> </td>
                            </c:if>



                        </tr>
                        <tr><td colspan="9"><hr></td> </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </c:if>
            </div>
        </div>

    </div>
</div>
</div>
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
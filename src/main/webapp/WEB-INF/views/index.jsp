<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 30.09.2016
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp" />
<jsp:include page="footer.jsp" />


    <%--<div id = "myCarousel" class = "carousel slide">--%>

        <%--<!-- Carousel indicators -->--%>
        <%--<ol class = "carousel-indicators">--%>
            <%--<li data-target = "#myCarousel" data-slide-to = "0" class = "active"></li>--%>
            <%--<li data-target = "#myCarousel" data-slide-to = "1"></li>--%>
            <%--<li data-target = "#myCarousel" data-slide-to = "2"></li>--%>
        <%--</ol>--%>

        <%--<!-- Carousel items -->--%>
        <%--<div class = "carousel-inner">--%>
            <%--<div class = "item active">--%>
                <%--<img src = "<c:url value="resources/images/pic1.jpg"/>" alt = "First slide">--%>
            <%--</div>--%>

            <%--<div class = "item">--%>
                <%--<img src = "<c:url value="resources/images/pic2.jpg"/>" alt = "Second slide">--%>
            <%--</div>--%>

            <%--<div class = "item">--%>
                <%--<img src = "<c:url value="resources/images/pic3.jpg"/>" alt = "Third slide">--%>
            <%--</div>--%>
        <%--</div>--%>

        <%--<!-- Carousel nav -->--%>
        <%--<a class = "carousel-control left" href = "#myCarousel" data-slide = "prev">&lsaquo;</a>--%>
        <%--<a class = "carousel-control right" href = "#myCarousel" data-slide = "next">&rsaquo;</a>--%>

    <%--</div>--%>
<div class="container">
    <section>
        <div class="row catalogue">
            <c:forEach var="obj" items="${allData}">
            <div class="col-xs-6 col-sm-3 back" >
                <div class="infoBox">
                 <a class="single_image" href="<c:url value="images/${obj.id}_0.jpg"/>"><img  src="<c:url value="images/${obj.id}_0.jpg"/>" alt="${obj.firm} ${obj.category.uaText} "${obj.color.uaText}"/></a>
                <c:if test="${lang_code == 'uaText'}">
                    <a href="${pageContext.request.contextPath}/goodInfo?id=${obj.id}"><c:out value="${obj.firm}"/></a>

                </c:if>
                <c:if test="${lang_code == 'ruText'}">
                    <a href="${pageContext.request.contextPath}/goodInfo?id=${obj.id}"><c:out value="${obj.firm}"/></a>
                </c:if>
                <div class="priceInfo">
                    <p class="price">${obj.price}грн.</p>

                    <form:form id="baskForm${obj.id}" action="addToBasket" method="POST">
                        <input type="hidden" name="goodId" value="${obj.id}">
                        <input type="button" onclick="tobasket(${obj.id})" value="<spring:message code="basket.add"/>" id="btn-basket-add" class="btn btn-success">
                    </form:form>
                </div>

            </div>
                 </div>
            </c:forEach>
        </div>
    </section>
</div>

<script type="text/javascript">
    $(function() {

            $('.carousel').carousel({
                interval: 5000
            })
        });
</script>

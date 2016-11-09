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
<jsp:include page="header.jsp" />
<jsp:include page="footer.jsp" />


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

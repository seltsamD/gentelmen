<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 03.10.2016
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<jsp:include page="footer.jsp" />

<div class="container">
    <div class="main">
    <spring:message code="page.add"/>
    <form:form  id="myform" action="addGood" method="POST" enctype="multipart/form-data" commandName="good">

        <form:input path="firm"/><form:errors path="firm" cssClass="error-msg"/>
        <form:input path="color"/><form:errors path="color" cssClass="error-msg"/>
        <form:input path="type"/><form:errors path="type" cssClass="error-msg"/>
        <form:input path="price"/><form:errors path="price" cssClass="error-msg"/>
        <form:input path="size"/><form:errors path="size" cssClass="error-msg"/>
        <form:input path="countImg"/><form:errors path="countImg" cssClass="error-msg"/>
        <input type="file" name="file" multiple="true">
        <input type="submit" value="<spring:message code="form.add"/>" id="btn-add">
    </form:form>
</div>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        var inp = null;
        inp = document.getElementById('file');
        var count = 0;
        for (var i = 0; i < inp.files.length; ++i) {
            var name = inp.files.item(i).name;
            count++;
        }
        alert("here is count: " + count);
    });
</script>
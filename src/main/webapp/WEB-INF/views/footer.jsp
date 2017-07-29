<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 03.10.2016
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="snackbar"><spring:message code="basket.IsAdded"/></div>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/new.css" />" rel="stylesheet">

<script src="<c:url value="/resources/js/jquery-2.1.4.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />" type="text/javascript"></script>

<script src="<c:url value="/resources/js/jquery-ui.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/app.js" />" type="text/javascript"></script>
<%--<footer class="footer">--%>
        <%--<p class="text-muted">Джентльмен.in.ua 2016-2017р.<br> gentlmen.in.ua@gmail.com </p>--%>
    <%--</footer>--%>

<script>
    (function (i, s, o, g, r, a, m) {
        i['GoogleAnalyticsObject'] = r;
        i[r] = i[r] || function () {
                    (i[r].q = i[r].q || []).push(arguments)
                }, i[r].l = 1 * new Date();
        a = s.createElement(o),
                m = s.getElementsByTagName(o)[0];
        a.async = 1;
        a.src = g;
        m.parentNode.insertBefore(a, m)
    })(window, document, 'script', 'https://www.google-analytics.com/analytics.js', 'ga');

    ga('create', 'UA-87631623-1', 'auto');
    ga('send', 'pageview');

</script>
    <!-- Top100 (Kraken) Counter -->
    <script>
        (function (w, d, c) {
            (w[c] = w[c] || []).push(function() {
                var options = {
                    project: 4458667
                };
                try {
                    w.top100Counter = new top100(options);
                } catch(e) { }
            });
            var n = d.getElementsByTagName("script")[0],
                    s = d.createElement("script"),
                    f = function () { n.parentNode.insertBefore(s, n); };
            s.type = "text/javascript";
            s.async = true;
            s.src =
                    (d.location.protocol == "https:" ? "https:" : "http:") +
                    "//st.top100.ru/top100/top100.js";

            if (w.opera == "[object Opera]") {
                d.addEventListener("DOMContentLoaded", f, false);
            } else { f(); }
        })(window, document, "_top100q");
    </script>
    <noscript><img src="//counter.rambler.ru/top100.cnt?pid=4458667"></noscript>
    <!-- END Top100 (Kraken) Counter -->
</footer>


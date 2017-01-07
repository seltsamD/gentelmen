<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 03.10.2016
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="snackbar"><spring:message code="basket.IsAdded"/></div>
<%--<footer class="footer">--%>
        <%--<p class="text-muted">Джентльмен.in.ua 2016-2017р.<br> gentlmen.in.ua@gmail.com </p>--%>
    <%--</footer>--%>
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


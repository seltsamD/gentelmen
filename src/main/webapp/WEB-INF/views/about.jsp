<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 09.11.2016
  Time: 18:32
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
        <title>Про нас інтернет-магазині джентльмен.in.ua</title>
    </c:if>
    <c:if test="${lang == 'ru'}">
        <title>О нас интернет-магазине джентльмен.in.ua</title>
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
    <c:if test="${lang == 'uk'}">
        <h1>Контакти</h1>
        <p>Ми з радістю відповімо на будь-які Ваші запитання за телефоном +380957763287 Дар'я<br>
        email: gentlmen.in.ua@gmail.com </p>
        <h1>Про нас</h1>
        <p>Інтернет магазин <a rel="up" href="http://джентльмен.in.ua">джентльмен.in.ua</a> пропонує широкий вибір чоловічого одягу та
            аксесуарів для справжніх цінителів.</p><p>Висока якість товару, швидке оброблення замовлення та ввічливі менеджери -
        це, на нашу думку, головні елементи успішного бізнесу. Сподіваємося, що Ви знайдене все необхідне в нашому
        магазині та отримаєте задоволення від швидкої та зручної покупки.</p>
        <p>Товари на нашому сайті це продукція зs стоків, шоурумів і аутлетов світових брендів.
            У нас Ви знайдете продукцію таких компаній як Marks and Spencer, Daniel Hechter, Primark, Cedar Wood State, Next, Tomas Nash і Burton.
            Ми із задоволення підберемо для Вас необхідний товар для створення неповторного образу.
        </p>
        <h2>Доставка</h2>
        <p>Ми відправимо Ваше замовлення у будь-якій зручний для вас спосіб (Укрпошта, Нова Пошта та Інтайм). У м.Львові можлива особиста зустріч.
        </p>
    </c:if>
    <c:if test="${lang == 'ru'}">
        <h1>Контакты</h1>
        <p>Мы с удовольствием ответим на любой Ваш вопрос по телефону +380957763287 Дарья<br>
            email: gentlmen.in.ua@gmail.com </p>
        <h2> О нас </h2>
                <p> Интернет магазин <a href="джентльмен.in.ua">джентльмен.in.ua</a> предлагает широкий выбор мужской одежды и
        аксессуаров для истиных ценителей. </p> <p> Высокое качество товара, быстрая обработка заказа и вежливые менеджеры -
        это, по нашему мнению, главные элементы успешного бизнеса. Надеемся, что Вы найдено все необходимое в нашем
        магазине и получите удовольствие от быстрой и удобной покупки.</p>
        <p>Товары на нашем сайте — это продукция со стоков, шоурумов и аутлетов мировых брендов.
            У нас Вы найдете продукцию таких компаний как Marks and Spencer, Daniel Hechter, Primark, Cedar Wood State, Next, Tomas Nash и Burton.
            Мы с удовольствие подберем для Вас необходимый товар для создания уникального образа.
        </p>
                <h2> Доставка </h2>
                <p> Мы отправим Ваш заказ в любой удобный для Вас способ (Укрпочта, Новая почта, Интайм). В г. Львов возможна личная встреча. </p>
    </c:if>
        </div>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>

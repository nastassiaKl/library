<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale/locale" var="local"/>

<html>
<head>
    <title><fmt:message key="label.library" bundle="${local}"/></title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/resource/js/jquery-3.2.1.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>

</head>
<body class="body">
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"/>

<div class="container">
    <h1 class="main-welcome"><fmt:message key="label.mainCaption" bundle="${local}"/></h1>
</div>

<div class="my-carousel">
    <div id="carousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carousel" data-slide-to="0" class="active"></li>
            <li data-target="#carousel" data-slide-to="1"></li>
            <li data-target="#carousel" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="item active">
                <img src="${pageContext.request.contextPath}/resource/images/carousel/1.jpg" class="image-carousel">
                <div class="carousel-caption my-caption">
                    <p class="p-carousel"><fmt:message key="label.caption1" bundle="${local}"/></p>
                </div>
            </div>
            <div class="item">
                <img src="${pageContext.request.contextPath}/resource/images/carousel/2.jpg" class="image-carousel">
                <div class="carousel-caption my-caption">
                    <p class="p-carousel"><fmt:message key="label.caption2" bundle="${local}"/></p>
                </div>
            </div>
            <div class="item">
                <img src="${pageContext.request.contextPath}/resource/images/carousel/3.jpg" class="image-carousel">
                <div class="carousel-caption my-caption">
                    <p class="p-carousel"><fmt:message key="label.caption3" bundle="${local}"/></p>
                </div>
            </div>
        </div>

        <a class="left carousel-control" href="#carousel" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
        </a>
        <a class="right carousel-control" href="#carousel" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
        </a>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"/>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale/locale" var="local"/>

<fmt:message key="label.numberTicket" bundle="${local}" var="numberTicket"/>
<fmt:message key="label.surname" bundle="${local}" var="surname"/>
<fmt:message key="label.name" bundle="${local}" var="name"/>
<fmt:message key="label.middleName" bundle="${local}" var="middleName"/>
<fmt:message key="label.age" bundle="${local}" var="age"/>
<fmt:message key="label.phone" bundle="${local}" var="phone"/>
<fmt:message key="label.mail" bundle="${local}" var="mail"/>
<fmt:message key="label.loginValue" bundle="${local}" var="login"/>
<fmt:message key="label.passwordValue" bundle="${local}" var="password"/>

<html>
<head>
    <title><fmt:message key="label.account" bundle="${local}"/></title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery-3.2.1.js"></script>

</head>
<body class="body">
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"/>

<c:set var="reader" scope="session" value="${sessionScope.userData}"/>

<div class="container">

    <a href="${pageContext.request.contextPath}/Controller?command=load_page&page=/jsp/user/account/changePassword.jsp" class="a-function"><fmt:message key="label.changePassword" bundle="${local}"/>
        <span class="glyphicon glyphicon-pencil"></span></a>
    <a href="${pageContext.request.contextPath}/Controller?number_ticket=${reader.numberTicket}&command=edit_account" class="a-edit-user"><fmt:message key="label.editProfile" bundle="${local}"/>
        <span class="glyphicon glyphicon-wrench"></span></a>

    <form action="${pageContext.request.contextPath}/Controller" method="post">

        <c:if test="${reader != null}">

            <div class="image">
                <img src="/resource/images/reader/${reader.profilePhoto}" alt="${reader.login}" class="profile-photo">
            </div>

            <p class="data-reader">${reader.surname} ${reader.name} ${reader.middleName}</p>

            <div class="col-md-4">
                <p class="parameter">${numberTicket}: </p><h2 class="h-parameter">${reader.numberTicket}</h2>
                <p class="parameter">${login}: </p><h2 class="h-parameter">${reader.login}</h2>
                <p class="parameter">${mail}: </p><h2 class="h-parameter">${reader.mail}</h2>
            </div>
            <div class="col-md-4 col-md-offset-4">
                <p class="parameter">${phone}: </p><h2 class="h-parameter">${reader.phoneNumber}</h2>
                <p class="parameter">${age}: </p><h2 class="h-parameter">${reader.age}</h2>
            </div>
        </c:if>
    </form>

</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"/>
</body>
</html>

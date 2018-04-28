<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale/locale" var="local"/>

<html>
<head>
    <title><fmt:message key="label.editing" bundle="${local}"/></title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/resource/js/author/author.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>

</head>
<body class="body">
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"/>
<c:set var="author" scope="request" value="${requestScope.author}"/>

<div class="container">

    <form role="form" action="${pageContext.request.contextPath}/Controller" method="post" name="form"
          onsubmit="return checkAuthor();">
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterSurnameAuthor" bundle="${local}"/></label>
            <input type="text" name="surname" class="form-control" value="${author.surname}">
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterNameAuthor" bundle="${local}"/></label>
            <input type="text" name="name" class="form-control" value="${author.name}">
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterMiddleNameAuthor" bundle="${local}"/></label>
            <input type="text" name="middle_name" class="form-control" value="${author.middleName}">
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterCountryAuthor" bundle="${local}"/></label>
            <input type="text" name="country" class="form-control" value="${author.countryBirth}">
        </div>
        <input type="hidden" name="command" value="update_author"/>
        <input type="hidden" name="id_author" value="${author.id}"/>
        <input type="submit" class="button" value="<fmt:message key="label.edit" bundle="${local}"/>"/>
    </form>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"/>
</body>
</html>

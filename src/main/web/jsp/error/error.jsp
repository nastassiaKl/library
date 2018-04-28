<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale/locale" var="local"/>

<html>
<head>
    <title><fmt:message key="label.errorPage" bundle="${local}"/></title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">

</head>
<body class="body-error">
<div class="container">
    <div class="error">
        <h2>Request from ${pageContext.errorData.requestURI} is failed <br/></h2>
        <h2>Servlet name: ${pageContext.errorData.servletName} <br/></h2>
        <h2>Status code: ${pageContext.errorData.statusCode} <br/></h2>
        <h2>Exception: ${pageContext.exception.message}</h2>
    </div>
</div>
</body>
</html>

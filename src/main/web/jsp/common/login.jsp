<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="err" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale/locale" var="local"/>
<fmt:message key="label.logIn" bundle="${local}" var="logIn"/>
<fmt:message key="label.login" bundle="${local}" var="login"/>
<fmt:message key="label.password" bundle="${local}" var="password"/>

<html>
<head>

    <title><fmt:message key="label.library" bundle="${local}"/></title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery-3.2.1.js"></script>

</head>
<body class="body">
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"/>

<div class="container">

    <err:error errorMessage="${messageLogin}"/>

    <form role="form" action="/Controller" method="post" class="form-login">
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.login" bundle="${local}"/></label>
            <input type="text" name="login" class="form-control" placeholder="${login}"/><br/>
        </div>

        <div class="form-group">
            <label class="my-label"><fmt:message key="label.password" bundle="${local}"/></label>
            <input type="password" name="password" class="form-control" placeholder="${password}"/><br/>
        </div>

        <input type="hidden" name="command" value="login"/>
        <input type="submit" value="${logIn}" class="button"/>
    </form>

    <a href="${pageContext.request.contextPath}/Controller?command=load_page&page=/jsp/forgetPassword.jsp" class="a-forgot-password"><fmt:message key="label.questionForgotPassword" bundle="${local}"/></a>

</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"/>
</body>
</html>

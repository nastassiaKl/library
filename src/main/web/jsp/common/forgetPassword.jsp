<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="err"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale/locale" var="local"/>

<fmt:message key="label.sendNewPassword" bundle="${local}" var="send"/>

<html>
<head>
    <title><fmt:message key="label.forgotPassword" bundle="${local}"/></title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/password/forgetPassword.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery-3.2.1.js"></script>

</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"/>

<err:error errorMessage="${messageForgotPassword}"/>

<div class="container">
    <form role="form" action="${pageContext.request.contextPath}/Controller" method="post" class="form-param" name="form" onsubmit="return checkForgetPassword();">

        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterNumberTicket" bundle="${local}"/> </label>
            <input type="text" name="number_ticket" class="form-control"/><br/>
        </div>

        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterMail" bundle="${local}"/> </label>
            <input type="text" name="mail" class="form-control"/><br/>
        </div>

        <input type="hidden" name="command" value="forget_password"/>
        <input type="submit" value="${send}" class="button"/>
    </form>

</div>

<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"></jsp:include>
</body>
</html>

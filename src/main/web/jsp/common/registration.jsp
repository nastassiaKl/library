<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="err"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale/locale" var="local"/>

<fmt:message key="label.registration" bundle="${local}" var="registration"/>
<fmt:message key="label.surname" bundle="${local}" var="surname"/>
<fmt:message key="label.name" bundle="${local}" var="name"/>
<fmt:message key="label.middleName" bundle="${local}" var="middleName"/>
<fmt:message key="label.age" bundle="${local}" var="age"/>
<fmt:message key="label.phone" bundle="${local}" var="phone"/>
<fmt:message key="label.mail" bundle="${local}" var="mail"/>
<fmt:message key="label.login" bundle="${local}" var="login"/>
<fmt:message key="label.password" bundle="${local}" var="password"/>
<fmt:message key="label.repeatPassword" bundle="${local}" var="repeatPassword"/>
<fmt:message key="label.toRegister" bundle="${local}" var="toRegistr"/>
<fmt:message key="label.clear" bundle="${local}" var="clear"/>

<html>
<head>
    <title>${registration}</title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/resource/js/reader/registration.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/jquery-3.2.1.js"></script>

</head>
<body class="body">
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"/>
<div class="container">

    <err:error errorMessage="${messageSameLogin}"/>

    <form role="form" action="${pageContext.request.contextPath}/Controller" name="form" onsubmit="return checkRegistration();" method="post"
          enctype="multipart/form-data" class="form-param">
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.makeChoice" bundle="${local}"/> </label>
            <label class="radio-inline">
                <input type="radio" name="role" value="Библиотекарь"><fmt:message key="label.librarian" bundle="${local}"/>
            </label>
            <label class="radio-inline">
                <input type="radio" name="role" value="Читатель"><fmt:message key="label.reader" bundle="${local}"/>
            </label>
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterSurname" bundle="${local}"/></label>
            <input type="text" name="surname" placeholder="${surname}" class="form-control field"><br/>
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterName" bundle="${local}"/></label>
            <input type="text" name="name" placeholder="${name}" class="form-control field"><br/>
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterMiddleName" bundle="${local}"/></label>
            <input type="text" name="middle_name" placeholder="${middleName}" class="form-control field"><br/>
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterAge" bundle="${local}"/></label>
            <input type="text" name="age" placeholder="${age}" class="form-control field"><br/>
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterPhone" bundle="${local}"/></label>
            <input type="text" name="phone" placeholder="${phone}" class="form-control field"><br/>
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterMail" bundle="${local}"/></label>
            <input type="text" name="mail" placeholder="${mail}" class="form-control field"><br/>
        </div>
        <div class="form-group">
            <span class="btn btn-default btn-file">
                <input type="file" name="profile_photo"><fmt:message key="label.addProfilePhoto" bundle="${local}"/>
            </span>
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterLogin" bundle="${local}"/></label>
            <input type="text" name="login" placeholder="${login}" class="form-control field"><br/>
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterPassword" bundle="${local}"/></label>
            <input type="password" name="password" placeholder="${password}" class="form-control field"><br/>
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.repeatPassword" bundle="${local}"/></label>
            <input type="password" name="password2" placeholder="${repeatPassword}" class="form-control field"><br/>
        </div>
        <div class="form-group">
            <input type="hidden" name="command" value="registration"/>
            <input type="submit" name="registration" value="${toRegistr}" class="button">
            <button type="reset" value="clear" onclick="clearForm()" class="button">${clear}</button>
        </div>
    </form>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"/>
</body>
</html>

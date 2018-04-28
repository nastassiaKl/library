<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale/locale" var="local"/>

<fmt:message key="label.edit" bundle="${local}" var="edit"/>

<html>
<head>
    <title><fmt:message key="label.editing" bundle="${local}"/></title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css.css">
    <script src="${pageContext.request.contextPath}/resource/js/librarian/librarian.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>

</head>
<body class="body">
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"/>
<c:set var="librarian" scope="request" value="${requestScope.librarian}"/>

<div class="container">

    <form role="form" action="${pageContext.request.contextPath}/Controller" method="post" name="form" onsubmit="return checkLibrarians();">
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterSurname" bundle="${local}"/> </label>
            <input type="text" name="surname" class="form-control" value="${librarian.surname}">
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterName" bundle="${local}"/> </label>
            <input type="text" name="name" class="form-control" value="${librarian.name}">
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterMiddleName" bundle="${local}"/> </label>
            <input type="text" name="middle_name" class="form-control" value="${librarian.middleName}">
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterLogin" bundle="${local}"/> </label>
            <input type="text" name="login" class="form-control" value="${librarian.login}">
        </div>
        <div class="form-group">
            <input type="hidden" name="command" value="update_librarian"/>
            <input type="hidden" name="id_librarian" value="${librarian.id}"/>
            <input type="submit" class="button" value="${edit}"/>
        </div>
    </form>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"/>
</body>
</html>

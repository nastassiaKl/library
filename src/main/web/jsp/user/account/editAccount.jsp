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
    <link rel="stylesheet" type="text/css" href = "${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href = "${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href = "${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/resource/js/reader/editAccount.js"></script>

</head>
<body>

<c:set var="reader" scope="request" value="${requestScope.reader}"/>

<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"/>

<div class="container">
    <form role="form" action="${pageContext.request.contextPath}/Controller" name="form" onsubmit="return checkEditAccount()" method="post"
          enctype="multipart/form-data">
        <div class="form-group">
            <input type="text" name="surname" value="${reader.surname}" class="form-control field"><br/>
        </div>
        <div class="form-group">
            <input type="text" name="name" value="${reader.name}" class="form-control field"><br/>
        </div>
        <div class="form-group">
            <input type="text" name="middle_name" value="${reader.middleName}" class="form-control field"><br/>
        </div>
        <div class="form-group">
            <input type="text" name="age" value="${reader.age}" class="form-control field"><br/>
        </div>
        <div class="form-group">
            <input type="text" name="phone" value="${reader.phoneNumber}" class="form-control field"><br/>
        </div>
        <div class="form-group">
            <input type="text" name="mail" value="${reader.mail}" class="form-control field"><br/>
        </div>
        <div class="form-group">
            <span class="btn btn-default btn-file">
                <input type="file" name="profile_photo"><fmt:message key="label.addProfilePhoto" bundle="${local}"/>
            </span>
        </div>
        <div class="form-group">
            <input type="text" name="login" value="${reader.login}" class="form-control field"><br/>
        </div>
        <div class="form-group">
            <input type="hidden" name="number_ticket" value="${reader.numberTicket}">
            <input type="hidden" name="old_profile_photo" value="${reader.profilePhoto}">
            <input type="hidden" name="command" value="update_account"/>
            <input type="submit" value="${edit}" class="button">
        </div>
    </form>
</div>

<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"/>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale/locale" var="local"/>

<fmt:message key="label.isbn" bundle="${local}" var="ISBN"/>
<fmt:message key="label.tittle" bundle="${local}" var="tittle"/>
<fmt:message key="label.surname" bundle="${local}" var="surname"/>
<fmt:message key="label.name" bundle="${local}" var="name"/>
<fmt:message key="label.middleName" bundle="${local}" var="middleName"/>
<fmt:message key="label.country" bundle="${local}" var="country"/>
<fmt:message key="label.genre" bundle="${local}" var="genre"/>
<fmt:message key="label.dateEdition" bundle="${local}" var="dateEdition"/>
<fmt:message key="label.placeEdition" bundle="${local}" var="placeEdition"/>
<fmt:message key="label.publisher" bundle="${local}" var="publisher"/>
<fmt:message key="label.numberCopies" bundle="${local}" var="numberCopies"/>
<fmt:message key="label.add" bundle="${local}" var="add"/>

<c:set var="book" scope="request" value="${requestScope.book}"/>

<html>
<head>
    <title><fmt:message key="label.editing" bundle="${local}"/></title>

    <link rel="shortcut icon" href="/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/resource/js/book/editBook.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>

</head>
<body class="body">
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"/>

<div class="container">

    <form role="form" action="${pageContext.request.contextPath}/Controller" method="post" name="form"
          onsubmit="return checkEditBook();" enctype="multipart/form-data">
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterIsbn" bundle="${local}"/></label>
            <input type="text" name="isbn" class="form-control" value="${book.isbn}">
            <p class="help-block">13 знаков</p>
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterTittle" bundle="${local}"/></label>
            <input type="text" name="tittle" class="form-control" value="${book.tittle}">
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterSurnameAuthor" bundle="${local}"/></label>
            <input type="text" name="surname" class="form-control" value="${book.author.surname}">
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterNameAuthor" bundle="${local}"/></label>
            <input type="text" name="name" class="form-control" value="${book.author.name}">
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterMiddleNameAuthor" bundle="${local}"/></label>
            <input type="text" name="middle_name" class="form-control" value="${book.author.middleName}">
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterCountryAuthor" bundle="${local}"/></label>
            <input type="text" name="country" class="form-control" value="${book.author.countryBirth}">
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterGenre" bundle="${local}"/></label>
            <c:forEach var="genre" items="${genres}">
                <br/><input type="checkbox" name="genre" value="${genre.value}"> ${genre.value}
            </c:forEach>
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterDateEdition" bundle="${local}"/></label>
            <input type="text" name="date_edition" class="form-control" value="${book.dateEdition}">
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterPlaceEdition" bundle="${local}"/></label>
            <input type="text" name="place_edition" class="form-control" value="${book.placeEdition}">
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterPublisher" bundle="${local}"/></label>
            <input type="text" name="publisher" class="form-control" value="${book.publisher}">
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.enterNumberCopies" bundle="${local}"/></label>
            <input type="text" name="number_copies" class="form-control" value="${book.numberCopies}">
        </div>
        <div class="form-group">
            <span class="btn btn-default btn-file">
                <input type="file" name="image"><fmt:message key="label.addImage" bundle="${local}"/>
            </span>
        </div>
        <input type="hidden" name="command" value="update_book"/>
        <input type="hidden" name="id_book" value="${book.id}"/>
        <input type="hidden" name="old_image" value="${book.image}"/>
        <input type="submit" class="button" value="Отредактировать"/>
    </form>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"/>
</body>
</html>

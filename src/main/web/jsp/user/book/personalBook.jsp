<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale/locale" var="local"/>

<fmt:message key="label.isbn" bundle="${local}" var="ISBN"/>
<fmt:message key="label.tittle" bundle="${local}" var="tittle"/>
<fmt:message key="label.surnameAuthor" bundle="${local}" var="surname"/>
<fmt:message key="label.nameAuthor" bundle="${local}" var="name"/>
<fmt:message key="label.middleNameAuthor" bundle="${local}" var="middleName"/>
<fmt:message key="label.genre" bundle="${local}" var="genre"/>
<fmt:message key="label.dateEdition" bundle="${local}" var="dateEdition"/>
<fmt:message key="label.placeEdition" bundle="${local}" var="placeEdition"/>
<fmt:message key="label.publisher" bundle="${local}" var="publisher"/>
<fmt:message key="label.numberCopies" bundle="${local}" var="numberCopies"/>
<fmt:message key="label.addToCart" bundle="${local}" var="addToCart"/>

<html>
<head>
    <title><fmt:message key="label.moreInfo" bundle="${local}"/></title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">

</head>
<body class="body">
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"/>

<c:set var="book" scope="request" value="${requestScope.personalBook}"/>

<div class="container">


    <c:if test="${sessionScope.role == 'reader'}">
        <div class="col-md-4 col-md-offset-5">
            <a class="a-function"
               href="${pageContext.request.contextPath}/Controller?id_reader=${user.id}&id_book=${book.id}&id_author=${book.author.id}&command=add_to_cart">${addToCart}</a>
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/Controller" method="post">
        <c:if test="${book != null}">

            <p class="tittle">${book.tittle}</p>

            <div class="image">
                <img src="/resource/images/book/${book.image}" alt="${book.tittle}" class="persImageBook">
            </div>

            <div class="author">${book.author.surname} ${book.author.name} ${book.author.middleName}</div>

            <div class="col-md-4">
                <p class="parameter">${ISBN}: </p>
                <h2 class="h-parameter">${book.isbn}</h2>
                <p class="parameter">${dateEdition}: </p>
                <h2 class="h-parameter">${book.dateEdition}</h2>
                <p class="parameter">${placeEdition}: </p>
                <h2 class="h-parameter">${book.placeEdition}</h2>
            </div>
            <div class="col-md-4 col-md-offset-4">
                <p class="parameter">${publisher}: </p>
                <h2 class="h-parameter">${book.publisher}</h2>
                <p class="parameter">${numberCopies}: </p>
                <h2 class="h-parameter">${book.numberCopies}</h2>
            </div>
        </c:if>
    </form>
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"/>
</body>
</html>

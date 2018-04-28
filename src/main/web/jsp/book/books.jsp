<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="suc" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="err" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale/locale" var="local"/>

<fmt:message key="message.emptyData" bundle="${local}" var="empyData"/>

<html>
<head>
    <title><fmt:message key="label.books" bundle="${local}"/></title>

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

<c:set var="currentPage" scope="session" value="${sessionScope.currentPage}"/>
<c:set var="numberOfPages" scope="request" value="${sessionScope.numberOfPages}"/>

<suc:success successMessage="${messageDeleteBook}"/>

<form action="${pageContext.request.contextPath}/Controller" method="post">
    <a href="Controller?command=get_genres" class="a-function"><fmt:message key="label.addNewBook" bundle="${local}"/>
        <span
                class="glyphicon glyphicon-plus"></span></a>
</form>

<c:choose>
<c:when test="${not empty sessionScope.books}">
<form action="${pageContext.request.contextPath}/Controller" method="post">
    <table class="table table-hover table-condensed">
        <thead class="table-thead">
        <tr>
            <th><fmt:message key="label.image" bundle="${local}"/></th>
            <th><fmt:message key="label.isbn" bundle="${local}"/></th>
            <th><fmt:message key="label.tittle" bundle="${local}"/></th>
            <th><fmt:message key="label.genre" bundle="${local}"/></th>
            <th><fmt:message key="label.surnameAuthor" bundle="${local}"/></th>
            <th><fmt:message key="label.nameAuthor" bundle="${local}"/></th>
            <th><fmt:message key="label.middleNameAuthor" bundle="${local}"/></th>
            <th><fmt:message key="label.country" bundle="${local}"/></th>
            <th><fmt:message key="label.dateEdition" bundle="${local}"/></th>
            <th><fmt:message key="label.placeEdition" bundle="${local}"/></th>
            <th><fmt:message key="label.publisher" bundle="${local}"/></th>
            <th><fmt:message key="label.numberCopies" bundle="${local}"/></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${books}">
            <tr>
                <td><img src="/resource/images/book/${book.image}" alt="${book.tittle}" class="imageBook"></td>
                <td>${book.isbn}</td>
                <td>${book.tittle}</td>
                <td>${book.genres}</td>
                <td>${book.author.surname}</td>
                <td>${book.author.name}</td>
                <td>${book.author.middleName}</td>
                <td>${book.author.countryBirth}</td>
                <td>${book.dateEdition}</td>
                <td>${book.placeEdition}</td>
                <td>${book.publisher}</td>
                <td>${book.numberCopies}</td>
                <td>
                    <div class="function-book">
                        <a class="a-function"
                           href="${pageContext.request.contextPath}/Controller?id_book=${book.id}&command=delete_book"><fmt:message
                                key="label.delete" bundle="${local}"/>
                            <span class="glyphicon glyphicon-trash"></span></a>
                        <a class="a-function"
                           href="${pageContext.request.contextPath}/Controller?id_book=${book.id}&command=edit_book"><fmt:message
                                key="label.edit" bundle="${local}"/><span class="glyphicon glyphicon-pencil"></span></a>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </c:when>
    <c:otherwise>
        <err:error errorMessage="${empyData}"/>
    </c:otherwise>
    </c:choose>
</form>

<ul class="pagination place-pagination">
    <c:if test="${currentPage gt 1}">
        <li>
            <a class="page-link"
               href="${pageContext.request.contextPath}/Controller?command=show_books&page=${currentPage - 1}"> ←</a>
        </li>
    </c:if>
    <c:forEach begin="1" end="${numberOfPages}" var="i">
        <c:choose>
            <c:when test="${currentPage eq i}">
                <li>
                    <a class="page-link">${i}</a>
                </li>
            </c:when>
            <c:otherwise>
                <li>
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/Controller?command=show_books&page=${i}">${i}</a>
                </li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${currentPage lt numberOfPages}">
        <li>
            <a class="page-link"
               href="${pageContext.request.contextPath}/Controller?command=show_books&page=${currentPage + 1}"> →</a>
        </li>
    </c:if>
</ul>

<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"/>
</body>
</html>

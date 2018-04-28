<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="err" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale/locale" var="local"/>

<fmt:message key="label.tittle" bundle="${local}" var="tittle"/>
<fmt:message key="label.surnameAuthor" bundle="${local}" var="surname"/>
<fmt:message key="label.genre" bundle="${local}" var="genre"/>
<fmt:message key="label.searchTittle" bundle="${local}" var="searchTittle"/>
<fmt:message key="label.searchGenre" bundle="${local}" var="searchGenre"/>
<fmt:message key="label.searchAuthor" bundle="${local}" var="searchAuthor"/>
<fmt:message key="label.addToCart" bundle="${local}" var="addToCart"/>
<fmt:message key="label.moreInfo" bundle="${local}" var="moreInfo"/>
<fmt:message key="message.emptyData" bundle="${local}" var="empyData"/>

<c:set var="user" scope="session" value="${sessionScope.user}"/>
<c:set var="currentPage" scope="session" value="${sessionScope.currentPage}"/>
<c:set var="numberOfPages" scope="request" value="${requestScope.numberOfPages}"/>

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

<div class="form-inline found-div">
    <div class="one-found-div">
        <form action="${pageContext.request.contextPath}/Controller" method="post">
            <input type="hidden" name="command" value="find_book_by_tittle"/>
            <input type="text" name="book" placeholder="${tittle}" class="find-book-input"/>
            <input type="submit" value="${searchTittle}" class="find-book"/>
        </form>
    </div>

    <div class="one-found-div">
        <form action="${pageContext.request.contextPath}/Controller" method="post">
            <input type="hidden" name="command" value="find_book_by_author"/>
            <input type="text" name="author"
                   placeholder="${surname}" class="find-book-input">
            <input type="submit" value="${searchAuthor}" class="find-book"/>

        </form>
    </div>

    <div class="one-found-div">
        <form action="${pageContext.request.contextPath}/Controller" method="post">
            <input type="hidden" name="command" value="find_book_by_genre"/>
            <input type="text" name="genre" placeholder=${genre} class="find-book-input">
            <input type="submit" value="${searchGenre}" class="find-book"/>
        </form>
    </div>
</div>

<c:choose>
    <c:when test="${sessionScope.books != null}">
        <form action="${pageContext.request.contextPath}/Controller" method="post">
            <table class="table table-hover">
                <thead class="table-thead">
                <tr>
                    <th><fmt:message key="label.image" bundle="${local}"/></th>
                    <th><fmt:message key="label.tittle" bundle="${local}"/></th>
                    <th><fmt:message key="label.genre" bundle="${local}"/></th>
                    <th><fmt:message key="label.surname" bundle="${local}"/></th>
                    <th><fmt:message key="label.name" bundle="${local}"/></th>
                    <th><fmt:message key="label.middleName" bundle="${local}"/></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="book" items="${books}">
                <tr>
                    <td><img src="/resource/images/book/${book.image}" alt="${book.tittle}" class="imageBook"></td>
                    <td>${book.tittle}</td>
                    <td>${book.genres}</td>
                    <td>${book.author.surname}</td>
                    <td>${book.author.name}</td>
                    <td>${book.author.middleName}</td>
                    <td>
                        <div class="function-book">
                            <c:if test="${sessionScope.role == 'reader'}">
                                <a class="a-function"
                                   href="${pageContext.request.contextPath}/Controller?&id_reader=${user.id}&id_book=${book.id}&id_author=${book.author.id}&command=add_to_cart">${addToCart}
                                    <span class="glyphicon glyphicon-shopping-cart"></span></a>
                            </c:if>
                            <a class="a-function"
                               href="${pageContext.request.contextPath}/Controller?id_book=${book.id}&command=get_personal_book">${moreInfo}</a>
                        </div>
                    </td>
                    </c:forEach>
                </tr>
                </tbody>
            </table>
        </form>
    </c:when>
    <c:otherwise>
        <err:error errorMessage="${empyData}"/>
    </c:otherwise>
</c:choose>

<ul class="pagination place-pagination">
    <c:if test="${currentPage gt 1}">
        <li>
            <a class="page-link"
               href="${pageContext.request.contextPath}/Controller?command=get_books&page=${currentPage - 1}"> ←</a>
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
                       href="${pageContext.request.contextPath}/Controller?command=get_books&page=${i}">${i}</a>
                </li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${currentPage lt numberOfPages}">
        <li>
            <a class="page-link"
               href="${pageContext.request.contextPath}/Controller?command=get_books&page=${currentPage + 1}"> →</a>
        </li>
    </c:if>
</ul>

<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"/>
</body>
</html>

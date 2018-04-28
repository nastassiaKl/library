<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="err"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale/locale" var="local"/>

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

<div class="container">

    <err:error errorMessage="${messageFindBook}"/>

    <c:if test="${requestScope.foundBooks != null}">
    <table class="table table-hover">
        <thead class="table-thead">
        <jsp:include page="${pageContext.request.contextPath}/jsp/user/book/tableHead.jsp"/>
        </thead>
        <tbody>
        <c:forEach var="foundBooks" items="${foundBooks}">
            <tr>
                <td>
                    <img src="/resource/images/book/${foundBooks.image}"
                         alt="${foundBooks.tittle}" class="imageBook">
                </td>
                <td>${foundBooks.isbn}</td>
                <td>${foundBooks.tittle}</td>
                <td>${foundBooks.genres}</td>
                <td>${foundBooks.author.surname}</td>
                <td>${foundBooks.author.name}</td>
                <td>${foundBooks.author.middleName}</td>
                <td>${foundBooks.dateEdition}</td>
                <td>${foundBooks.placeEdition}</td>
                <td>${foundBooks.publisher}</td>
                <td>${foundBooks.numberCopies}</td>
                <td>
                    <c:if test="${sessionScope.role == 'reader'}">
                        <a class="a-function"
                           href="${pageContext.request.contextPath}/Controller?&number_ticket=${user.numberTicket}&id_book=${foundBooksByTittle.id}&id_author=${foundBooksByTittle.author.id}&command=add_to_cart">${addToCart}</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </c:if>
        </tbody>
    </table>
</div>

<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"/>
</body>
</html>

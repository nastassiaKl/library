<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="user" scope="session" value="${sessionScope.reader}"/>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale/locale" var="local"/>

<fmt:message key="label.logIn" bundle="${local}" var="logIn"/>
<fmt:message key="label.registration" bundle="${local}" var="registration"/>
<fmt:message key="label.main" bundle="${local}" var="main"/>
<fmt:message key="label.books" bundle="${local}" var="books"/>
<fmt:message key="label.authors" bundle="${local}" var="authors"/>
<fmt:message key="label.librarians" bundle="${local}" var="librarians"/>
<fmt:message key="label.readers" bundle="${local}" var="readers"/>
<fmt:message key="label.account" bundle="${local}" var="account"/>
<fmt:message key="label.orderCart" bundle="${local}" var="orderCart"/>
<fmt:message key="label.orders" bundle="${local}" var="orders"/>
<fmt:message key="label.logout" bundle="${local}" var="logout"/>

<html>
<head>
</head>
<body>
<div class="container">

    <form action="${pageContext.request.contextPath}/Controller" method="post">
        <input type="hidden" name="command" value="locale">
        <input type="hidden" name="url" value="${pageContext.request.requestURI}">
        <div class="form-group footer-button">
            <button class="button-footer" type="submit" name="locale" value="en">EN</button>
            <button class="button-footer" type="submit" name="locale" value="ru">RU</button>
            <button class="button-footer" type="submit" name="locale" value="be">BY</button>
        </div>
    </form>

    <form action="${pageContext.request.contextPath}/Controller" method="post">
        <c:if test="${sessionScope.role == 'guest'}">
            <ul class="menu-main">
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=load_page&page=/jsp/common/start.jsp">${main}</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=get_books">${books}</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=load_page&page=/jsp/common/registration.jsp">${registration}</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=load_page&page=/jsp/common/login.jsp">${logIn}</a>
                </li>
            </ul>
        </c:if>

        <c:if test="${sessionScope.role == 'admin'}">
            <ul class="menu-main">
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=show_books">${books}</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=show_authors">${authors}</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=show_librarians">${librarians}</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=show_readers">${readers}</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=logout">${logout}</a>
                </li>
            </ul>
        </c:if>


        <c:if test="${sessionScope.role == 'reader'}">
            <ul class="menu-main">
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=load_page&page=/jsp/user/main.jsp">${main}</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=get_books">${books}</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?id_reader=${user.id}&command=get_personal_orders">${orderCart}</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?number_ticket=${user.numberTicket}&command=account">${account}</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=logout">${logout}</a>
                </li>
            </ul>
        </c:if>


        <c:if test="${sessionScope.role == 'librarian'}">
            <ul class="menu-main">
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=get_books">${books}</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=show_orders">${orders}</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Controller?command=logout">${logout}</a>
                </li>
            </ul>
        </c:if>
    </form>
</div>
</body>
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="err" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale/locale" var="local"/>

<fmt:message key="message.emptyData" bundle="${local}" var="empyData"/>

<c:set var="reader" scope="session" value="${sessionScope.reader}"/>

<html>
<head>
    <title><fmt:message key="label.orderCart" bundle="${local}"/></title>

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

<err:error errorMessage="${messageOrders}"/>

<err:error errorMessage="${messageApprovedOrders}"/>

<div class="container">

    <a class="a-function-orders"
       href="${pageContext.request.contextPath}/Controller?id_reader=${reader.id}&command=get_approved_orders"><fmt:message
            key="label.watchApprovedOrders" bundle="${local}"/>
        <span class="glyphicon glyphicon-ok"></span></a>

    <c:choose>
        <c:when test="${not empty requestScope.orders}">
            <table class="table table-hover">
                <thead class="table-thead">
                <tr>
                    <th><fmt:message key="label.numberTicket" bundle="${local}"/></th>
                    <th><fmt:message key="label.tittle" bundle="${local}"/></th>
                    <th><fmt:message key="label.surname" bundle="${local}"/></th>
                    <th><fmt:message key="label.name" bundle="${local}"/></th>
                    <th><fmt:message key="label.middleName" bundle="${local}"/></th>
                    <th><fmt:message key="label.image" bundle="${local}"/></th>
                    <td></td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.user.numberTicket}</td>
                        <td>${order.book.tittle}</td>
                        <td>${order.author.surname}</td>
                        <td>${order.author.name}</td>
                        <td>${order.author.middleName}</td>
                        <td><img src="/resource/images/book/${order.book.image}" alt="${order.book.tittle}"
                                 class="imageBook">
                        </td>
                        <td>
                            <a class="a-function"
                               href="${pageContext.request.contextPath}/Controller?id_order=${order.id}&id_reader=${order.user.id}&command=delete_order"><fmt:message
                                    key="label.delete" bundle="${local}"/>
                                <span class="glyphicon glyphicon-trash"></span></a>
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
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"/>
</body>
</html>

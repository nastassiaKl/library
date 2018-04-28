<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale/locale" var="local"/>

<html>
<head>
    <title><fmt:message key="label.approvedOrders" bundle="${local}"/></title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">

</head>
<body class="body">
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"/>

<div class="container">
    <c:if test="${requestScope.approvedOrders != null}"/>
    <table class="table table-hover">
        <thead class="table-thead">
        <tr>
            <th><fmt:message key="label.tittle" bundle="${local}"/></th>
            <th><fmt:message key="label.dateBorrow" bundle="${local}"/></th>
            <th><fmt:message key="label.dateReturn" bundle="${local}"/></th>
            <th><fmt:message key="label.methodBorrow" bundle="${local}"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${approvedOrders}">
            <tr>
                <td>${order.book.tittle}</td>
                <td>${order.dateBorrow}</td>
                <td>${order.dateReturn}</td>
                <td>${order.methodBorrow}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"/>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale/locale" var="local"/>

<fmt:message key="label.date" bundle="${local}" var="date"/>
<fmt:message key="label.subscription" bundle="${local}" var="subscription"/>
<fmt:message key="label.readingRoom" bundle="${local}" var="readingRoom"/>
<fmt:message key="label.takeOrder" bundle="${local}" var="takeOrder"/>

<html>
<head>
    <title><fmt:message key="label.orders" bundle="${local}"/></title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resource/images/icon.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app-style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/resource/js/order/order.js"></script>
    <script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>

</head>
<body class="body">
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/layout.jsp"/>

<div class="container">
    <form action="${pageContext.request.contextPath}/Controller" method="post" name="form" onsubmit="return checkOrder();" class="form-param">
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.dateBorrow" bundle="${local}"/> </label>
            <input type="text" name="date_borrow" placeholder="${date}" class="form-control">
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.dateReturn" bundle="${local}"/> </label>
            <input type="text" name="date_return" placeholder="${date}" class="form-control">
        </div>
        <div class="form-group">
            <label class="my-label"><fmt:message key="label.methodBorrow" bundle="${local}"/> </label><br/>
            <label class="radio-inline">
                <input type="radio" name="method_borrow" value="Абонемент">${subscription}
            </label>
            <label class="radio-inline">
                <input type="radio" name="method_borrow" value="Читательный зал">${readingRoom}
            </label>
        </div>
        <div class="form-inline">
            <input type="hidden" name="command" value="take_order"/>
            <input type="hidden" name="id_order" value="${order.id}"/>
            <input type="hidden" name="id_reader" value="${order.user.id}"/>
            <input type="hidden" name="mail" value="${order.user.mail}"/>
            <input type="hidden" name="id_book" value="${order.book.id}"/>
            <input type="hidden" name="tittle" value="${order.book.tittle}"/>
            <input type="submit" value="${takeOrder}" class="button"/><br/>
            <button type="reset" value="clear" onclick="clearForm()" class="button"><fmt:message key="label.clear" bundle="${local}"/></button>
        </div>
    </form>
</div>
</body>
</html>

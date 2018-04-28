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
    <title><fmt:message key="label.authors" bundle="${local}"/></title>

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

<suc:success successMessage="${messageDeleteAuthor}"/>

<div class="container">

    <c:choose>
    <c:when test="${not empty sessionScope.authors}">
    <form action="Controller" method="post">
        <table class="table table-hover table-condensed">

            <thead class="table-thead">
            <tr>
                <th><fmt:message key="label.surnameAuthor" bundle="${local}"/></th>
                <th><fmt:message key="label.nameAuthor" bundle="${local}"/></th>
                <th><fmt:message key="label.middleNameAuthor" bundle="${local}"/></th>
                <th><fmt:message key="label.country" bundle="${local}"/></th>
                <th></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="author" items="${authors}">
                <tr>
                    <td>${author.surname}</td>
                    <td>${author.name}</td>
                    <td>${author.middleName}</td>
                    <td>${author.countryBirth}</td>
                    <td>
                        <div class="functions">
                            <a class="a-function"
                               href="${pageContext.request.contextPath}/Controller?id_author=${author.id}&command=delete_author"><fmt:message
                                    key="label.delete" bundle="${local}"/> <span
                                    class="glyphicon glyphicon-trash"></span></a>
                            <a class="a-function"
                               href="${pageContext.request.contextPath}/Controller?id_author=${author.id}&command=edit_author"><fmt:message
                                    key="label.edit" bundle="${local}"/> <span
                                    class="glyphicon glyphicon-pencil"></span></a>
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
</div>
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"/>
</body>
</html>

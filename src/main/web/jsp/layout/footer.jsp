<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="mytag"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale/locale" var="local"/>

<html>
<head>
</head>
<body class="body">
<footer>
    <div class="row">
        <div class="col-lg-12">
            <mytag:footer />
        </div>
    </div>
</footer>
</body>
<script src="${pageContext.request.contextPath}/resource/js/bootstrap.js"></script>
</html>

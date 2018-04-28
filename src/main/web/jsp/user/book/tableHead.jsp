<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale/locale" var="local"/>

<fmt:message key="label.image" bundle="${local}" var="image"/>
<fmt:message key="label.isbn" bundle="${local}" var="ISBN"/>
<fmt:message key="label.tittle" bundle="${local}" var="tittle"/>
<fmt:message key="label.surname" bundle="${local}" var="surname"/>
<fmt:message key="label.name" bundle="${local}" var="name"/>
<fmt:message key="label.middleName" bundle="${local}" var="middleName"/>
<fmt:message key="label.genre" bundle="${local}" var="genre"/>
<fmt:message key="label.dateEdition" bundle="${local}" var="dateEdition"/>
<fmt:message key="label.placeEdition" bundle="${local}" var="placeEdition"/>
<fmt:message key="label.publisher" bundle="${local}" var="publisher"/>
<fmt:message key="label.numberCopies" bundle="${local}" var="numberCopies"/>
<html>
<head>

</head>
<body>
<tr>
    <th>${image}</th>
    <th>${ISBN}</th>
    <th>${tittle}</th>
    <th>${genre}</th>
    <th>${surname}</th>
    <th>${name}</th>
    <th>${middleName}</th>
    <th>${dateEdition}</th>
    <th>${placeEdition}</th>
    <th>${publisher}</th>
    <th>${numberCopies}</th>
    <th></th>
</tr>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Yevhenii Khudolii
  Date: 12.10.2020
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: eberk
  Date: 07.10.2020
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Watches</title>
    <link rel="stylesheet" type="text/css" href="resources/styles.css">
</head>
<body>
<table>
    <thead>
    <tr>
        <th>id</th>
        <th>Mark</th>
        <th>Type</th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="watchBean" scope="request" type="beans.WatchBean"/>
    <c:forEach items="${watchBean.watches}" var="c">
        <tr>
            <td>${c.id}</td>
            <td>${c.mark}</td>
            <td>${c.type}</td>
        </tr>
    </c:forEach>
    </tbody>
    <a href="showcountries.jsp" type="button">Back to Countries</a>
</table>
</body>
</html>

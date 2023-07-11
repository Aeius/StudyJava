<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-07-03
  Time: 오후 1:34
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html> 
	<head> 
    	<meta charset="utf-8">
<title>웹페이지 제목</title>
	</head> 
	<body>
        <h1>index page</h1>
        <table>
            <tr>
                <th>Num</th>
                <th>Name</th>
                <th>Age</th>
            </tr>
            <c:forEach var="people" items="${peopleList}">
            <tr>
                <td>${people.num}</td>
                <td>${people.name}</td>
                <td>${people.age}</td>
            </tr>
            </c:forEach>
        </table>

        <a href="/add">추가</a>
	</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-07-03
  Time: 오후 3:34
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html> 
	<head> 
    	<meta charset="utf-8">
<title>웹페이지 제목</title>
	</head> 
	<body>
        <form action="add" method="post">
            name : <input type="text" name="name" />
            <br>
            age : <input type="tel" name="age" />
            <br>
            <button type="submit">추가</button>
            <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
        </form>
	</body>
</html>
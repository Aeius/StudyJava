<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-07-19
  Time: 오후 1:56
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
	    <h2>로그인</h2>
        <form action="login" method="post">
            username : <input type="text" id="username" name="username"> <br>
            password : <input type="password" id="password" name="password"> <br>
            role : <input type="radio" class="role" name="role" value="ADMIN" checked >ADMIN <input type="radio" class="role" name="role" value="USER">USER <br>
            <button type="submit">로그인</button>

            <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
        </form>
        <a href="/register">회원가입으로</a>
	</body>
</html>
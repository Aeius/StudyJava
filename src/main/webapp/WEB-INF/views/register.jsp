<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-07-19
  Time: 오후 1:48
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
	    <h2>회원가입</h2>
	    <form action="register" method="post">
            username : <input type="text" id="username" name="username"> <br>
            password : <input type="password" id="password" name="password"> <br>
            role : <input type="radio" class="role" name="role" value="ADMIN" checked >ADMIN <input type="radio" class="role" name="role" value="USER" >USER <br>
            <button type="submit">가입</button>
            <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
        </form>
        <a href="/login">로그인으로</a>
	</body>
</html>
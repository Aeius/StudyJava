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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    	<meta charset="utf-8">
    	<style>
            #modal {
                display:none;
            }
    	</style>
<title>나만의 테스트 페이지</title>
	</head> 
	<body>
        <h1>index page</h1>
        <table>
            <tr>
                <th>Num</th>
                <th>Name</th>
                <th>Age</th>
                <th></th>
            </tr>
            <c:forEach var="people" items="${peopleList}">
            <tr>
                <td>${people.num}</td>
                <td>${people.name}</td>
                <td>${people.age}</td>
                <td><button type="button" onclick="peopleUpdate(${people.num}, '${people.name}', ${people.age})">수정</button></td>
                <td><button type="button" onclick="peopleDelete(${people.num})">삭제</button></td>
            </tr>
            </c:forEach>
        </table>

        <a href="/add">추가</a>
        <br/>
        @Value 테스트 결과 : ${test } <br>
        @Value 테스트 결과 : ${thunder }

        <form action="logout" method="post">
            <button type="submit">로그아웃</button>
        </form>
        <!-- modal -->
        <div id="modal">
            <h4>수정</h4>
            <form action="add" method="post">
                num : <input type="text" name="num" id="num" readonly/>
                <br>
                name : <input type="text" name="name" id="name" />
                <br>
                age : <input type="tel" name="age" id="age" />
                <br>
                <button type="submit">확정</button>
                <button type="button" class="close-modal">닫기</button>
            </form>
        </div>
	</body>
	<script>
        function peopleDelete(num) {
	        var ajax = $.ajax(){
	            type: 'post',
	            url: 'http://localhost:8080/delete/'+num,
	        }
	        ajax.done(function(){
	            alert(num + '번 삭제 성공!')
	        })
	    }

	    function peopleUpdate(num, name, age) {
	        console.log('update')
            $('#modal').css('display','block')
            $('#num').val(num)
            $('#name').val(name)
            $('#age').val(age)
	    }

	    $('.close-modal').on('click', function() {
	        $('#modal').css('display','none')
	    })
	</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<h1>joinFrm.jsp- 회원가입 양식</h1>
<form action="join" name="joinFrm" method="post">
아이디: <input type="text" name="id"><br> 
비번: <input	type="password" name="pw"><br>
이름:<input type="text" name="name"><br>
<!-- 성별:<input type="radio" name="gender" value="남자">남자 -->
<!-- <input type="radio" name="gender" value="여자">여자<br> -->
<button>회원가입</button>
<button type="reset">취소</button>
</form>
</body>
</html>
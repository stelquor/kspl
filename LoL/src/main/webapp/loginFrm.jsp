<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>loginFrm.jsp - 로그인 화면</h1>
	<form action="/login" method="post">
		아이디: <input type="text" name="id"><br> 
		비번: <input	type="password" name="pw"><br>
		<button>로그인</button>
		<a href="/joinfrm">회원가입</a>
	</form>
</body>
</html>
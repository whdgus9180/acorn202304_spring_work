<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>회원 정보 수정 양식</h1>
		<form action="update" method="post">
			<div>
				<label for="num">번호</label>
				<input type="text" id="num" name="num" value="${dto.num }" readonly>
			</div>
			<br>
			<div>
				<label for="name">이름</label>
				<input type="text" id="name" name="name" value="${dto.name }"/>
			</div>
			<br>
			<div>
				<label for="addr">주소</label>
				<input type="text" id="addr" name="addr" value="${dto.addr }"/>
			</div>
			<br>
			<button type="submit">수정확인</button>
			<button type="reset">취소</button>
		</form>
	</div>
</body>
</html>
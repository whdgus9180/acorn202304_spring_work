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
		<h1>작성자 방명록 수정 양식</h1>
		<form action="update" method="post">
			<div>
				<label for="num">번호</label>
				<input type="text" id="num" name="num" value="${dto.num }" readonly>
			</div>
			<br>
			<div>
				<label for="writer">작성자</label>
				<input type="text" id="writer" name="writer" value="${dto.writer }"/>
			</div>
			<br>
			<div>
				<label for="content">내용</label>
				<textarea name="content" id="content" cols="30" rows="10" >${dto.content }</textarea>
			</div>
			<div>
				<label for="pwd">글 작성시 입력했던 비밀번호</label>
				<input type="password" id="pwd" name="pwd"/>
			</div>
			<br>
			<button type="submit">수정확인</button>
			<button type="reset">취소</button>
		</form>
	</div>
</body>
</html>
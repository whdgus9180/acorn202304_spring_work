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
	<h1>방명록 추가 폼</h1>
	<form action="${pageContext.request.contextPath }/guest/insert" method="post">
		<div>
			<laber for="writer">작성자</laber>
			<input type="text" name="writer" id="writer"/>
		</div>
		<div>
			<laber for="content">내용</laber>
			<textarea name="content" id="content" cols="30" rows="10"></textarea>
		</div>
		<div>
			<laber for="pwd">비밀번호</laber>
			<input type="password" name="pwd" id="pwd"/>
		</div>
		<button type="submit">등록</button>
	</form>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>알림</h1>
		<p>
			<strong>${param.num }</strong> 번님의 정보를 삭제 했습니다.
			<a href="${pageContext.request.contextPath }/member/list">확인</a>
		</p>
	</div>
</body>
</html>
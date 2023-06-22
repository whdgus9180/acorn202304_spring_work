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
		<h1>회원 추가 폼</h1>
		<form action="${pageContext.request.contextPath }/member/insert" method="post">
			<div>
				<laber for="name">이름</laber>
				<input type="text" name="name" id="name"/>
			</div>
			<div>
				<laber for="addr">주소</laber>
				<input type="text" name="addr" id="addr"/>
			</div>
			<button type="submit">추가</button>
		</form>
		
	</div>
</body>
</html>
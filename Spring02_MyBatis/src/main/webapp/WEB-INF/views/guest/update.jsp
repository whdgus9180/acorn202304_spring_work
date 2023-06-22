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
	<script>
		alert("${param.num} 번 작성자의 방명록을 수정했습니다!");
		//javascript 를 이용해서 페이지 이동시키기(리다일렉트 이동)
		location.href="${pageContext.request.contextPath }/guest/list"
	</script> 
</body>
</html>
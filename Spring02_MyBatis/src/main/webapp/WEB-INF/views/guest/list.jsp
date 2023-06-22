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
		<a href="${pageContext.request.contextPath }/guest/insertform">방명록 추가</a>
		<h1>방명록 목록입니다.</h1>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>작성자</th>
					<th>내용</th>	
					<th>등록일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="tmp" items="${list }">
					<tr>
						<td>${tmp.num }</td>
						<td>${tmp.writer }</td>
						<td>
						<textarea rows="5">${tmp.content }</textarea>
						</td>
						<td>${tmp.regdate }</td>
						<td>
						<a href="updateform?num=${tmp.num }">수정</a>
						</td>
						<td>
							<form action="delete" method="post">
								<input type="hidden" name="num" value="${tmp.num }"/>
								<input type="password" name="pwd" placeholder="비밀번호..."/>
								<button type="submit">삭제</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
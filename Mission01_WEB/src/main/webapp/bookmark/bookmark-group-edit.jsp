<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>북마크 그룹 수정</h1>
	<jsp:include page="/header.jsp" />
	<table>
		<tr>
			<th>북마크 이름</th><td><input type="text"></td>
		</tr>	
		<tr>
			<th>순서</th><td><input type="text"></td>
		</tr>
		<tr>
			<td><a href="">돌아가기</a> | <button value="">수정</button></td>
		</tr>
	</table>
</body>
</html>
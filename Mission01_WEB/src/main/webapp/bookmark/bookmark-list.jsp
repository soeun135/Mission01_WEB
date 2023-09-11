<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<link href="/table.css" rel="stylesheet" type="text/css" />

</head>
<body>
<h1>북마크 목록</h1>
	<jsp:include page="/header.jsp" />
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>북마크 이름</th>
				<th>와이파이명</th>
				<th>등록일자</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
		<!-- 북마크 저장 테이블에서 가져온 값 쏴주기 -->
		<c:forEach items="${lists }" var="bm">
			<tr>
				<td>${bm.id }</td>
				<td>${bm.bookmarkName }</td>
				<td>${bm.wifiName }</td>
				<td>${bm.makeDate }</td>
				<td><a href="/bookmark/bookmarkdel?id=${bm.id }">삭제</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>
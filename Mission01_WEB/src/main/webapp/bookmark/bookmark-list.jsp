<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>북마크 그룹</h1>
	<jsp:include page="/header.jsp" />
	<div>
		<button onclick="bmgroupadd_()">북마크 그룹 이름 추가</button>
		<script>
			function bmgroupadd_() {
				location.href="bookmark-group-add.jsp";
			}
		</script>
	</div>
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
		<!-- 북마크 그룹 테이블에서 가져온 값들 쏴주기 -->
		<c:forEach items="${lists }" var="bm">
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="/bookmark/bookmarkdel?id=">삭제</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>
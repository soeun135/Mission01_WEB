<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<link href="/table.css" rel="stylesheet" type="text/css" />
<style>
tbody {
	text-align : right;
}
</style>
</head>
<body>
	<h1>북마크 그룹 추가</h1>
	<jsp:include page="/header.jsp" />
	<br>
	<table>
	<thead>
		<tr>
			<th>북마크 이름</th><td><input type="text" id="name"></td>
		</tr>	
		<tr>
			<th>순서</th><td><input type="text" id="order"></td>
		</tr>
	</thead>
	</tbody>
		<tr>
			<td><button value="" onclick="bmgroupadd_()">추가</button></td>
		</tr>
	</tbody>
	</table>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
		<script>
			function bmgroupadd_() {
				var n = document.getElementById('name').value;
				var o = document.getElementById('order').value
					
				$.ajax({
					url:"bookmarkadd",
					type:'get',
					data: {name:n, order:o}
				});
			}
		</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<style>
table {
  border: 1px solid #696969;
}
th, td {
padding: 5px;
  border: solid white , 
}
th {
	background:#04AA6D;
	color : white;
}
td {
	width : 5%;
}
tr:nth-child(even) {background-color: #f2f2f2;}

tbody {
	text-align : right;
}
</style>

</head>
<body>
	<h1>북마크 그룹 수정</h1>
	<jsp:include page="/header.jsp" />
	<table>
	<thead>
		<tr>
			<th>북마크 이름</th><td><input type="text" id="name"></td>
		</tr>	
		<tr>
			<th>순서</th><td><input type="text" id="order"></td>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td><a href="/bookmark/bookmarkglist">돌아가기</a> | <button value="" onclick="bookmarkedit_()">수정</button></td>
		</tr>
		</tbody>
	</table>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
		<script>
		function bookmarkedit_() {
			var n = document.getElementById('name').value;
			var o = document.getElementById('order').value
			var i = getParameterByName('id');
			console.log(n);
			console.log(o);
			$.ajax({
				url:"bookmarkedit",
				type:'get',
				data: {id:i,name : n, order:o}
			});
		}
		function getParameterByName(name) {
			  name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
			  var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
			  results = regex.exec(location.search);
			  return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
			}
		</script>
</body>
</html>
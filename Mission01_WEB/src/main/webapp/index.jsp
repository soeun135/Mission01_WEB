<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="api.APIService" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<style>
thead {
	height:50px;
	background: pink;
}
th, td {
  border: 1px solid white ,
}
</style>
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
	<%
		APIService apiService = new APIService();
	%>
	<div>
		<a href="index.jsp">홈</a> |
		<a href="index.jsp">위치 히스토리 목록</a> |
		<a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
	</div>
	<div>
		LAT: <input type="text" id="x" value="0.0">, 
		LNT: <input type="text" id="y" value="0.0">
		<button id="my-Space" onclick="calDist_()">내 위치 가져오기</button>
		<script>
		<% double xx = 0; double yy = 0; %>
    		function calDist_() {
    			var x = document.getElementById('x').value;
			var y = document.getElementById('y').value;
			<%=xx %> = x;
			<%=yy %> = y;
	  		<% apiService.calDist(xx, yy); %>
    		}
  		</script>
		<input type="button" value="근처 WIPI 정보 보기">
		
	</div>
	<table>
		<thead>
			<tr>
				<th>거리</th>
				<th>관리번호</th>
				<th>자치구</th>
				<th>와이파이명</th>
				<th>도로명주소</th>
				<th>상세주소</th>
				<th>설치위치(층)</th>
				<th>설치유형</th>
				<th>설치기관</th>
				<th>서비스구분</th>
				<th>망종류</th>
				<th>설치년도</th>
				<th>실내외구분</th>
				<th>WIFI접속환경</th>
				<th>X좌표</th>
				<th>Y좌표</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</body>
</html>
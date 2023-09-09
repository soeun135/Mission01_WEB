<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="api.APIService"%>
<%@ page import="java.util.List"%>
<%@ page import="dto.WifiHistory"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<h1>위치 히스토리 목록</h1>
	<div>
		<a href="index.jsp">홈</a> | <a href="index.jsp">위치 히스토리 목록</a> | <a
			href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
	</div>
	<%
		APIService apiService = new APIService();
		List<WifiHistory> history = apiService.showHistory();
	%>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>조회일자</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<%
				for (WifiHistory wifi : history) {
				%>
			
			<tr>
				<td><%=wifi.getId()%></td>
				<td><%=wifi.getX()%></td>
				<td><%=wifi.getY()%></td>
				<td><%=wifi.getMakeDate()%></td>
				<td><button>삭제</button>
			</tr>
			<%
			}
			%>
			</tr>
		</tbody>
	</table>
</body>
</html>
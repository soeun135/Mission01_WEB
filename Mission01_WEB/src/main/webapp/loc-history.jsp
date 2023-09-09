<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="api.APIService"%>
<%@ page import="java.util.List"%>
<%@page import="dto.WifiHistory"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
			</tr>
			<%
			}
			%>
			</tr>
		</tbody>
	</table>
</body>
</html>
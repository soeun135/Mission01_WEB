<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
				%>
			
			<tr>
				<td><%=wifi.getManageNo()%></td>
				<td><%=wifi.getDist()%></td>
				<td><%=wifi.getGugun()%></td>
				<td><%=wifi.getWifiName()%></td>
				<td><%=wifi.getAddress()%></td>
			</tr>
			<%
			%>
			</tr>
		</tbody>
	</table>
</body>
</html>
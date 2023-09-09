<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="api.APIService"%>
<%@ page import="dto.WifiList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
thead {
	height: 50px;
	background: pink;
}

th, td {
	border: 1px solid white,
}
</style>
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
	<div>
		<a href="index.jsp">홈</a> | <a href="index.jsp">위치 히스토리 목록</a> | <a
			href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
	</div>
	<%
	APIService apiService = new APIService();
	List<WifiList> wifiListResult = apiService.showWifi();
	%>

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
				<th>작업일자</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<%
				for (WifiList wifi : wifiListResult) {
				%>
			
			<tr>
				<td><%=wifi.getManageNo()%></td>
				<td><%=wifi.getDist()%></td>
				<td><%=wifi.getGugun()%></td>
				<td><%=wifi.getWifiName()%></td>
				<td><%=wifi.getAddress()%></td>
				<td><%=wifi.getDetailAddress()%></td>
				<td><%=wifi.getSetFloor()%></td>
				<td><%=wifi.getSetType()%></td>
				<td><%=wifi.getSetOrgan()%></td>
				<td><%=wifi.getServiceDivision()%></td>
				<td><%=wifi.getNetworkKind()%></td>
				<td><%=wifi.getSetYear()%></td>
				<td><%=wifi.getInOutdoor()%></td>
				<td><%=wifi.getWificonnectEnvirionment()%></td>
				<td><%=wifi.getX()%></td>
				<td><%=wifi.getY()%></td>
				<td><%=wifi.getWorkDate()%></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
</body>
</html>
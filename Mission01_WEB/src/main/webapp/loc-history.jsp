<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="dto.WifiHistory"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<link href="table.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<h1>위치 히스토리 목록</h1>
	<jsp:include page="header.jsp"/>
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
			<c:forEach items= "${lists }" var="wifi">
			<tr>
				<td>${wifi.id }</td>
				<td>${wifi.x }</td>
				<td>${wifi.y }</td>
				<td>${wifi.makeDate }</td>
				<td><button id="delbtn" value="${wifi.id }" onclick='del_()'>삭제</button>
				<script>
				function del_() {
					var x = document.getElementById('delbtn').value;
					console.log(x);
					location.href='historydel?id='+x;
				}
				</script>
			</tr>
			</c:forEach>
			</tr>
		</tbody>
	</table>
</body>
</html>
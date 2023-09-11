<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<link href="table.css" rel="stylesheet" type="text/css" />
</style>
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
	<jsp:include page="header.jsp"/>

	<div>
		LAT: <input type="text" id="x" value="0.0">, 
		LNT: <input type="text" id="y" value="0.0">
		<button onclick="calDist_()">내 위치 가져오기</button> 
		<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
		<script>
		var xv;
		var yv;
    		function calDist_() {
    			xv = document.getElementById('x').value;
			yv = document.getElementById('y').value;
    		$.ajax({
    			url:"distupdate",
    			type:'get',
    			data: { x: xv, y: yv}
    		});
    		document.getElementById('x').value = xv;
    		document.getElementById('y').value = yv;
    		}
    		
  		</script>
  		<input type="button" onclick="loadWifi_()" value="근처 WIPI 정보 보기">
		<script>
		function loadWifi_() {
			document.getElementById('x').value = xv;
			document.getElementById('y').value = yv;

			location.href='showwifi';
		}
		</script> 
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
				<th>작업일자</th>
				
			</tr>
		</thead>
		<tbody>
		<c:if test="${lists eq null}" var="result">
		</table>
			<p style="text-align: center;">위치정보를 입력한 후에 조회해주세요.</p>
	
		</c:if>
		<c:forEach items= "${lists }" var="dto">
		<tr>
			<td>${dto.dist }</td>
			<td>${dto.manageNo }</td>
			<td>${dto.gugun }</td>
			<td><a href="wifi-detail.jsp?manageNo=${dto.manageNo }">${dto.wifiName }</a></td>
			<td>${dto.address }</td>
			<td>${dto.detailAddress }</td>
			<td>${dto.setFloor }</td>
			<td>${dto.setType }</td>
			<td>${dto.setOrgan }</td>
			<td>${dto.serviceDivision }</td>
			<td>${dto.networkKind }</td>
			<td>${dto.setYear }</td>
			<td>${dto.inOutdoor }</td>
			<td>${dto.wificonnectEnvirionment }</td>
			<td>${dto.x }</td>
			<td>${dto.y }</td>
			<td>${dto.workDate }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>
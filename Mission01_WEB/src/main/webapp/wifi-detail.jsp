<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.ShowWifiDAO" %>
<%@ page import="dto.WifiList" %>
<%@ page import="dto.BookMark" %>
<%@ page import="dao.BookmarkGroupDAO" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
WifiList wifiList = new WifiList();
	ShowWifiDAO dao = new ShowWifiDAO(); 
	wifiList = dao.findBymanageNo(request.getParameter("manageNo"));
	
	//BookMark bookmark = new BookMark();
	BookmarkGroupDAO bDao = new BookmarkGroupDAO();
	List<BookMark> bookmark = bDao.showList();
%>
	<h1>와이파이 정보 구하기</h1>
	<jsp:include page="header.jsp" />
	<form method="get" action="/bookmark/bookmarkListadd">
	<select id="bookmarkGroup">
			<option>북마크 그룹 이름 선택</option>
			<% for(BookMark b : bookmark) { %>
			<option value="<%=b.getBookmarkName() %>"> <%=b.getBookmarkName() %> </option>
			<% } %>
	</select>
	<input type="submit" value="북마크 추가하기">
	</form>
	<table>
		<tr>
			<th>거리(Km)</th><td><%=wifiList.getDist() %></td>
		</tr>
		<tr>
			<th>관리번호</th><td><%=wifiList.getManageNo() %></td>
		</tr>
		<tr>
			<th>자치구</th><td><%=wifiList.getGugun() %></td>
		</tr>
		<tr>
			<th>와이파이명</th><td><%=wifiList.getWifiName() %></td>
		</tr>
		<tr>
			<th>도로명주소</th><td><%=wifiList.getAddress() %></td>
		</tr>
		<tr>
			<th>상세주소</th><td><%=wifiList.getDetailAddress() %></td>
		</tr>
		<tr>
			<th>설치위치(층)</th><td><%=wifiList.getSetFloor() %></td>
		</tr>
		<tr>
			<th>설치유형</th><td><%=wifiList.getSetType() %></td>
		</tr>
		<tr>
			<th>설치기관</th><td><%=wifiList.getSetOrgan() %></td>
		</tr>
		<tr>
			<th>서비스구분</th><td><%=wifiList.getServiceDivision() %></td>
		</tr>
		<tr>
			<th>망종류</th><td><%=wifiList.getNetworkKind() %></td>
		</tr>
		<tr>
			<th>설치년도</th><td><%=wifiList.getSetYear() %></td>
		</tr>
		<tr>
			<th>실내외구분</th><td><%=wifiList.getInOutdoor() %></td>
		</tr>
		<tr>
			<th>WIFI접속환경</th><td><%=wifiList.getWificonnectEnvirionment() %></td>
		</tr>
		<tr>
			<th>X좌표</th><td><%=wifiList.getX() %></td>
		</tr>
		<tr>
			<th>Y좌표</th><td><%=wifiList.getY() %></td>
		</tr>
		<tr>
			<th>작업일자</th><td><%=wifiList.getWorkDate() %></td>
		</tr>
	</table>
</body>
</html>
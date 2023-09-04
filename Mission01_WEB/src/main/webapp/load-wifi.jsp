<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="api.APIService" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	APIService apiService = new APIService();
		int cnt = apiService.apiCnt();
		System.out.println(cnt);
	%>
	<h1><%=cnt %>개의 데이터를 정상적으로 저장하였습니다.
	</h1>
	<a href="index.jsp">홈 으로 가기</a>
</body>
</html>
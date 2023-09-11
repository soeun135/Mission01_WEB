<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="api.APIService" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body>
<%
	APIService apiService = new APIService();

	double x = Double.parseDouble(request.getParameter("xvalue"));
	double y = Double.parseDouble(request.getParameter("yvalue"));
	
%>

	<a href="index.jsp?xValue=<%= x %>&yValue=<%= y %>">홈</a>
</body>
</html>
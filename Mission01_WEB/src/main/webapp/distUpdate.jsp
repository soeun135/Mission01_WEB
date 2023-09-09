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

	double x = Double.parseDouble(request.getParameter("xvalue"));
	double y = Double.parseDouble(request.getParameter("yvalue"));
	
	apiService.calDist(x, y);
%>

	<a href="index.jsp?xValue=<%= x %>&yValue=<%= y %>">홈</a>
</body>
</html>
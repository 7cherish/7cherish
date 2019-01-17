<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%-- 	
<%
	Date d = new Date();
	String today = String.format("%tY/%tm/%td %tT", d, d, d, d);
	// 2018/12/04 20:00:00
%>
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include테스트</title>
</head>
<body>
	<h2>오늘의 운세</h2>
	<%-- <h4><%=today %></h4> --%>
	<h4><%@ include file="today.jsp"%></h4>
	<select>
		<option>자</option>
		<option>축</option>
		<option>인</option>
		<option>묘</option>
		<option>진</option>
		<option>사</option>
		<option>오</option>
		<option>미</option>
		<option>신</option>
		<option>유</option>
		<option>술</option>
		<option>해</option>
	</select>
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<!-- import는 따로 빼서 작성 -->
<%
	Date now = new Date(); // 현재 시각
	String date = String.format("%tF", now); // yyyy-mm-dd
	String time = String.format("%tp %tT", now, now); // 오전/오후 시각정보
	String today = String.format("%tY년 %tm월 %td일 %tA", now, now, now, now); // 년월일요일 출력
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>import테스트</title>
</head>
<body>
	<h2>현재시각정보</h2>
	<ul>
		<li>오늘 날짜 : <%=date%></li>
		<li>현재 시각 : <%=time%></li>
		<li>오늘은 <%=today%>입니다.</li>
	</ul>

</body>
</html>
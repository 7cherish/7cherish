<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%-- isErrorPage속성을 true로 하면, exception이라는 내장객체접근가능 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오류페이지</title>
<style>
body {
	text-align: center;
}

span {
	color: red;
	font-size: 2em;
}
</style>
</head>
<body>
	<h2>재고관리 오류</h2>
	<p><span><%=exception.getMessage() %></span></p>
	<p>관리자에게 문의하세요.</p>
	<a href="<%=request.getContextPath()%>">메인페이지로</a>
		
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>el : param</title>
</head>
<body>
	<h2>el : param</h2>
	<!-- jsp도 하나의 서블릿이다. -->
	<!-- http://localhost:9090/action/el/elParam.jsp -->
	<form action="elParamEnd.jsp">
		<input type="text" name="pname" placeholder="제품명" />
		<br />
		<input type="number" name="pcount" placeholder="수량" />
		<br />
		<input type="text" name="option" placeholder="옵션1" />
		<br />
		<input type="text" name="option" placeholder="옵션2" />
		<input type="submit" value="제출" />
	</form>
</body>
</html>
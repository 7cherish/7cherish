<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>el : paramEnd</title>
</head>
<body>
	<h2>주문내역확인</h2>
	<p>상품명 : ${param.pname}</p>
	<p>개수 : ${param.pcount}개</p>
	<!-- 옵션이 체크박스처럼 동일한 이름(=>option)으로 여러 개 지정되어있다. -->
	<!-- 복수개는 paramValues를 사용하면 배열처럼 받아온다. -->
	<p>옵션1 : ${paramValues.option[0]}</p>
	<p>옵션2 : ${paramValues.option[1]}</p>

</body>
</html>
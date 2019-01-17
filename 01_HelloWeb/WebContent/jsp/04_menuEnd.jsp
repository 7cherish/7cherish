<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// java코드영역
	// jsp에는 request, response라는 내장객체가 존재한다.
	// return값이 Object이기 때문에 형변환이 필수이다.
	// request객체 속성에 담아둔 변수 추출
	String mainmenu = (String) request.getAttribute("mainmenu");
	String sidemenu = (String) request.getAttribute("sidemenu");
	String drink = (String) request.getAttribute("drink");
	int price = (Integer) request.getAttribute("price");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문결과페이지</title>
<style>
</style>
</head>
<body>
	<h2>주문결과페이지</h2>
	<p>
		감사합니다.
		<%=mainmenu%>,
		<%=sidemenu%>,
		<%=drink%>을/를 주문하셨습니다. 총 결제금액은
		<%=price%>원 입니다.
	</p>
</body>
</html>
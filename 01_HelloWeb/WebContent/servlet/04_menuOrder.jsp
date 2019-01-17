<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// 1. request 인코딩
	request.setCharacterEncoding("utf-8");

	// 2. parameter 핸들링
	String mainmenu = request.getParameter("mainmenu");
	String sidemenu = request.getParameter("sidemenu");
	String drink = request.getParameter("drink");

	// 3. 비지니스 로직 처리
	int price = 0;

	switch (mainmenu) {
	case "한우버거":
		price += 5000;
		break;
	case "밥버거":
		price += 4500;
		break;
	case "치즈버거":
		price += 4000;
		break;
	}

	switch (sidemenu) {
	case "감자튀김":
		price += 1500;
		break;
	case "어니언링":
		price += 1700;
		break;
	}

	switch (drink) {
	case "콜라":
		//	price += 1000;
		//	break;
		//	밑에랑 가격이 같기 때문에 안 적어줘도 가능하다.
	case "사이다":
		price += 1000;
		break;
	case "커피":
		price += 1500;
		break;
	case "밀크쉐이크":
		price += 2500;
		break;
	}
	System.out.println("price = " + price);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문처리 및 결과페이지</title>
</head>
<body>
	<h2>주문처리 및 결과페이지</h2>
	<p>
		감사합니다.
		<%=mainmenu%>,
		<%=sidemenu%>,
		<%=drink%>을/를 주문하셨습니다. 총 결제금액은
		<%=price%>원 입니다.
	</p>

</body>
</html>
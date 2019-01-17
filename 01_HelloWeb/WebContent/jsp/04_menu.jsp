<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 메뉴</title>
<style>
h3 {
	color: lightgray;
}

table {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	border: 1px solid black;
	text-align: center;
	padding: 10px;
}
</style>
</head>
<body>
	<h1>오늘의 메뉴</h1>
	<h3><%@ include file="today.jsp"%></h3>

	<table>
		<tr>
			<th>메뉴</th>
			<th>품목</th>
			<th>가격</th>
		</tr>

		<tr>
			<td rowspan="4">메인메뉴</td>
		</tr>

		<tr>
			<td>한우버거</td>
			<td>5000원</td>
		</tr>

		<tr>
			<td>밥버거</td>
			<td>4500원</td>
		</tr>

		<tr>
			<td>치즈버거</td>
			<td>4000원</td>
		</tr>

		<tr>
			<td rowspan="3">사이드메뉴</td>
		</tr>

		<tr>
			<td>감자튀김</td>
			<td>1500원</td>
		</tr>

		<tr>
			<td>어니언링</td>
			<td>1700원</td>
		</tr>

		<tr>
			<td rowspan="5">음료메뉴</td>
		</tr>

		<tr>
			<td>콜라</td>
			<td>1000원</td>
		</tr>

		<tr>
			<td>사이다</td>
			<td>1000원</td>
		</tr>

		<tr>
			<td>커피</td>
			<td>1500원</td>
		</tr>

		<tr>
			<td>밀크쉐이크</td>
			<td>2500원</td>
		</tr>
	</table>
	<br />
	<br />
		<!-- 
	<form action="/web/menuOrder.do" method="get">
	 -->
	<!-- 컨텍스트패스를 표현식으로 처리 -->
	<!-- <form name="menuFrm" action="/web/menuOrder.do" method="get"> -->
	<!-- 컨텍스트패스를 변수로 처리 -->
<%-- 	
	<form name="menuFrm"
		action="<%=request.getContextPath()%>/menuOrder.do" method="get">
		 --%>
	<form name="menuFrm"
		action="<%=request.getContextPath()%>/jsp/04_menuOrder.jsp" method="get">

		<label>메인메뉴 : </label> <select name="mainmenu" required>
			<option value="" disabled selected>햄버거를 선택하세요.</option>
			<option value="한우버거">한우버거</option>
			<option value="밥버거">한우버거</option>
			<option value="치즈버거">한우버거</option>
		</select> <br /> <br /> <label>사이드메뉴 : </label> <select name="sidemenu"
			required>
			<option value="" disabled selected>사이드메뉴를 선택하세요.</option>
			<option value="감자튀김">감자튀김</option>
			<option value="어니언링">어니언링</option>
		</select> <br /> <br /> <label>음료메뉴 : </label> <select name="drink" required>
			<option value="" disabled selected>음료를 선택하세요.</option>
			<option value="콜라">콜라</option>
			<option value="사이다">사이다</option>
			<option value="커피">커피</option>
			<option value="밀크쉐이크">밀크쉐이크</option>
		</select> <br /> <br /> <input type="submit" value="주문" /> &nbsp; <input
			type="reset" value="취소" />
	</form>

</body>
</html>
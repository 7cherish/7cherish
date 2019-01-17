<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품추가페이지</title>
<style>
div#container{
	border: 1px solid;
	width: 80%;
	margin: 0 auto;
	padding: 50px;
	text-align: center;
}

div#container table{
	border-collapse: collapse;
	width: 355px;
	margin: auto;
}

div#container table td{
	padding: 5px;
}
/* tr 밑에 있는 첫 번째 td */
div#container table tr td:first-of-type{
	text-align: right;
	width: 30%;
}

div#container table tr td:last-of-type{
	text-align: left;
}

/* 마지막 버튼 td */
div#container table tr:last-of-type td{
	text-align: center;
}

a#to-main{
	text-decoration: none;
	float: left;
}
</style>
</head>
<body>
	<div id="container">
		<a href="<%=request.getContextPath() %>" id="to-main">Home</a>
		<br />
		<h2>상품재고관리시스템 - 상품추가</h2>
		<form action="<%=request.getContextPath() %>/jsp/product/productInsert.jsp"
			  method="post">
			  <!-- 데이터가 바뀌면 post -->
			<table>
				<!-- (tr>td+(td>input))*5 -->
				<tr>
					<td>상품아이디 : </td>
					<td><input type="text"
							   name="pid"
							   required />
					</td>
				</tr>
				<tr>
					<td>상품명 : </td>
					<td><input type="text" 
							   name="pname"
							   required />
					</td>
				</tr>
				<tr>
					<td>가격 : </td>
					<td><input type="number"
				               name="price"
				               step="1000"
				               required />
				     </td>
				               <!-- step은 n씩 증가하겠다는 것 -->
				</tr>
				<tr>
					<td>재고 : </td>
					<td><input type="number"
							   name="stock"
							   value="0"
							   required />
					</td>
				</tr>
				<tr>
					<td>상품설명 : </td>
					<td><textarea name="desc"
							      cols=30
							      rows=3 /></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="상품추가" />
						&nbsp;&nbsp;
						<input type="reset" value="초기화" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
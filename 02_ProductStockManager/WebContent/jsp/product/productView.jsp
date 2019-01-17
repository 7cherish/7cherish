<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="/jsp/common/error.jsp"%>
<%@ page
	import="product.model.vo.*,
				 product.model.service.ProductService,
				 product.model.exception.ProductException,
				 java.util.*"%>
<%
	//1. 한글관련 인코딩처리
	request.setCharacterEncoding("utf-8");

	// 2. 전송값 파라미터 핸들링
	String pid = request.getParameter("pid");
	/* String pname = request.getParameter("pname");
	int price = Integer.parseInt(request.getParameter("price"));
	String desc = request.getParameter("desc");
	int stock = Integer.parseInt(request.getParameter("stock")); */
	
	
	// 3. 업무로직처리요청 : service단
	/* Product p = new ProductService().selectOne(pid); */

	Product p = null;
	
	try {
		p = new ProductService().selectOne(pid);
	} catch (ProductException e) {
		e.printStackTrace();
		throw new Exception("상품 상세보기 오류!");
	}
	
	List<Product> list = null;
	List<ProductIO> ioList = null;

	// service단에 업무(비지니스)로직 요청
	try {
		list = new ProductService().selectProductList();
		System.out.println("list@index.jsp = " + list);

		ioList = new ProductService().selectProductIOList();
		System.out.println("ioList@index.jsp = " + ioList);
	} catch (ProductException e) {
		e.printStackTrace(); // 로깅용 예외출력
		throw new Exception("재고관리시스템 index페이지 오류");
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품상세보기페이지</title>
<style>
div#container {
	border: 1px solid;
	width: 80%;
	margin: 0 auto;
	padding: 50px;
	text-align: center;
}

div#container table {
	border-collapse: collapse;
	width: 355px;
	margin: auto;
}

div#container table td {
	padding: 5px;
}

/* div#container table#io {
	border: 1px solid black;
	border-collapse: collapse;
	width: 50%;
} */

div#container table#io th, table#io td {
	border: 1px solid black;
	padding: 5px;
}
/* tr 밑에 있는 첫 번째 td */
div#container table tr td:first-of-type {
	text-align: right;
	width: 30%;
}

div#container table tr td:last-of-type {
	text-align: left;
}

/* 마지막 버튼 td */
div#container table tr:last-of-type td {
	text-align: center;
}

a#to-main {
	text-decoration: none;
	float: left;
}

/* 수정불가한 항목 스타일추가 */
input[readonly] {
	background: lightgray;
}

/* 상품입출고테이블 */
div#container table#io {
	border:1px solid black;
	border-collapse:collapse;
	width:80%;
	margin:0 auto 15px;
}

div#container table#io th, table#io td{
	border:1px solid black;
	padding:5px;
}
</style>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<script>
/* 
onload에 안 넣어서 안 됨
$("#btn-del").on("click", function(){
	console.log("#btn-del clicked!!!");
});
*/

$(function() {
	$("#btn-del").on("click", function() {
		/* console.log("#btn-del clicked!!!"); */
		if(!confirm("정말로 삭제하겠습니까?"))
			return;
		
		// 삭제요청 전달
		// 1. 페이지 이동 형식
		<%-- location.href = "<%=request.getContextPath() %>/jsp/product/productDelete.jsp?pid=<%=pid %>"; --%>
		
		// 2. 폼전송방식
		$("#delFrm").submit();
		
	});
});


</script>
</head>
<body>
	<form
		action="<%=request.getContextPath() %>/jsp/product/productDelete.jsp"
		id="delFrm" method="post">
		<input type="hidden" name="pid" value="<%=pid %>" />
	</form>
	<div id="container">
		<a href="<%=request.getContextPath() %>" id="to-main">Home</a> <br />
		<h2>상품재고관리시스템 - 상세보기</h2>
		<form
			action="<%=request.getContextPath() %>/jsp/product/productUpdate.jsp"
			method="post">
			<!-- 데이터가 바뀌면 post -->
			<table>
				<!-- (tr>td+(td>input))*5 -->
				<tr>
					<td>상품아이디 :</td>
					<td><input type="text" name="pid" value="<%=pid %>" readonly
						required /></td>
				</tr>
				<tr>
					<td>상품명 :</td>
					<td><input type="text" name="pname" value="<%=p.getpName() %>"
						required /></td>
				</tr>
				<tr>
					<td>가격 :</td>
					<td><input type="number" name="price" step="1000"
						value="<%=p.getPrice() %>" required /></td>
					<!-- step은 n씩 증가하겠다는 것 -->
				</tr>
				<tr>
					<td>재고 :</td>
					<td><input type="number" name="stock"
						value="<%=p.getStock() %>" required /></td>
				</tr>
				<tr>
					<td>상품설명 :</td>
					<td><textarea name="desc" cols=30 rows=3>
						 <%=p.getDescription() %>	      
						</textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="상품수정" />
						&nbsp;&nbsp; <input type="button" id="btn-del" value="상품삭제" /></td>
				</tr>
			</table>
		</form>


		<hr style="clear: right;" />

		<h3>상품입출고현황</h3>
		<table id="io">
			<thead>
				<tr>
					<th>입출고번호</th>
					<th>상품아이디</th>
					<th>날짜</th>
					<th>수량</th>
					<th>입/출고</th>
				</tr>
			</thead>
			<tbody>
				<!-- 리스트에서 불러온 것을 한 행마다 실행해줄 것 -->
				<%
	    if(!ioList.isEmpty()){
	    /* 리스트가 비어있지 않은 경우 */
	    	for(int i = 0; i < ioList.size(); i++){
	    		ProductIO pio = ioList.get(i);
	    %>
				<tr pid="<%=pio.getProductId() %>">
					<td><%=pio.getIoNo() %></td>
					<td><%=pio.getProductId() %></td>
					<td><%=pio.getpDate() %></td>
					<td><%=pio.getAmount() %></td>
					<td><%="I".equals(pio.getStatus())?"입고":"출고" %></td>
					<%-- <td><% out.println("I".equals(pio.getStatus())?"입고":"출고"); --%>
				</tr>
				<%
	    	}
	    }
	    else{
	    /* 데이터가 없을 경우 대비 유효성 검사 */
	    %>
				<tr>
					<td colspan="4">데이터가 없습니다.</td>
				</tr>
				<%
	    }
		%>
			</tbody>
		</table>
		<form
			action="<%= request.getContextPath() %>/jsp/product/productIOInsert.jsp"
			method="post">
			<input type="radio" name="status" id="input" value="I" checked />
			<label for="input">입고</label> 
			<input type="radio" name="status" id="output" value="O" />
			<label for="output">출고</label>
			&nbsp;&nbsp; 
			<input type="number" name="amount" id="amount" min="0" placeholder="수량을 입력하세요." required /> 
			<input type="hidden" name="pid" value="<%=p.getProductId()%>" />

			<!-- type:submit태그에서 유효성검사코드 onsubmit이 아닌 onclick작성할 것. form태그에서 onsubmit속성으로 작성. -->
			<!-- <input type="submit" onsumbit="return fn_pio_validate();" value="전송" /> -->
			<input type="submit" onclick="return fn_pio_validate();" value="전송" />
		</form>
	</div>
</body>
</html>
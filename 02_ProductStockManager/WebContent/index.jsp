<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/jsp/common/error.jsp"%>
<%@ page import="product.model.service.ProductService,
				 product.model.vo.*,
				 product.model.exception.ProductException,
				 java.util.*" %>
<%
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
<title>상품 재고 관리</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.3.1.js"></script>
<script>
$(function(){
	// 제품추가버튼 이벤트핸들러
	$("#btn-add").on("click", function(){
		location.href = "<%=request.getContextPath()%>/jsp/product/productForm.jsp";
	});
	
	// 테이블행 클릭시, 해당 상품의 상세보기 페이지로 이동
	$("table tr").on("click", function(e){
		var target = e.target.tagName;
		/*
		console.log("target = ", target);
		*/
		
		/* 여기서 this는 tr */
		var pid = $(this).attr("pid");
		/*
		console.log("pid = ", pid);
		*/
		
		// 데이터 없는 경우 (헤더를 클릭한 경우) return
		if(pid == undefined) return;
		
		// 상세보기 페이지로 이동
		location.href="<%=request.getContextPath() %>/jsp/product/productView.jsp?pid=" + pid;
		
	});
});

</script>
<style>
div#container {
	/* border:1px solid black; */
	width: 80%;
	margin: auto;
	padding: 50px;
	text-align: center;
}

section {
	float: left;
}

section#tbl-container {
	/* border:1px solid black; */
	width: 50%;
	text-align: center;
	padding: 0 50px 50px
}

section#tbl-container table {
	border: 1px solid black;
	border-collapse: collapse;
	width: 100%;
}

section#tbl-container table th, section#tbl-container table td {
	border: 1px solid black;
	padding: 5px;
}

section#srch-container {
	/* border:1px solid black; */
	width: 25%;
	height: 300px;
	text-align: left;
	padding: 30px 30px 100px 30px;
}

button#btn-add {
	float: right;
	margin: 10px 0;
}

/* 마우스오버시 컬러변경효과 */
section#tbl-container table tr:hover td {
	background: lightgray;
	color: white;
	cursor: pointer;
}
</style>
</head>

<body>
<div id="container">
	<h2>상품재고관리 시스템</h2>
	<section id="tbl-container">
	<h3>상품현황</h3>
	<table id="product">
		<thead>
			<tr>
				<th>상품아이디</th>
				<th>상품명</th>
				<th>가격</th>
				<th>재고량</th>
			</tr>
		</thead>
		<tbody>
		<!-- 리스트에서 불러온 것을 한 행마다 실행해줄 것 -->
		<%
	    if(!list.isEmpty()){
	    /* 리스트가 비어있지 않은 경우 */
	    	for(int i = 0; i < list.size(); i++){
	    		Product p = list.get(i);
	    %>
	    	<tr pid="<%=p.getProductId() %>">
	    		<td><%=p.getProductId() %></td>
	    		<td><%=p.getpName() %></td>
	    		<td><%=p.getPrice() %></td>
	    		<td><%=p.getStock() %></td>
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
	
	<button id="btn-add">상품추가</button>
	
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

	
	</section>
	<section id="srch-container">
		<form action="<%=request.getContextPath() %>/jsp/product/productFinder.jsp" 
			  name="srchFrm">
			<input type="radio" 
				   name="srchType" 
				   id="pid" 
				   value="pid" 
				   checked/>
				   <label for="pid">상품아이디</label>
			<input type="radio" 
				   name="srchType" 
				   id="pname" 
				   value="pname" />
				   <label for="pname">상품명</label> 
			<br /><br />
			<input type="search" 
				   name="srchKeyword" 
				   id="srchKeyword" 
				   size=25
				   required
				   placeholder="검색할 아이디를 입력하세요."/>
			<br /><br />
			<input type="submit" onclick="return srchValidate();" value="검색" />
		</form>
	</section>
</div>
<script>
$("[name=srchType]").change(function(){
		var id = $(this).attr("id");
		if(id === 'pid'){
			$("#srchKeyword").attr("placeholder", "검색할 상품명을 입력하세요.");
		}
		else{
			$("#srchKeyword").attr("placeholder", "검색할 상품아이디를 입력하세요.");			
		}
});
// 검색어 유효성 검사
function srchValidate(){
	var len = $("#srchKeyword").val().trim().length;
	if(len == 0){
		alert("검색어를 입력하세요.");
		return false;
	}
	return true;
}
</script>

</body>
</html>
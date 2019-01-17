<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/jsp/common/error.jsp"%>
<%@ page import="product.model.service.ProductService,
				 product.model.vo.*,
				 product.model.exception.ProductException,
				 java.util.*" %>
<%
	// 1. 인코딩(get방식이므로 생략가능)
	
	// 2. 파라미터핸들링
	String srchType = request.getParameter("srchType");
	String srchKeyword = request.getParameter("srchKeyword");
	System.out.printf("[%s = %s]\n", srchType, srchKeyword);
	
	// 3. 업무로직 => 서비스단
	// list에다가 결과값 담은 다음에 담아만 두면 밑에서 사용할 수 있을테니까..
	// 상품아이디와 상품명 검색을 따로 처리해야한다.
	List<Product> list = null;
	try {
		if("pname".equals(srchType)){
			list = new ProductService().selectByPName(srchKeyword);
		}
		else{
			// @실습문제
			list = new ProductService().selectByPId(srchKeyword);			
		}
	} catch (ProductException e) {
		throw new Exception("상품 검색 오류!");
	}
	
	// 4. view단 처리 (같은 페이지에서 결과를 보여주므로 생략)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 재고 관리</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.3.1.js"></script>
<script>

$(function(){	
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

/* 마우스오버시 컬러변경효과 */
section#tbl-container table tr:hover td {
	background: lightgray;
	color: white;
	cursor: pointer;
}

#to-main{
	text-decoration: none;
	float: left;
}
</style>
</head>

<body>
<div id="container">
	<!-- 인덱스 페이지로 돌아가는 a 태그 -->
	<a href="<%=request.getContextPath() %>" id='to-main'></a>
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

	</section>
	<section id="srch-container">
		<form action="<%=request.getContextPath() %>/jsp/product/productFinder.jsp" 
			  name="srchFrm">
			<input type="radio" 
				   name="srchType" 
				   id="pid" 
				   value="pid" 
				   <%="pid".equals(srchType)?"checked":"" %>/>
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
				   placeholder="검색할 아이디를 입력하세요."
				   value="<%=srchKeyword  %>"/>
			<br /><br />
			<input type="submit" onclick="return srchValidate();" value="검색" />
		</form>
	</section>
</div>
<script>
$("[name=srchType]").change(function(){
		var id = $(this).attr("id");
		if(id === 'pid'){
			$("#srchKeyword").attr("placeholder", "검색할 상품아이디을 입력하세요.");
		}
		else{
			$("#srchKeyword").attr("placeholder", "검색할 상품명을 입력하세요.");			
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
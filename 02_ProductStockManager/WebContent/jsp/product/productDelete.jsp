<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="/jsp/common/error.jsp"%>
<%@ page import="product.model.vo.*,
				 product.model.service.*,
				 product.model.exception.*" %>
<%
	//1. 인코딩 처리
	request.setCharacterEncoding("utf-8");
	
	// 2. 파라미터 핸들링 (사용자가 보낸 데이터를 실제 자바 변수에 담아야 한다)
	String pid = request.getParameter("pid");
	
	System.out.printf("[%s\n]", pid);		
	
	// 3. 업무로직 요청 : service단
	int result = 0;
	try {
		result = new ProductService().deleteProduct(pid);
	} catch (ProductException e) {
		e.printStackTrace();
		throw new Exception("상품 삭제 오류!");
	}

	// 4. view단 처리 : DML이므로 반드시 페이지 이동처리
	// 성공시에는 index페이지로, 실패시에는 상세보기페이지로 이동
	String msg = "";
	String loc = "/";
	if(result > 0){
		msg = "상품 삭제 성공!";
		loc = "/";
	}
	else{
		msg = "상품 삭제 실패!";
		loc= "/jsp/product/productView.jsp?pid=" + pid;
	}
	
	// msg.jsp에 포워딩
	request.setAttribute("msg", msg);
	request.setAttribute("loc", loc);
	request.getRequestDispatcher("/jsp/common/msg.jsp")
		   .forward(request, response);
%>
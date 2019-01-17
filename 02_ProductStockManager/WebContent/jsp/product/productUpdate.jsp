<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="/jsp/common/error.jsp"%>
<%@ page import="product.model.vo.*,
				 product.model.service.*,
				 product.model.exception.*" %>
<%
	// 1. 인코딩 처리
	request.setCharacterEncoding("utf-8");
	
	// 2. 파라미터 핸들링 (사용자가 보낸 데이터를 실제 자바 변수에 담아야 한다)
	String pid = request.getParameter("pid");
	String pname = request.getParameter("pname");
	int price = Integer.parseInt(request.getParameter("price"));
	int stock = Integer.parseInt(request.getParameter("stock"));
	String desc = request.getParameter("desc");
	
	System.out.printf("[%s %s %s %s %s\n]",
				pid,
				pname,
				price,
				stock,
				desc);		
	
	// 3. 업무로직 요청 : service단
	Product p = new Product(pid, pname, price, desc, stock);
	int result = 0;
	try {
		result = new ProductService().updateProduct(p);
	} catch (ProductException e) {
		e.printStackTrace();
		throw new Exception("상품 수정 오류!");
	}

	// 4. view단 처리 : 처리결과에 따라 분기한다
	String msg = "";
	String loc = "/";
	if(result > 0){
		msg = "상품 수정 성공!";
		loc= "/jsp/product/productView.jsp?pid=" + pid;
	}
	else{
		msg = "상품 수정 실패!";
	}
	
	// msg.jsp에 포워딩
	request.setAttribute("msg", msg);
	request.setAttribute("loc", loc);
	request.getRequestDispatcher("/jsp/common/msg.jsp")
		   .forward(request, response);
%>
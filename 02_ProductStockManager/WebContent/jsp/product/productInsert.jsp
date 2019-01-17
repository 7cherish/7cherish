<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="/jsp/common/error.jsp" %>
<%@ page import="product.model.vo.Product,
				 product.model.service.ProductService,
				 product.model.exception.ProductException" %>
<%
	// 상품등록처리페이지
	// 1. 한글관련 인코딩처리
	request.setCharacterEncoding("utf-8");

	// 2. 전송값 파라미터 핸들링
	String pid = request.getParameter("pid");
	String pname = request.getParameter("pname");
	int price = Integer.parseInt(request.getParameter("price"));
	int stock = Integer.parseInt(request.getParameter("stock"));
	String desc = request.getParameter("desc");
	
	System.out.printf("[%s %s %s %s %s]\n",
				pid, 
				pname, 
				price, 
				stock, 
				desc);
	
	// 3. 업무로직처리요청 : service단
	Product p = new Product(pid, 
					pname, 
					price, 
					desc, 
					stock);
	String msg = "";
	// 서비스단에 업무요청
	// DML은 숫자를 결과로 돌려줌. 0이거나 0 이상이거나
	try {
		int result = new ProductService().insertProduct(p);
		String loc = "/"; // 루트 디렉토리
		
		if (result > 0) {
			msg = "상품 등록 성공!";
		} else {
			msg = "상품 등록 실패!";
		}
		
	// 4. 결과에 따른 view단처리
		//msg.jsp사용하기
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		RequestDispatcher reqDispatcher
			= request.getRequestDispatcher("/jsp/common/msg.jsp");
		reqDispatcher.forward(request, response);
		
	} catch (ProductException e) {
		e.printStackTrace();
		throw new Exception("상품 추가 오류!");
	}

%>

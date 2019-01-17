<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/jsp/common/error.jsp"%>
<%@ page import="product.model.vo.*,
				 product.model.service.*,
				 product.model.exception.*" %>
<%
	String pid = request.getParameter("pid");
	String status = request.getParameter("status");
	int amount = Integer.parseInt(request.getParameter("amount"));
	
	ProductIO pio = new ProductIO();
	pio.setProductId(pid);
	pio.setStatus(status);
	pio.setAmount(amount);
	//System.out.println("pio@productIOInsert.jsp="+pio);
	
	//사용자피드백을 위한 status변수
	status = "I".equals(pio.getStatus())?"상품입고":"상품출고";
	
	try {
		//(심화)서버쪽 유효성 검사
		//해당상품이 없거나, 수량이 0보다 같거나 작거나, 출고시, 재고량보다 출고량이 많은 경우 대비 유효성 검사실시
		Product p = new ProductService().selectOne(pio.getProductId());
		if(p==null || pio.getAmount()<=0 
			|| ("O".equals(pio.getStatus())&&(p.getStock()<pio.getAmount()))) 
			throw new ProductException("상품입출고  부정입력 오류!!! : "+pio);
		
		int result = new ProductService().insertProduct_IO(pio);
		if(result>0){
			request.setAttribute("msg", status+"성공!");
			request.setAttribute("loc", "/jsp/product/productView.jsp?pid="+pid);			
			RequestDispatcher reqDispatcher  = request.getRequestDispatcher("/jsp/common/msg.jsp");
			reqDispatcher.forward(request, response);
		}
	} catch (ProductException e) {
		System.err.println(e.getMessage());
		throw new Exception(status+"오류!!!");
	}

%>
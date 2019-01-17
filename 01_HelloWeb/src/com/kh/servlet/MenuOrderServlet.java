package com.kh.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MenuOrderServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
//			price += 1000;
//			break;
//			밑에랑 가격이 같기 때문에 안 적어줘도 가능하다.
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
		
		// jsp에게 view단 처리 위임
		// HttpServletRequest객체에 각 변수를 속성으로 저장한 후에 주소값을 넘김
		request.setAttribute("mainmenu", mainmenu);
		request.setAttribute("sidemenu", sidemenu);
		request.setAttribute("drink", drink);
		request.setAttribute("price", price);
		
		// jsp에게 전달 (jsp forwarding)
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/jsp/04_menuEnd.jsp");
		
		// ServletException처리는 WAS에게 던짐
		reqDispatcher.forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

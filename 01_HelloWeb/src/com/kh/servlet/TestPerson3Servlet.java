package com.kh.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestPerson3Servlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 1. request 인코딩
		request.setCharacterEncoding("utf8");

		// 2. parameter 핸들링
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String animal = request.getParameter("animal");

		String[] foodArr = request.getParameterValues("food");
		String food = "";
		for (int i = 0; i < foodArr.length; i++) {
			food += foodArr[i] + " ";
		}
		System.out.println("name = " + name);
		System.out.println("color = " + color);
		System.out.println("animal = " + animal);
		System.out.println("food = " + food);

		// 3. 비지니스로직 처리
		String recommendation = "";
		switch (color) {
		case "빨강":
			recommendation = "빨간 페라리";
			break;
		case "노랑":
			recommendation = "노란 우산";
			break;
		case "초록":
			recommendation = "초록 대나무";
			break;
		case "파랑":
			recommendation = "파란 캔디바";
			break;
		}

		// 4. view단 처리
		// response.setContentType("text/html; charset=utf-8");
		// PrintWriter out = response.getWriter();
		// out.println("<html>");
		// out.println("<head>");
		// out.println("<title>개인취향검사 - 추천</title>");
		// out.println("</head>");
		// out.println("<body>");
		// out.println("<h2>개인취향검사 - 추천</h2>");
		// out.printf("<p>오늘 %s 어떠세요?</p>", recommendation);
		// out.println("</body>");
		// out.println("</html>");

		// jsp에게 view단 처리위임
		// HttpServletRequest객체에 각 변수를 속성으로 저장한 후에 주소값을 넘김
		// key:value 형식으로 저장된다고 보면 된다.
		request.setAttribute("name", name);
		request.setAttribute("color", color);
		request.setAttribute("animal", animal);
		request.setAttribute("food", food);
		request.setAttribute("recommendation", recommendation);

		// jsp에 전달
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/servlet/testPersonEnd.jsp");
		// ServletException처리는 WAS에게 던짐.
		reqDispatcher.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}

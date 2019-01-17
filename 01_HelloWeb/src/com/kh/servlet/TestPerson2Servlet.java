package com.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestPerson2Servlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// post방식은 get방식과 조금 다르다.
		// 1. 인코딩처리
		request.setCharacterEncoding("utf8");

		// 2. 파라미터핸들링 (자바변수를 담는 것)
		// 사용자가 무엇을 입력하든(숫자, 문자 등등) request를 통해 꺼내면 다 String이다.
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String animal = request.getParameter("animal");
		// 체크박스 등 복수 개의 값이 전달되는 경우 배열에 담는다.
		// getParameterValues() 메소드 사용
		String[] foodArr = request.getParameterValues("food");
		String food = "";
		for (int i = 0; i < foodArr.length; i++) {
			food += foodArr[i] + " ";
		}
		System.out.printf("%s %s %s %s", name, color, animal, food);
		
		// 3. 응답 출력
		response.setContentType("text/html; charset=utf-8");
		// 예외는 WAS에서 처리하므로 던짐.
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>개인취향검사(POST)</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>개인취향검사(POST)</h2>");
		out.printf("<p>%s %s %s %s</p>", name, color, animal, food);
		out.println("</body>");
		out.println("</html>");
		
		

	}
}

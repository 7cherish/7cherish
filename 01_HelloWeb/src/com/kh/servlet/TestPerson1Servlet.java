package com.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet이란? 웹서비스를 위한 자바클래스이다. 
 * javax.servlet.Servlet 인터페이스
 * javax.servlet.GenericServlet 추상클래스 
 * javax.servlet.http.HttpServlet 추상클래스
 */

public class TestPerson1Servlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// 1. 폼데이터 변수처리
		String name = request.getParameter("name"); // 01_testPerson.html파일에서 input name = "name"인걸 가져온다.
		String color = request.getParameter("color"); // 01_testPerson.html파일에서 input name = "color"인걸 가져온다.
		String animal = request.getParameter("animal"); // 01_testPerson.html파일에서 input name = "animal"인걸 가져온다.

		// 복수 개 이상의 데이터 처리 : getParameterValues, 배열에 담아준다.
		String[] foodArr = request.getParameterValues("food");

		// 확인
		System.out.println("name = " + name);
		System.out.println("color = " + color);
		System.out.println("animal = " + animal);
		// 배열은 반복문을 통해 출력
		String food = "";
		for (int i = 0; i < foodArr.length; i++) {
			System.out.println("foodArr[" + i + "] = " + foodArr[i]);
			food += foodArr[i] + " ";
		}

		// 2. 응답 준비
		// response핸들링
		response.setContentType("text/html; charset=utf-8");
		// 문자기반출력스트림 객체
		// 예외는 WAS에서 처리하므로 던짐.
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>개인취향검사(GET)</title>");
		out.println("<style>");
		out.println("h2{color:red;}");
		out.println("span.name{color:lightblue; font-weight:bold}");
		out.println("span.color{color:lightgreen; font-weight:bold}");
		out.println("span.animal{color:lightsalmon; font-weight:bold}");
		out.println("span.food{color:lightcoral; font-weight:bold}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>개인취향검사(GET)</h2>");
		out.printf("<span class='name'>%s</span>님의 개인취향은 ", name);
		out.printf("<span class='color'>%s</span>색을 좋아하고 ", color);
		out.printf("<span class='animal'>%s</span>을 좋아합니다. ", animal);
		out.printf("좋아하는 음식은 <span class='food'>%s</span>입니다.", food);
		out.println("</body>");
		out.println("</html>");

	}

}

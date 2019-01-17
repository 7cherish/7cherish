package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.exception.MemberException;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberViewServlet
 */
@WebServlet(name = "MemberViewServlet", urlPatterns = "/member/memberView")
// 여러 개 쓸때는 속성?을 다 적어줘야함
public class MemberViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // <- 이 예외들은 Tomcat WAS에서 처리해준다.
		try {
			// 1. encoding
			request.setCharacterEncoding("utf-8");

			// 2. parameterHandling
			String memberId = request.getParameter("memberId");

			// 3. businessLogic
			// 멤버 아이디 값을 가지고 회원정보 한 명의 것을 가져올 수 있다.
			Member m = new MemberService().selectOne(memberId);
			System.out.println("member@MemberViewServlet = " + m);

			// 4. view
			// 비정상적인 요청으로 해당회원정보가 없을 경우를 대비
			String view = "/WEB-INF/views/member/memberView.jsp";
			String msg = "";
			String loc = "/";

			if (m == null) {
				view = "/WEB-INF/views/common/msg.jsp";
				msg = "해당 회원이 존재하지 않습니다.";
			}

			// request객체에 속성 등록
			request.setAttribute("member", m); // 회원정보
			request.setAttribute("msg", msg); // 실패했을 경우만 사용
			request.setAttribute("loc", loc); // 실패했을 경우만 사용

			RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
			reqDispatcher.forward(request, response);
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException("회원조회오류!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

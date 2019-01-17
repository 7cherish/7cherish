package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdatePasswordEndServlet
 */
@WebServlet("/member/updatePasswordEnd")
public class MemberUpdatePasswordEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. parameterHandling
		String memberId = (String) request.getParameter("memberId");
		String password = (String) request.getParameter("password");
		String password_new = (String) request.getParameter("password_new");

		// 2. businessLogic
		// 기존 비밀번호 확인
		// logincheck : memberId, password
		// 1(로그인 한 상태), 0(아이디는 있지만 비밀번호가 틀린 상태)
		Member m = new Member();
		m.setMemberId(memberId);
		m.setPassword(password);
		System.out.println();

		int result = new MemberService().loginCheck(m);
		String msg = "";
		String loc = "";
		
		System.out.println("기존비밀번호체크 : " + result);

		if (result == MemberService.LOGIN_OK) {
			// 새 비밀번호 변경
			m.setPassword(password_new); // 변경할 비밀번호로 Member객체 갱신
			result = new MemberService().updatePassword(m);
			
			if(result > 0) {
				// 비밀번호 변경 성공
				msg = "비밀번호가 변경되었습니다.";
				// 팝업창을 닫기 위한 코드
				String script = "self.close();";
				// jsp에 전달하기 위해 속성으로 전달
				request.setAttribute("script", script);
			}
			else {
				// 비밀번호 변경 실패
				msg = "비밀번호 변경에 실패했습니다.";
				loc = "/member/updatePassword?memberId=" + memberId;
			}
			
		} else {
			msg = "기존 비밀번호를 잘못 입력하셨습니다.";
			loc = "/member/updatePassword?memberId=" + memberId;
		}

		// 3. view
		String view = "/WEB-INF/views/common/msg.jsp";

		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);

		RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
		reqDispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

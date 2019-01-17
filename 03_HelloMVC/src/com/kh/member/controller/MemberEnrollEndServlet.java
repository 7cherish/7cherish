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
 * Servlet implementation class MemberEnrollEndServlet
 */
@WebServlet("/member/memberEnrollEnd")
// 회원가입 다 하고 나서 DB에 넘겨주는 용도
// MemerEnrollEndServlet은 memberEnroll.jsp에서 회원가입버튼 눌렀을 때 실제로 DB에 insert되는 로직 처리하는 서블릿
public class MemberEnrollEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		
		//2. 파라미터 핸들링
		String memberId = (String)request.getParameter("memberId");
		String password = (String)request.getParameter("password");
		String memberName = (String)request.getParameter("memberName");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = (String)request.getParameter("email");
		String phone = (String)request.getParameter("phone");
		String address = (String)request.getParameter("address");
		String gender = (String)request.getParameter("gender");
		String[] hobbyArr = request.getParameterValues("hobby");
		String hobby = "";
		if(hobbyArr != null) {
			hobby = String.join(",", hobbyArr);			
		}
		
		Member m = new Member(memberId, password, memberName, gender, age, email, phone, address, hobby, null);
		
		int result = new MemberService().insertMember(m);
		
		String msg = "";
		String loc = "/";
		String view = "/WEB-INF/views/common/msg.jsp";
		
		if(result>0){
			msg = "회원가입 완료!";
		}
		else{
			msg = "회원가입 실패!";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
		reqDispatcher.forward(request,  response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
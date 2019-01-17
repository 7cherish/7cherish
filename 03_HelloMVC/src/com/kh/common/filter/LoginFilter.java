package com.kh.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(servletNames = { "MemberViewServlet"}, urlPatterns = { "/board/boardForm" })
// 복수 개도 가능하다 => 배열 선언이기 때문
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("[LoginFilter!!]");
		// @실습문제 : 로그인하지 않거나, 로그인한 본인이 아닌 회원정보를 요청할시에
		// "잘못된 경로로 접근하셨습니다." 경고창을 띄우고,
		// index페이지로 이동하세요.
		
		// 1. 로그인하지 않고 사용자정보조회
		// => session객체의 memberLoggedIn의 속성값 확인 (null이면 로그인 안 한 것)
		// 2. 본인이 아닌 사용자 정보조회
		// => session객체의 memberLoggedIn.getMemberId()값과 요청파라미터의 memberId값을 비교 (다르면 자기 것이 아님)
		
//		HttpSession session = reqeust.getSession(); => 부모 타입에서는 이 리퀘스트를 사용할 수 없다.
		HttpSession session = ((HttpServletRequest)request).getSession();
		// 그러므로 형변환을 해줘야 한다. (.)우선순위때문에 괄호 잘해줘야 함 
		
		// 로그인한 사용자
		Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
		// Attribute로 가져오는 것은 오브젝트객체이기 때문에 형변환 해줘야 한다.
		
		// 요청 memberId
		// 현재 조회하고자 하는 사용자 아이디
		String reqMemberId = request.getParameter("memberId");
		// 정상적인 요청 : localhost:9090/mvc/member/memberView?memberId=abcde
		// 비정상적인 요청 : localhost:9090/mvc/member/memberView => reqMemberId가 null일 수 있다.
		
		// 사용자 검사
		if(memberLoggedIn == null || 
			reqMemberId == null || 
			!(reqMemberId.equals(memberLoggedIn.getMemberId()) || 
			  "admin".equals(memberLoggedIn.getMemberId()))) {
			// 부정요청시 처리 (1. 로그인하지 않은 경우, 2. 본인이 아닌 사용자 정보조회하는 경우)
			request.setAttribute("msg", "잘못된 경로로 접근하셨습니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
					.forward(request, response);
			// 더이상 servlet 또는 filterChain으로 진행시키지 않기 위해서 리턴
			return;
		}
		
//		HttpServletRequest httpReq = (HttpServletRequest) request;
//		// 1. encoding => EncodeFilter에서 처리하였으므로 생략
//
//		// 2. parameterHandling
//		String memberId = httpReq.getParameter("memberId");
//		System.out.println("memberId = " + memberId);
//
//		// 3. businessLogic
//		// 현재 세션 가져오는 법
//		HttpSession s = httpReq.getSession();
//		Member m = (Member) s.getAttribute("memberLoggedIn"); // memberLoggedInServlet참고
//		
//		// 4. view
//		String msg = "";
//		String loc = "/";
//		String view = "/WEB-INF/views/common/msg.jsp";
//		if (m == null || !m.getMemberId().equals(memberId)) {
//			msg = "잘못된 경로로 접근하셨습니다";
//			httpReq.setAttribute("msg", msg);
//			httpReq.setAttribute("loc", loc);
//			RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
//			reqDispatcher.forward(httpReq, response);
//			return;
//		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

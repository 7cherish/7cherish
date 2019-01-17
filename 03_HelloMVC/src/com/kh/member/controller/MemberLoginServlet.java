package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.exception.MemberException;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/login")
// url맵핑을 전달받고 있음(?)
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		// 1. 인코딩
		request.setCharacterEncoding("utf-8");
		
		// 2. 파라미터 핸들링
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		System.out.printf("[%s, %s]\n", memberId, password);
		
		// return 1 : 로그인성공
		// return 0 : 패스워드 틀림
		// return -1 : 존재하지 않는 아이디
		Member m = new Member();
		m.setMemberId(memberId);
		m.setPassword(password);
		
		
		// 3. 업무로직요청
		int result = new MemberService().loginCheck(m);
		
		System.out.println("[로그인 결과 : " + result + "]");
		
		// 4. view단처리
		String view = "";

		// 로그인 실패한 경우 사용
		String msg = "";
		String loc = "/";
		
		
		// 0. 로그인후 이전페이지로 리다이렉트하기
		String referer = request.getHeader("Referer");
		String origin = request.getHeader("Origin");
		String url = request.getRequestURL().toString();
		String uri = request.getRequestURI();
		// Uniform Resource Locator/Indicator
		// uri > url
		// url : 요청한 파일 위치를 알려주는거
		// uri : 파일의 고유 식별자
		// uri = url + urn
		
		System.out.println("referer=" + referer);
		// referer=http://localhost:9090/mvc/board/boardView?boardNo=35
		System.out.println("origin=" + origin);
		// origin=http://localhost:9090
		System.out.println("url=" + url);
		// url=http://localhost:9090/mvc/member/login
		System.out.println("uri=" + uri);
	    // uri=/mvc/member/login
		
		// 크롬외 브라우저용
		if(origin == null) {
			origin = url.replace(uri, "");
		}
		
		// 1. 로그인 성공한 경우
		
		/*
		@실습문제 : 로그인에 성공한 경우, 
		로그인한 회원정보를 db로부터 가져와서
		request객체의 memberLoggedIn 속성에 저장하세요.
		service단 메소드명 : selectOne
		
		null인 경우, 로그인창(?) 나오게
		null이 아닌 경우(로그인 성공), ~님 환영합니다. 마이페이지, 로그아웃 버튼 나오게 하기
		*/
		
		if(result == MemberService.LOGIN_OK) {
//			view = "/index.jsp"; // view단으로 index.jsp를 쓰겠다. 리다이렉트해서 이제 필요없음
			
			// 쿠키 관련
			String saveId = request.getParameter("saveId");
			// 체크된 경우 : on , 체크되지 않은 경우 : null
			System.out.println("saveId = " + saveId);
			
			// 쿠키생성 : Server단, 보관 : client단에서 하게 된다.
			if(saveId != null) {
				// 체크한 경우
				// 이름은 Key Value형식으로 하면 된다.
				// 쿠키는 Key:Value형식으로 저장된다.
				Cookie c = new Cookie("saveId", memberId);
				c.setMaxAge(7 * 24 * 60 * 60); // 쿠키유효기간 : 7일, 단위 : 초
				// 어디서 쿠키 사용할지 지정
				c.setPath("/mvc"); // 쿠키유효디렉토리 설정
				// response객체에 쿠키 세팅
				response.addCookie(c);
			}
			else {
				// 체크하지 않은 경우
				// 현재 등록된 쿠키삭제 목적
				Cookie c = new Cookie("saveId", memberId);
				c.setMaxAge(0); // 쿠키유효기간을 0으로 설정해서 삭제
				c.setPath("/mvc"); // 삭제하고자 하는 쿠키와 동일하게 할 것
				response.addCookie(c);
			}
			
			
			Member memberLoggedIn = new MemberService().selectOne(memberId);
//			// request객체에 속성으로 저장
//			request.setAttribute("memberLoggedIn", memberLoggedIn);
			
			// request객체로부터 세션객체 가져오기
			// getSession(true) :  기본값. 
			// 세션이 있으면, 해당세션을 반납, 없으면 새로 생성후 반납
			// getSession(false)
			// 세션이 있으면, 해당세션을 반납, 없으면 null을 반납
			HttpSession session = request.getSession(true);
			
			// timeout설정 : web.xml보다 우선 순위 높음
			// 시간을 최대 몇 분까지 줄지 (단위 : 초)
			session.setMaxInactiveInterval(3 * 60);
			
			
			// 세션객체아이디 확인
//			System.out.println("새션객체 아이디 : " + session.getId());
			
			String ip = request.getRemoteAddr();
//			System.out.println("[ip = " + ip + "]");
			session.setAttribute("ip", ip);
			
			// 세션객체에 속성으로 저장
			session.setAttribute("memberLoggedIn", memberLoggedIn);
			
			// 페이지 리다이렉트 : 클라이언트에게 돌려보낸 후, 해당 url로 다시 요청하도록 한다.
			// 요청할 주소만 보낸다. forward와 다르다.
//			response.sendRedirect(request.getContextPath()); // /mvc
			response.sendRedirect(referer);
			
		}
		// 2. 로그인 실패한 경우
		else {
			view = "/WEB-INF/views/common/msg.jsp";
			
			// 2-1. 비밀번호가 틀린 경우
			if(result == MemberService.WRONG_PASSWORD) {
				msg = "패스워드를 잘못 입력하셨습니다.";
			}
			// 2-2. 아이디가 존재하지 않는 경우
			else if(result == MemberService.ID_NOT_EXIST) {
				msg = "존재하지 않는 아이디입니다.";
			}
			
			
			// 속성에 값 보관
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			
			RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
			reqDispatcher.forward(request, response);
		}
		
		
		} catch(Exception e) {
			e.printStackTrace();
			throw new MemberException("로그인 오류! 관리자에게 문의하세요.");
			
		}
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

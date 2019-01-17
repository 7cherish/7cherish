package com.kh.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.admin.model.service.AdminService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberListServlet
 */
/**
 * 페이징 recipe
 * 1. 주요공식
 * - 시작rownum/끝rownum을 구하는 공식 : AdminDao.selectMemberList(cPage, numPerPage)
 * - 전체페이지수totalPage를 구하는 공식 : 올림처리. 모든 게시물을 담아야함.
 * - 페이지바에서 시작번호pageStart를 구하는 공식
 * 
 * 2. 사용할 변수
 * (컨텐츠영역)
 * - 현재 페이지 cPage
 * - 페이지당 컨텐츠 수 numPerPage
 * 
 * (페이지바영역)
 * - 총 컨텐츠 수 totalContent
 * - 전체 페이지 수 totalPage : 페이지당 컨텐츠 수와 총 컨텐츠 수를 알면 구할 수 있다.
 * - 페이지바에 표시할 페이지 수 pageBarSize
 * - 페이지바시작변수 startPage, 페이지바끝변수 endPage
 * - 페이지바에서 사용할 증감변수 pageNo
 */
@WebServlet("/admin/memberList")
public class AdminMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 관리자여부 체크
		Member memberLoggedIn = (Member)request.getSession()
											   .getAttribute("memberLoggedIn");
		if(memberLoggedIn == null ||
		  !"admin".equals(memberLoggedIn.getMemberId())) {
			request.setAttribute("msg", "잘못된 경로로 접근하셨습니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
				   .forward(request, response);
			return;
		}
		
		// 1. parameterHandling
		int cPage; // 시작 페이지
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1; // 별 다른 일이 없으면 null일때 1페이지로 해주는 코드
		}
		
		
		int numPerPage;
		try {
		numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		} catch (NumberFormatException e) {
			numPerPage = 5; // 초기값 세팅
		}
		
		System.out.printf("[cPage=%s, numPerPage=%s]\n", cPage, numPerPage);
			
		// 2. 업무로직
		// 2.1. 컨텐츠영역 (회원목록 리스트로 받아오기)
		List<Member> list = new AdminService().selectMemberList(cPage, numPerPage);
		
		// 2.2. 페이지바영역
		// 전체 컨텐츠 수 (전체 회원 수)를 구하기
		int totalContent = new AdminService().selectMemberCount();
		System.out.printf("[totalContent=%s]\n", totalContent);
		
		// (공식2) 전체페이지수 구하기
		// totalContent, numPerPage
		// 예) numPerPage = 10, totalContent = 113 => totalPage = 11
		int totalPage = (int)Math.ceil((double)totalContent/numPerPage);
		System.out.printf("[totalPage=%s]\n", totalPage);
		
		// 페이지바 html
		String pageBar = "";
		
		// 페이지바 길이
		int pageBarSize = 5;
		
		// (공식3) 시작페이지startPage 번호세팅
		// cPage = 5, pageBarSize = 5 => 1
		// cPage = 6, pageBarSize = 5 => 6
		int startPage = ((cPage-1)/pageBarSize) * pageBarSize + 1;
		int endPage = startPage + pageBarSize - 1;
		System.out.printf("[startPage=%s, endPage=%s]\n", startPage, endPage);
		
		// 페이지 증감변수
		int pageNo = startPage;
		
		// [이전] section
		if(pageNo == 1) {
			
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+
					   "/admin/memberList?"+
					   "cPage="+(pageNo-1)+
					   "&numPerPage="+numPerPage+"'>[이전]</a>";
		}
		
		// [페이지] section
		while(pageNo <= endPage && pageNo <= totalPage) {
			if(cPage == pageNo) {
				pageBar += "<span class='cPage'>"+pageNo+"</span>";
			}
			else {
				pageBar += "<a href='"+request.getContextPath()+
						   "/admin/memberList?"+
						   "cPage="+pageNo+
						   "&numPerPage="+numPerPage+"'>"+
						   pageNo+"</a>";
			}
			pageNo++;
		}
		
		// [다음] section
		if(pageNo > totalPage) {
			
		}
		else {
			// 전체 페이지보다 현재 페이지가 작으면 이 코드 실행
			pageBar += "<a href='"+request.getContextPath()+
					   "/admin/memberList?"+
					   "cPage="+pageNo+
					   "&numPerPage="+numPerPage+"'>[다음]</a>";
		}
		
		// 3. view단처리
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp")
			   .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

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
 * Servlet implementation class AdminMemberFinderServlet
 */
/**
 * 페이징 recipe 1. 주요공식 - 시작rownum/끝rownum을 구하는 공식 :
 * AdminDao.selectMemberList(cPage, numPerPage) - 전체페이지수totalPage를 구하는 공식 :
 * 올림처리. 모든 게시물을 담아야함. - 페이지바에서 시작번호pageStart를 구하는 공식
 * 
 * 2. 사용할 변수 (컨텐츠영역) - 현재 페이지 cPage - 페이지당 컨텐츠 수 numPerPage
 * 
 * (페이지바영역) - 총 컨텐츠 수 totalContent - 전체 페이지 수 totalPage : 페이지당 컨텐츠 수와 총 컨텐츠 수를
 * 알면 구할 수 있다. - 페이지바에 표시할 페이지 수 pageBarSize - 페이지바시작변수 startPage, 페이지바끝변수
 * endPage - 페이지바에서 사용할 증감변수 pageNo
 */
@WebServlet("/admin/memberFinder")
public class AdminMemberFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 0. 관리자가 아닌 경우
		// 현재 로그인한 사람, 로그인하지 않고 요청하는 사람 걸러내기
		Member memberLoggedIn = (Member) request.getSession().getAttribute("memberLoggedIn");
		if (memberLoggedIn == null || !"admin".equals(memberLoggedIn.getMemberId())) {
			request.setAttribute("msg", "잘못된 경로로 접근하셨습니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			return;
		}

		// 1. parameterHandling
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		System.out.printf("[%s : %s]\n", searchType, searchKeyword);

		// cPage, numPerPage에 대한 파라미터처리 (Exception)
		int cPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1; // 초기화
		}

		int numPerPage;
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		} catch (NumberFormatException e) {
			numPerPage = 5; // 초기화
		}
		System.out.printf("[searchType=%s, searchKeyword=%s, cPage=%s, numPerPage=%s]\n", searchType, searchKeyword,
				cPage, numPerPage);

		// 2. businessLogic요청
		// 2.1. 컨텐츠 영역
		List<Member> list = null;
		switch (searchType) {
		case "memberId":
			list = new AdminService().selectMemberByMemberId(searchKeyword, cPage, numPerPage);
			break;
		case "memberName":
			list = new AdminService().selectMemberByMemberName(searchKeyword, cPage, numPerPage);
			break;
		case "gender":
			list = new AdminService().selectMemberByGender(searchKeyword, cPage, numPerPage);
			break;
		}

		// 2.2. 페이지바 영역
		// 검색한 전체 회원수를 구해와야한다. 그래야 페이지바를 만들 수 있다.
		// memberId를 검색하는지 memberName을 검색하는지 gender를 검색하는지 모르기 때문에
		// 분기해서 각각 처리해야 한다.
		// 위의 switch문 참고
		int totalContent = 0;
		switch (searchType) {
		case "memberId":
			totalContent = new AdminService().selectMemberCountByMemberId(searchKeyword);
			break;
		case "memberName":
			totalContent = new AdminService().selectMemberCountByMemberName(searchKeyword);
			break;
		case "gender":
			totalContent = new AdminService().selectMemberCountByGender(searchKeyword);
			break;
		}

		// totalPage 구하기 => 를 통해서 startPage, endPage를 알 수 있다.
		int totalPage = (int) Math.ceil((double) totalContent / numPerPage);
		System.out.printf("[totalPage=%s]\n", totalPage);
		// 페이지바 길이
		int pageBarSize = 5;
		int startPage = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
		int endPage = startPage + pageBarSize - 1;

		// 페이지 증감변수
		int pageNo = startPage;

		// pageBar : html
		// (주의) 검색어를 링크에 어떻게 포함시킬것인가?
		String pageBar = "";

		// [이전] section
		if (pageNo == 1) {

		} else {
			pageBar += "<a href='" + request.getContextPath() + "/admin/memberFinder?" + "cPage=" + (pageNo - 1)
					+ "&numPerPage=" + numPerPage + "&searchType=" + searchType + "&searchKeyword=" + searchKeyword
					+ "'>[이전]</a>";
		}
		// [페이지] section
		// 보통 !(빠져나가는 조건식)으로 많이 쓴다.
		// while(pageNo <= pageEnd && pageNo <= totalPage){
		while (pageNo <= endPage && pageNo <= totalPage) {
			if (cPage == pageNo) {
				pageBar += "<span class='cPage'>" + pageNo + "</span>";
			} else {
				pageBar += "<a href='" + request.getContextPath() + "/admin/memberFinder?" + "cPage=" + pageNo
						+ "&numPerPage=" + numPerPage + "&searchType=" + searchType + "&searchKeyword=" + searchKeyword
						+ "'>" + pageNo + "</a>";
			}
			pageNo++;
		}
		// [다음] section
		if (pageNo > totalPage) {

		} else {
			// 전체 페이지보다 현재 페이지가 작으면 이 코드 실행
			pageBar += "<a href='" + request.getContextPath() + "/admin/memberFinder?" + "cPage=" + pageNo
					+ "&numPerPage=" + numPerPage + "&searchType=" + searchType + "&searchKeyword=" + searchKeyword
					+ "'>[다음]</a>";
		}

		// 3. view단 처리
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.getRequestDispatcher("/WEB-INF/views/admin/memberFinder.jsp").forward(request, response);

		System.out.printf("[list=%s, pageBar=%s, cPage=%s, numPerPage=%s]\n", list, pageBar, cPage, numPerPage);
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

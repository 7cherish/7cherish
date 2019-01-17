package com.kh.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet(name = "BoardListServlet", urlPatterns = "/board/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. parameterHandling
		int cPage; // 시작페이지
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
			cPage = 1;
		}
		
		int numPerPage;
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		} catch(NumberFormatException e) {
			numPerPage = 5; // 초기값 세팅
		}
		
		System.out.printf("[cPage=%s, numPerPage=%s]\n", cPage, numPerPage);
		
		// 2. 업무로직
		// 2.1. 컨텐츠영역 (게시판 리스트로 받아오기)
		List<Board> bList = new BoardService().selectBoardList(cPage, numPerPage);
		
		// 2.2. 페이지바영역
		// 전체 컨텐츠 수 (전체 게시글 수)를 구하기
		int totalContent = new BoardService().selectBoardCount();
		System.out.printf("[totalContent=%s]\n", totalContent);
		
		// (공식2) 전체 페이지수 구하기s
		int totalPage = (int)Math.ceil((double)totalContent/numPerPage);
		System.out.printf("[totalPage=%s]\n", totalPage);
		
		// 페이지바 html
		String pageBar = "";
		
		// 페이지바 길이
		int pageBarSize = 5;
		
		// (공식3) 시작페이지 startPage 번호세팅
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
					   "/board/boardList?"+
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
						   "/board/boardList?"+
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
							   "/board/boardList?"+
							   "cPage="+pageNo+
							   "&numPerPage="+numPerPage+"'>[다음]</a>";
				}
				
				// 3. view단처리
				request.setAttribute("bList", bList);
				request.setAttribute("pageBar", pageBar);
				request.setAttribute("cPage", cPage);
				request.setAttribute("numPerPage", numPerPage);
				request.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp")
					   .forward(request, response);
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

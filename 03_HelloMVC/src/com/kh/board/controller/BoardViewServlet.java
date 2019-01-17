package com.kh.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. parameterHandling
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		// boardNo는 board테이블의 pk
		// 하나하나의 행의 정보를 가져올 수 있다.

		// 2. businessLogic - Service단에 요청
		BoardService boardService = new BoardService();

		// boardCookie 확인
		Cookie[] cookies = request.getCookies();
		String boardCookieVal = "";
		boolean hasRead = false;

		/*
		 * 쿠키 검사할땐 반드시 쿠키 null 여부부터 검색해야 한다. 사이트에 최초 접속하면 쿠키가 없기 때문에 null을 리턴한다. 그러면 작업을
		 * 진행할 수 없으니까 NullPointerException발생
		 */
		if (cookies != null) {
			for (Cookie c : cookies) {
				String name = c.getName();
				String value = c.getValue();

				// boardCookie인 경우
				if ("boardCookie".equals(name)) {
					boardCookieVal = value;
					if (value.contains("|" + boardNo + "|")) {
						// value가 (안의 내용)을 가지고 있니?
						// 있다면 hasRead를 true로 바꿔주고 반복문 종료
						hasRead = true;
						break;
					}
				}
			}
		}

		// boardCookie에서 읽음 여부에 따라 분기
		// 읽은 적이 없을때만 조회수 카운트
		if (!hasRead) {
			int result = boardService.increaseReadCount(boardNo);

			// 쿠키를 새로 생성 : 지금 읽은 현재 게시물 번호를 추가해야하기 때문
			Cookie boardCookie = new Cookie("boardCookie", boardCookieVal + "|" + boardNo + "|");
//			boardCookie.setMaxAge(); // 삭제하지 않으면 계속 있음 (영속설정)
//			boardCookie.setPath("/mvc/board"); // 현재 디렉토리 기준으로 설정
			response.addCookie(boardCookie);
		}
		
		// 게시글 가져오기
		Board b = new BoardService().selectOne(boardNo);
		System.out.println("board@BoardViewServlet = " + b);
		
		// 게시글에 대한 댓글 가져오기
		List<BoardComment> commentList = boardService.selectCommentList(boardNo);
		request.setAttribute("board", b); // 게시글 정보
		request.setAttribute("commentList", commentList);

		// 3. view단
		String msg = "";
		String loc = "/";
		String view = "/WEB-INF/views/board/boardView.jsp";
		if (b == null) {
			view = "/WEB-INF/views/common/msg.jsp";
			msg = "상세보기 실패";
		}
		// request객체에 속성 등록
		request.setAttribute("msg", msg); // 실패했을 경우만 사용
		request.setAttribute("loc", loc); // 실패했을 경우만 사용

		request.getRequestDispatcher(view).forward(request, response);
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

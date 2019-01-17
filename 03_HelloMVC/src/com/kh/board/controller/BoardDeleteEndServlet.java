package com.kh.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;

/**
 * Servlet implementation class BoardDeleteEndServlet
 */
@WebServlet("/board/boardDelete")
public class BoardDeleteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. parameterHandling
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String rName = request.getParameter("rName");
		// 파일이 있는 게시글도 있고 없는 게시글도 있는데,
		// rName의 값이 파일이 있는 경우 : rName = 저장된 파일명,
		// rName의 값이 파일이 없는 경우 : rName = "" null은 rName이라는 name이 아예 없을 때 나온다.

		// 2. businessLogic
		int result = new BoardService().deleteBoard(boardNo);
		// 첨부파일삭제
		if (result > 0 && !"".equals(rName)) {
			String saveDirectory = getServletContext().getRealPath("/upload/board/");
			File delFile = new File(saveDirectory + rName);
			System.out.println("board_delFile@BoardDeleteEndServlet=" + delFile); // toString오버라이딩되어서 파일경로 출력
			// 파일삭제
//			System.out.println("@BoardDeleteEndServlet_파일삭제여부 : " + delFile.delete()); // boolean을 리턴, 파일 삭제시 true를 반환
		
			// (삭제한)파일이동(WebCotent/deleteFiles/board)
			String delDirectory = getServletContext().getRealPath("/deleteFiles/board/");
			// 생성되진 않았지만 가상의 파일 생성
			// 삭제파일이 보관될곳(delDirectory)
			File delFile_ = new File(delDirectory + rName);
			// 기존파일 delFile_로 리네임
			delFile.renameTo(delFile_);
			
		}

		// 3. view
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/board/boardList";
		
		if(result > 0) {
			// 삭제 성공
			msg = "게시글 삭제 성공!";
		}
		else {
			msg = "게시글 삭제 실패!";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view)
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

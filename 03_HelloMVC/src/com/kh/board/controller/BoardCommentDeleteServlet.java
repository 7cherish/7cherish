package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;

/**
 * Servlet implementation class BoardCommentDeleteServlet
 */
@WebServlet("/board/boardCommentDelete")
public class BoardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 파라미터 핸들링
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int boardCommentNo = Integer.parseInt(request.getParameter("boardCommentNo"));
		int boardCommentLevel = Integer.parseInt(request.getParameter("boardCommentLevel"));
		
		int result = new BoardService().boardCommentDelete(boardCommentNo, boardCommentLevel);
		
		String msg = "";
		String loc = "/board/boardView?boardNo=";
		String view = "/WEB-INF/views/common/msg.jsp";
		
		if(result>0){
			msg = "댓글 삭제 성공!";
		}
		else{
			msg = "댓글 삭제 실패!";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc + boardNo);
		request.getRequestDispatcher(view)
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

package com.kh.photo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.photo.model.service.PhotoService;

/**
 * Servlet implementation class PhotoListServlet
 */
@WebServlet("/photo/photoList")
public class PhotoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 업무로직처리(전달받은게 없으므로 바로 업무로직처리)
		// 전체 페이지 수 : 총 게시물 수, 페이지당 표시할 게시물 수
		// ex) 103개의 게시물이 있고 페이지당 10개씩 표시한다고 하면 11개 페이지가 필요함(올림처리)
		int totalContents = new PhotoService().selectPhotoCount();
		int numPerPage = 5;
		int totalPage = (int)Math.ceil(totalContents * 1.0 /numPerPage);
		
		request.setAttribute("totalPage", totalPage);
		
		// 2. view단 처리
		request.getRequestDispatcher("/WEB-INF/views/photo/photoList.jsp")
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

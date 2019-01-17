package com.kh.action.el;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.action.model.vo.Person;

/**
 * Servlet implementation class ElServlet
 */
@WebServlet("/el")
public class ElServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ElServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request객체에 속성으로 저장
//		request.setAttribute("coffee", "티오피");
		request.setAttribute("year", 2019);
		request.setAttribute("person", new Person("홍길동", '남', 400));
		
		List<String> items = new ArrayList<>();
		items.add("기계식키보드");
		items.add("스케이트보드");
		items.add("보드카토닉");
		
		request.setAttribute("items", items);
		
		HttpSession session = request.getSession();
		session.setAttribute("coffee", "예가체프");
		
		// applicationScope -> servletContext
		ServletContext application = request.getServletContext();
		application.setAttribute("coffee", "옐로버번");
		
		String view = "/el/elView.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

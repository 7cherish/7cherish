package com.kh.action.standard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.action.model.vo.Person;

/**
 * Servlet implementation class PersonViewServlet
 */
@WebServlet("/personView")
public class PersonViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonViewServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 특정 처리만 하고 바로 jsp로 넘어갈 것이다.
		
		// Person객체 생성
		Person person = new Person();
		person.setName("홍길동");
		person.setGender('남');
		person.setAge(20);
		
		// request객체에 속성으로 지정
		request.setAttribute("person", person);
		
		// view단 전송
		String view = "/standard/useBean/personView.jsp";
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

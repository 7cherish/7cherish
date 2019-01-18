package com.kh.action.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.action.model.vo.Person;

/**
 * Servlet implementation class PersonBeanServlet
 */
@WebServlet("/personBeanHandler")
public class PersonBeanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonBeanServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Encoding
		request.setCharacterEncoding("UTF-8");
		
		// 2. parmeterHandling
		String name = request.getParameter("name");
		char gender = request.getParameter("gender").charAt(0);
		int age = Integer.parseInt(request.getParameter("age"));
		String view = request.getParameter("view");
		
		Person p = new Person(name, gender, age);
		System.out.println("p @PersonBeanServlet = " + p);
		System.out.println("view @PersonBeanServlet = " + view);
		
		// 3. view단 처리
		request.setAttribute("person", p);
		request.getRequestDispatcher("/test/" + view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

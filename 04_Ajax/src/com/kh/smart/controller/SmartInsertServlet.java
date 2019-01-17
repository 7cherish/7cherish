package com.kh.smart.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.smart.model.dao.SmartDao;
import com.kh.smart.model.vo.Smart;

/**
 * Servlet implementation class SmartInsertServlet
 */
@WebServlet("/smart/order.do")
public class SmartInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SmartInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.encoding
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		//2.param
		String pname = (String)request.getParameter("pname");
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		//3.업무로직
		int result = new SmartDao().insertSmart(pname, amount);
			
		
		List<Smart> list = new SmartDao().selectRecent5(); //결과값이 담겨있음
		
		//4.view단
		//java객체 => json(gson) 
		 new Gson().toJson(list , response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

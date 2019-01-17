package com.kh.json;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.common.UserListSingleton;
import com.kh.model.vo.User;

/**
 * Servlet implementation class AjaxGsonSelectAllServlet
 */
@WebServlet("/gson/selectAll.do")
public class AjaxGsonSelectAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxGsonSelectAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전달받은 파라미터 없음
		// 1. 업무로직
		// 회원정보 가져오기
		List<User> userList = UserListSingleton.getInstance().getUserList();
		System.out.println("userList@AjaxGsonSelectAllServlet" + userList);
		
		//2. 응답객체
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new Gson();
		String jsonStr = gson.toJson(userList);
		System.out.println("jsonStr@AjaxGsonSelectAllServlet" + jsonStr);
		response.getWriter().println(jsonStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

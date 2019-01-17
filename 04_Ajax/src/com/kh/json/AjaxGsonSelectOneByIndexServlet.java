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
 * Servlet implementation class AjaxGsonSelectOneByIndexServlet
 */
@WebServlet("/gson/selectOneByIndex.do")
public class AjaxGsonSelectOneByIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxGsonSelectOneByIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. encoding
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		// 2. parameterHandling
		int index = Integer.parseInt(request.getParameter("index"));
		
		// 3. businessLogic
		List<User> userList = UserListSingleton.getInstance().getUserList();
		
		// 리턴할 유저객체 생성
		User user = null;
		
		// 간단한 유효성 검사 : index가 0보다 같거나 크면서 index는 userList.size()보다 작아야 한다.
		if(index >= 0 && index < userList.size()) {
			user = userList.get(index);
		}
		
		// 4. view
//		1번째 방법
//		Gson gson = new Gson();
//		String jsonStr = gson.toJson(user);
//		response.getWriter().println(jsonStr);
		
//		2번째 방법
		// void com.google.gson.Gson.toJson(Object src, Appendable writer) throws JsonIOException
		new Gson().toJson(user, response.getWriter()); // json문자로 바꿀 자바 객체, PrintWriter객체
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

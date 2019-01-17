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
 * Servlet implementation class AjaxGsonInsertUserServlet
 */
@WebServlet("/gson/insertUser.do")
public class AjaxGsonInsertUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxGsonInsertUserServlet() {
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
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String userAddr = request.getParameter("userAddr");
		
		// 3. 업무로직처리 : User추가
		// 회원정보 가져오기
		// 리턴할 유저객체 생성
		List<User> userList = UserListSingleton.getInstance().getUserList();
		userList.add(new User(userId, userName, userAddr));
		System.out.println("userList@AjaxGsonInsertUserServlet" + userList);
		
		//4. 응답객체에 출력
		Gson gson = new Gson();
		String jsonStr = gson.toJson(userList);
		System.out.println("jsonStr@AjaxGsonInsertUserServlet" + jsonStr);
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

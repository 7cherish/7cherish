package com.kh.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.kh.model.vo.User;

/**
 * Servlet implementation class AjaxJsonServlet
 */
@WebServlet("/json/helloJson.do")
public class AjaxJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxJsonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. json 객체(오브젝트, 어레이) key value형식 자바의 Map과 비슷. 순서보장x
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", "이순신");
		jsonObj.put("age", 27);
		jsonObj.put("height", 188.5);
		jsonObj.put("bool", true);
//		jsonObj.put("friend", new User("sun", "선조", "종로구 경복궁"));
		// {"bool":true,"name":"이순신","friend":User [userId=sun, userName=선조, userAddr=종로구 경복궁],"age":27,"height":188.5}
		// User메소드의 toString된것이 출력됨. 꺼내 쓰기 애매하다.
		// JSONObject로 넣어야 한다.
		
		JSONObject sun = new JSONObject();
		// 필드명 필드값을 쌍으로 저장
		sun.put("userId", "sun");
		sun.put("userName", "선조");
		sun.put("userAddr", "종로구 경복궁");
		
		// json오브젝트에 키와 값을 넣었다.
		jsonObj.put("sun", sun);
		
		System.out.println(jsonObj);
		// {"bool":true,"name":"이순신","sun":{"userAddr":"종로구 경복궁","userName":"선조","userId":"sun"},"age":27,"height":188.5}
		
		// 출력
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(jsonObj);
		
		// 2. json배열 : ArrayList
		JSONArray jsonArr = new JSONArray();
		
		List<User> list = new ArrayList<>();
		list.add(new User("honggd", "홍길동", "서울시 동작구"));
		list.add(new User("sinsa", "신사임당", "서울시 관악구"));
		list.add(new User("dangoon", "단군", "경기도 평택"));
		
		for(User u: list) {
			// 유저객체 하나하나를 JSON객체에 넣어준다.
			JSONObject jsonUser = new JSONObject();
			jsonUser.put("userId", u.getUserId());
			jsonUser.put("userName", u.getUserName());
			jsonUser.put("userAddr", u.getUserAddr());
			
			// JSON배열에 추가
			jsonArr.add(jsonUser);
		}
		out.println(jsonArr);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

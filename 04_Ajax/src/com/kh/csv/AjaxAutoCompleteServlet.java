package com.kh.csv;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.model.dao.AjaxDao;

/**
 * Servlet implementation class AjaxAutoCompleteServlet
 */
@WebServlet("/csv/autoComplete.do")
public class AjaxAutoCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxAutoCompleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 한글값 대비 encoding
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/csv; charset=utf-8");

		// 2. parameterHandling
		String srchName = request.getParameter("srchName");
		System.out.println("srchName@AjaxAutoCompleteServlet=" + srchName);

		// 3. 업무로직
		List<String> list = new AjaxDao().selectByName(srchName);

		// 리턴할 csv변수 선언
		String csv = "";
		System.out.println("list@AjaxAutoCompleteServlet=" + list);

		// csv가공. list가 비어있지 않다면 밑의 코드 실행
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				if (i != 0)
					csv += ","; // i가 0이 아닐 때 콤마 찍기
				csv += list.get(i);
			}
		}
		// 김민성, 김보람, 김병선, 김민지, 김은찬, 김민우

		// 4. view단 처리
		PrintWriter out = response.getWriter();
		out.append(csv);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.kh.csv;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.model.vo.Member;

/**
 * Servlet implementation class AjaxCSVServlet
 */
@WebServlet("/csv.do")
public class AjaxCSVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxCSVServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 업무로직
		List<Member> list = new ArrayList<>();
		list.add(new Member("박보검", "01007890789", "parkBogum.jpg"));
		list.add(new Member("줄리아 로버츠", "01012341234", "juliaRoberts.jpg"));
		list.add(new Member("맷 데이먼", "01045674567", "mattDamon.jpg"));
		
		// 응답객체 쓸 CSV변수 작성
		String csv = "";
		for(int i=0; i<list.size(); i++) {
			if(i!=0) csv += "§"; // 데이터에 절대 포함되지 않을 구분자 사용할 것
			csv += list.get(i);
		}
		System.out.println("csv@AjaxCSVServlet=" + csv);
		
		// 2. response객체 출력
		response.setContentType("text/csv; charset=UTF-8");
		// 출력스트림 가져오기
		PrintWriter out = response.getWriter();
		out.append(csv);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

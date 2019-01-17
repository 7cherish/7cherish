package com.kh.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.model.vo.Member;

/**
 * Servlet implementation class AjaxXmlMemberServlet
 */
@WebServlet("/xml/member.do")
public class AjaxXmlMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxXmlMemberServlet() {
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
		
		// 2. view단 처리
		request.setAttribute("list", list);
		request.getRequestDispatcher("/xml/member_xml.jsp")
			   .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

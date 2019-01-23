package com.kh.emp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.emp.model.service.EmpService;
import com.kh.emp.model.service.EmpServiceImpl;

public class EmpListController2 extends AbstractController {
	// 1. Student에서는 IStudentService(인터페이스명)하고 StudentService(실제 구현 클래스) 로 했었다.
	// 2. 또 다른 스타일은 StudentService(인터페이스명) StudentServiceImpl(실제 구현 클래스) 가 있다.
	EmpService empService = new EmpServiceImpl();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 0. 파라미터 핸들링
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		String gender = request.getParameter("gender");
		String salary = request.getParameter("salary");
		String salaryFlag = request.getParameter("salaryFlag");
		
		
		Map<String, String> map = new HashMap<>();
		map.put("searchType", searchType);
		map.put("searchKeyword", searchKeyword);
		map.put("gender", gender);
		map.put("salary", salary);
		map.put("salaryFlag", salaryFlag);
		System.out.println("map@searchType, searchKeyword, gender, salary, salaryFlag@EmpListController2 = " + map);
		
		// 1. 업무로직
		// Map으로 처리
		// serach0은 검색 기능 제거하고 기본이 될 것이다.
		List<Map<String, String>> list = empService.search2(map);

		System.out.println("list@EmpListController2 = " + list);
		

		// 2. view단
		request.setAttribute("list", list);
		
		setView("/WEB-INF/views/emp/search2.jsp");
	}

}

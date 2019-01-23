package com.kh.emp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.emp.model.service.EmpService;
import com.kh.emp.model.service.EmpServiceImpl;

public class EmpListController3 extends AbstractController {
	// 1. Student에서는 IStudentService(인터페이스명)하고 StudentService(실제 구현 클래스) 로 했었다.
	// 2. 또 다른 스타일은 StudentService(인터페이스명) StudentServiceImpl(실제 구현 클래스) 가 있다.
	EmpService empService = new EmpServiceImpl();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 0. 파라미터 핸들링
		// 체크박스이므로 배열로 받아야 한다.
		// 조건에 따라 나를때만 배열 사용
		String[] jobCodeArr = request.getParameterValues("jobCode");
		String[] deptCodeArr = request.getParameterValues("dept_code");

		// 이번에는 key : String, value : String[]
		Map<String, String[]> map = new HashMap<>();
		map.put("jobCodeArr", jobCodeArr);
		map.put("deptCodeArr", deptCodeArr);
		
		System.out.println("map@jobCodeArr@EmpListController3 = " + map);
		
		// 1. 업무로직
		// Map으로 처리
		// serach0은 검색 기능 제거하고 기본이 될 것이다.
		// 돌려받는건 String, String
		List<Map<String, String>> list = empService.search3(map);

		System.out.println("list@EmpListController3 = " + list);

		// 2. view단
		request.setAttribute("list", list);
		
		setView("/WEB-INF/views/emp/search3.jsp");
	}

}

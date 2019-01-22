package com.kh.emp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.emp.model.service.EmpService;
import com.kh.emp.model.service.EmpServiceImpl;

public class EmpListController extends AbstractController {
	// Student에서는 IStudentService(인터페이스명)하고 StudentService(실제 구현 클래스) 로 했었다.
	// 또 다른 스타일은 StudentService(인터페이스명) StudentServiceImpl(실제 구현 클래스) 가 있다.
	EmpService empService = new EmpServiceImpl();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1. 업무로직
		// Map으로 처리
		// serach0은 검색 기능 제거하고 기본이 될 것이다.
		List<Map<String, String>> list = empService.search0();
		System.out.println("list@EmpListController = " + list);
		
		// 2. view단
		request.setAttribute("list", list);
		setView("/WEB-INF/views/emp/search1.jsp");
	}

}

package com.kh.emp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.emp.model.service.EmpService;
import com.kh.emp.model.service.EmpServiceImpl;

public class EmpListController extends AbstractController {
	// 1. Student에서는 IStudentService(인터페이스명)하고 StudentService(실제 구현 클래스) 로 했었다.
	// 2. 또 다른 스타일은 StudentService(인터페이스명) StudentServiceImpl(실제 구현 클래스) 가 있다.
	EmpService empService = new EmpServiceImpl();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 0. 파라미터 핸들링
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		
		// 이전에 했던 방식은 Dao SqlSession에 작성하고
		// 1번째 인자 : mapper.xml의 쿼리를 찾기 위해서 namespace.쿼리 id 전달
		// 2번째 인자 : 없거나 1개했었는데 (여러 개 안 됨)
		
		// 현재는 인자를 2개 전달해야 하기 때문에 Map으로 전달해야 한다.
		Map<String, String> map = new HashMap<>();
		map.put("searchType", searchType);
		map.put("searchKeyword", searchKeyword);
		System.out.println("map@searchType, searchKeyword@EmpListController = " + map);
		
		// 1. 업무로직
		// Map으로 처리
		// serach0은 검색 기능 제거하고 기본이 될 것이다.
		// 결과값 받을 list를 우선 null로 초기화 해준다.
		List<Map<String, String>> list = null;
		if(searchType == null || searchKeyword == null) {
			list = empService.search0();
			System.out.println("list@search0@EmpListController = " + list);
		} else {
			list = empService.search1(map);
			System.out.println("list@search1@EmpListController = " + list);
		}

		// 2. view단
		request.setAttribute("list", list);
		
		setView("/WEB-INF/views/emp/search1.jsp");
	}

}

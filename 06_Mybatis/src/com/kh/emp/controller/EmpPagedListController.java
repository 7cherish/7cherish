package com.kh.emp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.emp.model.service.EmpService;
import com.kh.emp.model.service.EmpServiceImpl;

public class EmpPagedListController extends AbstractController {
	
	EmpService empService = new EmpServiceImpl();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1. 파라미터 핸들링
		int numPerPage = 5;
		int cPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1;
		}
		
		// 2. 업무로직 처리
		List<Map<String, String>> list = empService.selectEmpPagedList(cPage, numPerPage);
		System.out.println("list@EmpPagedListController" + list);
		
		// 3. view단 처리
		request.setAttribute("list", list);
		setView("/WEB-INF/views/emp/empPagedList.jsp");
	}

}

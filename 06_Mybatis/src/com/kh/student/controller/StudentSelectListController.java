package com.kh.student.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.student.model.service.IStudentService;
import com.kh.student.model.service.StudentService;
import com.kh.student.model.vo.Student;

public class StudentSelectListController extends AbstractController {
	
	IStudentService studentService = new StudentService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1. 업무로직
		// vo
		List<Student> list = studentService.selectList();
		
		System.out.println("list@StudentSelectListController" + list);

		// Map
		List<Map<String, String>> mapList = studentService.selectMapList();
		
		System.out.println("mapList@StudentSelectListController" + mapList);
		
		// 2. view단 처리
		request.setAttribute("list", list);
		request.setAttribute("mapList", mapList);
		
		setView("/WEB-INF/views/student/selectList.jsp");
	}

}

package com.kh.student.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.common.AbstractController;
import com.kh.student.model.service.IStudentService;
import com.kh.student.model.service.StudentService;
import com.kh.student.model.vo.Student;

public class StudentSelectOneAjaxController extends AbstractController {
	
	IStudentService studentService = new StudentService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1. 파라미터 핸들링
		int studentNo = Integer.parseInt(request.getParameter("stdtNo"));
		System.out.println("studentNo@StudentSelectOneAjaxController = " + studentNo);
		
		// 2. 비지니스로직 처리
		Student s = new StudentService().selectOneStudent(studentNo);
		
		
		// 3. json 쓰기
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(s, response.getWriter());
	}

}

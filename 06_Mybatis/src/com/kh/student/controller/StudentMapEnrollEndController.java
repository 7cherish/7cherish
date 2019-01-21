package com.kh.student.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.student.model.service.IStudentService;
import com.kh.student.model.service.StudentService;

public class StudentMapEnrollEndController extends AbstractController {

	IStudentService studentService = new StudentService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 2.parameterHandling
		String studentName = request.getParameter("studentName");
		String studentTel = request.getParameter("studentTel");
		String studentEmail = request.getParameter("studentEmail");
		String studentAddr = request.getParameter("studentAddr");

		Map<String, String> map = new HashMap<>();
		map.put("studentName", studentName);
		map.put("studentTel", studentTel);
		map.put("studentEmail", studentEmail);
		map.put("studentAddr", studentAddr);

		// 3.businessLogic
		// insertStudent iStudentService에 먼저 만들어짐
		int result = studentService.insertStudent(map);

		// 4.view단 지정
		System.out.println(result > 0 ? "학생등록성공" : "학생등록실패");
		setRedirect(true); // 바로 현재페이지 돌아가기
		setView(request.getContextPath() + "/student/studentEnroll.do");
	}

}

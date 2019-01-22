package com.kh.student.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.student.model.service.IStudentService;
import com.kh.student.model.service.StudentService;

public class StudentDeleteController extends AbstractController {
	IStudentService studentService = new StudentService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터
		int studentNo = Integer.parseInt(request.getParameter("stdtNo"));
		
		int result = studentService.deleteStudentNo(studentNo);
		
		// 2. view단 처리
		System.out.println(result > 0 ? "학생삭제성공!" : "학생삭제실패!");
		setRedirect(true);
		setView(request.getContextPath() + "/student/selectOne.do");
		
		
	}
}

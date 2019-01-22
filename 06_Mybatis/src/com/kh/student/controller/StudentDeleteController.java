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
		// 0.파라미터 처리
		// 최초 입력값이 없다면 NumberFormatException 발생함.
		int studentNo = 0;
		try {
			studentNo = Integer.parseInt(request.getParameter("stdtNo"));
		} catch (NumberFormatException e) {
		}
		System.out.println("studentNo@StudentDeleteController = " + studentNo);

		// 1. 업무로직처리
		int result = 0;
		if (studentNo != 0) {
			result = studentService.deleteStudent(studentNo);
		}
		// 2.view단 출력
		System.out.println(result > 0 ? "삭제성공@StudentDeleteController" : "삭제실패@StudentDeleteController");
		this.setRedirect(true);
		this.setView(request.getContextPath() + "/student/selectOne.do");

	}
}

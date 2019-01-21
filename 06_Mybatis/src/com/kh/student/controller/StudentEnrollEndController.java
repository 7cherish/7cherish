package com.kh.student.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.student.model.service.IStudentService;
import com.kh.student.model.service.StudentService;
import com.kh.student.model.vo.Student;

public class StudentEnrollEndController extends AbstractController {
	// isRedirect view가 다 있다.
	IStudentService studentService = new StudentService();
	// 다형성 contoller 인터페이스 해서 service를 다룸
	// 다형성 : 부모타입의 참조변수에 자식객체를 담아서 사용. => 동적로딩이 가능해짐.

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1.encoding => fliter

		// 2.parameterHandling
		String studentName = request.getParameter("studentName");
		String studentTel = request.getParameter("studentTel");
		String studentEmail = request.getParameter("studentEmail");
		String studentAddr = request.getParameter("studentAddr");

		Student s = new Student();
		s.setStudentName(studentName);
		s.setStudentTel(studentTel);
		s.setStudentEmail(studentEmail);
		s.setStudentAddr(studentAddr);
		
		//System.out.println(s);

		// 3.businessLogic
		int result = studentService.insertStudent(s);
		// insertStudent iStudentService에 먼저 만들어짐

		// 4.view단 지정
		System.out.println(result > 0 ? "학생등록성공" : "학생등록실패");
		setRedirect(true); // 바로 현재페이지 돌아가기
		setView(request.getContextPath() + "/student/studentEnroll.do");

	}

}

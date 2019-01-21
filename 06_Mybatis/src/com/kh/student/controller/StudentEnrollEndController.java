package com.kh.student.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.student.model.service.IStudentService;
import com.kh.student.model.service.StudentService;
import com.kh.student.model.vo.Student;

public class StudentEnrollEndController extends AbstractController {
	// 사용하게 될 Service객체를 필드로 만들 것이다.
	// 근데 StudentService를 클래스타입으로 다루는게 아니라
	// 더 상위개념인 인터페이스로 다룰 것이다.
	
	// 하나의 서비스 객체를 필드로 만들어 사용하게 될텐데
	// 이때 서비스 객체를 다형성을 적용해서 IStudentService라는 인터페이스로 제어할 것이다.

	// isRedirect view가 다 있다.
	IStudentService studentService = new StudentService();
	// 다형성 contoller 인터페이스 해서 service를 다룸
	// 다형성 : 부모타입의 참조변수에 자식객체를 담아서 호출 => 동적로딩이 가능해짐.

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1.encoding => EncodeFilter가 처리함

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
		// insertStudent(s)는
		// studentService클래스에 먼저 만들어지는 것이 아니라,
		// IStudentService인터페이스에 먼저 만들어진다.

		// 4.view단 지정
		System.out.println(result > 0 ? "학생등록성공!" : "학생등록실패!");
		setRedirect(true); // 바로 현재페이지 돌아가기
		setView(request.getContextPath() + "/student/studentEnroll.do");

	}

}

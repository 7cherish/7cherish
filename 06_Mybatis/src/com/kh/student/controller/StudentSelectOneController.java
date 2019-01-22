package com.kh.student.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.student.model.service.IStudentService;
import com.kh.student.model.service.StudentService;

public class StudentSelectOneController extends AbstractController {
	// 인터페이스타입으로 서비스단을 제어한다.
	IStudentService studentService = new StudentService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 0. 파라미터 핸들링
		// studentNo값이 없으면 0이 될 것이다.
		// 0이거나 사용자 입력값이 들어온다.
		int studentNo = 0;
		try {
		studentNo = Integer.parseInt(request.getParameter("stdtNo"));
		} catch(NumberFormatException e) {
			// 처리코드 없음, 에러만 안 나게 함.
		}
		
		// 1. 비지니스 로직
		// a) 전체 학생수 구하기
		int count = studentService.selectStudentCount();
		request.setAttribute("count", count);
		
		// b) 학생번호로 학생이름 가져오기
		String studentName = "";
		// 모두 가져올 필요가 없다.
		// studentNo가 0이 아닐 때만
		if(studentNo != 0) {
			studentName = studentService.selectStudentName(studentNo);
		}

		// 서비스에 다녀왔다면 request에 속성으로 저장
		request.setAttribute("studentName", studentName);
		
		// 2. view단 처리
		setView("/WEB-INF/views/student/selectOne.jsp");
	}

}

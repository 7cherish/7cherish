package com.kh.student.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;

public class StudentEnrollController extends AbstractController{
// 서블릿은 하나이고, 컨트롤러로 넘기기 때문에 컨트롤러를 하나로 묶어서 처리할 필요가 있다.
// 컨트롤러 클래스의 부모클래스(추상클래스)를 임의로 만들 것이다.
// AbstractController를 상속한다.
// 부모필드와 메소드는 내 메소드
// 부모가 추상클래스이므로 구현해야할 메소드가 있다면 반드시 구현하게 해야 한다.
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		this.setRedirect(false); // false : 포워딩처리됐다. true : 리다이렉트처리됐다. 기본값 생략가능
		this.setView("/WEB-INF/views/student/studentEnroll.jsp");
	}

}

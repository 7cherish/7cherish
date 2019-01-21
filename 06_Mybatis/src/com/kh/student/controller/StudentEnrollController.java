package com.kh.student.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;

public class StudentEnrollController extends AbstractController{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		this.setRedirect(false); // 기본값 생략가능
		this.setView("/WEB-INF/views/student/studentEnroll.jsp");
	}

}

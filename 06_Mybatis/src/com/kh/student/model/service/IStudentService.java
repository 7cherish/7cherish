package com.kh.student.model.service;

import java.util.Map;

import com.kh.student.model.vo.Student;

public interface IStudentService {
	// 오버로딩. 동일한 메소드명. 다른 파라미터 타입
	int insertStudent(Student s);

	int insertStudent(Map<String, String> map);

	
	
}

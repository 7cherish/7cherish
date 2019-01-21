package com.kh.student.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.student.model.vo.Student;

public interface IStudentDao {
	
	// 오버로딩. 동일한 메소드명. 다른 파라미터
	int insertStudent(SqlSession session, Student s);

	int insertStudent(SqlSession session, Map<String, String> map);

}

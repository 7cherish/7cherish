package com.kh.student.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.student.model.vo.Student;

public interface IStudentDao {
	
	// 오버로딩. 동일한 메소드명. 다른 파라미터
	int insertStudent(SqlSession session, Student s);

	int insertStudent(SqlSession session, Map<String, String> map);

	int selectStudentCount(SqlSession session);

	String selectStudentName(SqlSession session, int studentNo);

	int deleteStudent(SqlSession session, int studentNo);

	Student selectOneStudent(SqlSession session, int studentNo);

	List<Student> selectList(SqlSession session);

	List<Map<String, String>> selectMapList(SqlSession session);

}

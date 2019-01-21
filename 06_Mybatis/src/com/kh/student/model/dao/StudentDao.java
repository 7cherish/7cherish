package com.kh.student.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.student.model.vo.Student;

public class StudentDao implements IStudentDao {

	@Override
	public int insertStudent(SqlSession session, Student s) {
		int result = session.insert("student.insertStudent1" , s); //student(네임스페이스 ) 쿼리를 찾아가 s를 실행하고 insert문을 찾아가봐라
		
		return result;
	}

	@Override
	public int insertStudent(SqlSession session, Map<String, String> map) {
		int result = session.insert("student.insertStudent2" , map); 
		
		return result;
	}

}

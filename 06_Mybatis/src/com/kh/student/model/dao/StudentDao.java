package com.kh.student.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.student.model.vo.Student;

public class StudentDao implements IStudentDao {
	// 오버로딩. 동일한 메소드명. 다른 파라미터 타입
	@Override
	public int insertStudent(SqlSession session, Student s) {
		// DML에 관련된 메소드들을 지원한다. (미리 만들어져 있다.) 
		// 쿼리를 찾아가기 위한 쿼리에 대한 고유 아이디값(네임스페이스, student)을 적어준다.
		// 클래스 객체 s를 가지고 쿼리를 찾아가서 insert문을 실행해봐라
		
//		int org.apache.ibatis.session.SqlSession.insert(String arg0, Object arg1)
		// String arg0은 특정 매퍼 파일의 특정쿼리태그를 가리킨다.
		// Object arg1은 없거나 하나만 가져온다.
		// 여러 개를 넘기고 싶으면 Map에 담아서 하나의 객체만 넘겨야 한다.
		// student = mapper의 namespace
		// insertStudent1 = insert태그의 id
		int result = session.insert("student.insertStudent1", s);

		return result;
	}

	@Override
	public int insertStudent(SqlSession session, Map<String, String> map) {
		int result = session.insert("student.insertStudent2", map);
		System.out.println("result@StudentDao = " + result);

		return result;
	}

	@Override
	public int selectStudentCount(SqlSession session) {
		// session.selectOne인 이유는 결과값이 하나이기 때문이다.
		// 즉 하나의 행이라는 것
		// 학생 한 명의 정보를 읽어오는 것도 selectOne
		int count = session.selectOne("student.selectStudentCount");
		System.out.println("count@StudentDao = " + count);
		return count;
	}

	@Override
	public String selectStudentName(SqlSession session, int studentNo) {
		String studentName = session.selectOne("student.selectStudentName", studentNo);
		System.out.println("studentName@StudentDao = " + studentName);

		return studentName;
	}

	@Override
	public int deleteStudentNo(SqlSession session, int studentNo) {
		int result = session.delete("student.deleteStudent", studentNo);
		System.out.println("deleteStudentNo@StudentDao = " + result);

		return result;
	}

}

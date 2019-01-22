package com.kh.student.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.common.SqlSessionTemplate;
import com.kh.student.model.dao.IStudentDao;
import com.kh.student.model.dao.StudentDao;
import com.kh.student.model.vo.Student;

public class StudentService implements IStudentService {
	// 인터페이스로써 실제 구현객체를 다루는 것을 디자인 패턴 중 전략패턴이라고 한다.
	/* cf) 전략패턴이란?
	 * 알고리즘 인터페이스를 정의하고, 
	 * 각각의 알고리즘 클래스별로 캡슐화하여 각각의 알고리즘을 교체 사용 가능하게한다.
	 * 즉, 하나의 결과를 만드는 목적은 동일하나 그 목적을 달성할 수 있는 방법(전략, 알고리즘)이 여러가지 존재할 경우 
	 * 기본이 되는 가장 많이 사용되는 패턴 중 하나이다.
	 * 
	 * ex)
	 * 같은 인터페이스 구현해야할 메소드를 만들지만 a회사 b회사에 맞게 구현
	 * 그럼에도 불구하고 controller에는 변함이 없다.
	 */

	IStudentDao studentDao = new StudentDao();

	@Override
	public int insertStudent(Student s) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = studentDao.insertStudent(session, s);
		
		// 트랜잭션 처리
		// SqlSessionTemplate에서 autoCommit을 막아놔서 하위 코드를 작성한 의미가 있다. 
		// session = factory.openSession(false);
		// true로 하면 무조건 commit된다.
		if (result > 0) {
			session.commit();
		}
		else {
			session.rollback();
		}
		
		// 사용한 세션 반납
		session.close();

		return result;
	}

	@Override
	public int insertStudent(Map<String, String> map) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = studentDao.insertStudent(session, map);
		
		// DML이므로 트랜잭션 처리
		if (result > 0) {
			session.commit();
		}
		else {
			session.rollback();
		}

		return result;
	}

	@Override
	public int selectStudentCount() {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int count = studentDao.selectStudentCount(session);
		
		// 사용한 세션은 반드시 반납해줘야 한다.
		// 사용한 세션 반납
		session.close();
		
		return count;
	}

	@Override
	public String selectStudentName(int studentNo) {
		String studentName = "";
		
		SqlSession session = SqlSessionTemplate.getSqlSession();
		studentName = studentDao.selectStudentName(session, studentNo);
		
		// 사용한 세션 반납
		session.close();
		
		return studentName;
	}

	@Override
	public int deleteStudentNo(int studentNo) {
		
		int result = 0;
		
		SqlSession session = SqlSessionTemplate.getSqlSession();
		result = studentDao.deleteStudentNo(session, studentNo);
		
		// DML이므로 트랜잭션 처리
		if (result > 0) {
			session.commit();
		}
		else {
			session.rollback();
		}
		
		// 사용한 세션 반납
		session.close();
		
		return result;
	}

	public Student selectOneStudent(int studentNo) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		
		Student s = studentDao.selectOneStudent(session, studentNo);
		
		// 사용한 세션 반납
		session.close();
		
		return s;
	}

	@Override
	public List<Student> selectList() {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		
		List<Student> list = studentDao.selectList(session);
		
		// 사용한 세션 반납
		session.close();
		return list;
	}

	@Override
	public List<Map<String, String>> selectMapList() {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		
		List<Map<String, String>> mapList = studentDao.selectMapList(session);

		// 사용한 세션 반납
		session.close();
		
		return mapList;
	}

}

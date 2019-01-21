package com.kh.student.model.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.common.SqlSessionTemplate;
import com.kh.student.model.dao.IStudentDao;
import com.kh.student.model.dao.StudentDao;
import com.kh.student.model.vo.Student;

public class StudentService implements IStudentService {
	// 인터페이스에서 제어하고 객체만 바꿔주면 밑에서는 동일하게 사용할수 있는 것을 디자인 패턴중 전략패턴이라 한다.
	// 같은 인터페이스 구현해야할 메소드를 만들지만 a회사 b회사에 맞게 구현
	// 그럼에도 불구하고 controller에는 변함이 없다.

	IStudentDao studentDao = new StudentDao();

	@Override
	public int insertStudent(Student s) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = studentDao.insertStudent(session, s);

		// 템플릿에서 오토커밋을 막아놔서 가능한것이다. session = factory.openSession(false);
		if (result > 0)
			session.commit();
		else
			session.rollback();

		return result;
	}

	@Override
	public int insertStudent(Map<String, String> map) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = studentDao.insertStudent(session, map);

		if (result > 0)
			session.commit();
		else
			session.rollback();

		return result;
	}

}

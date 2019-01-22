package com.kh.student.model.service;

import java.util.List;
import java.util.Map;

import com.kh.student.model.vo.Student;
/*
 *  인터페이스는 그 인터페이스를 구현한 클래스를 어떻게 조작할 것인가를 규정한다. 
 *  그렇기 때문에 외부에서 제어 할 수 있는 가장 개방적인 접근 제어자인 public만을 허용한다. 
 *  public을 생략하면 접근 제어자 default가 되는 것이 아니라 public이 된다. 
 *  왜냐하면 인터페이스의 맴버는 무조건 public이기 때문이다.
 * */
public interface IStudentService {
	// 오버로딩. 동일한 메소드명. 다른 파라미터 타입
	int insertStudent(Student s);

	int insertStudent(Map<String, String> map);

	int selectStudentCount();

	String selectStudentName(int studentNo);

	int deleteStudent(int studentNo);
	
	Student selectOneStudent(int studentNo);
	
	List<Student> selectList();

	List<Map<String, String>> selectMapList();

	
	
}

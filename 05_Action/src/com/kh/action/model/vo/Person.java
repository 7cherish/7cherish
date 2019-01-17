package com.kh.action.model.vo;
/**
 * <pre>
 * 자바빈(JavaBean) : 
 * 개발자코드(new 생성자)에 의해 생성되지 않고, 
 * 컨테이너가 관리하는 자바 객체이다. 
 * 
 * 조건 :
 * 1. 반드시 기본 생성자가 있어야 한다. 
 * (기본 생성자를 JVM이 자동으로 안 만들어줄때가 파라미터 생성자를 우리가 직접 만들었을때인데,
 * 파라미터 생성자를 만들었을때 반드시 기본 생성자를 만들어줘야 한다.)
 * 
 * 2. 동일한 이름을 가진 getter/setter 메소드가 있어야 한다. => 이 메소드로부터 참조하기 때문이다.
 * 			- property 속성명은 get/set을 제외한 
 * 			  나머지 단어의 첫 글자를 소문자로 바꾼 이름이다.
 * 3. setter매개변수타입과 getter메소드의 리턴타입이 동일해야한다.
 * 4. 만약 property(필드같은것)타입이 String/기본형이 아니라면 표준액션에서 사용할 수 없다. 
 * 			- 이런 경우 스크립틀릿을 사용할 것. => 스크립틀릿보다 표현할 수 있는 범위가 작다.
 *</pre>
 */
public class Person {
	private String name; // String 기본값 : null
	private char gender; // char 기본값 : 공백
	private int age; // int 기본값 : 0
	
	public Person() {
		super();
	}

	public Person(String name, char gender, int age) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "[name=" + name + ", gender=" + gender + ", age=" + age + "]";
	}
	
	

}

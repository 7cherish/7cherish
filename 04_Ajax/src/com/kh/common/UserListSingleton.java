package com.kh.common;

import java.util.ArrayList;
import java.util.List;

import com.kh.model.vo.User;

public class UserListSingleton {
	private static UserListSingleton instance; // UserListSingleton 타입의 객체가 단 하나만 만들어져서 저장될 곳
	private List<User> userList;
	
	// private 생성자
	// 생성자를 호출하면 데이터가 쌓인다.
	private UserListSingleton() {
		userList = new ArrayList<>();
		userList.add(new User("honggd", "홍길동", "서울시 동작구"));
		userList.add(new User("sinsa", "신사임당", "서울시 관악구"));
		userList.add(new User("dangoon", "단군", "경기도 평택"));
		userList.add(new User("sesese", "세종대왕", "서울시 서초구"));
		userList.add(new User("yeon", "연산군", "서울시 강북구"));
		userList.add(new User("jung_master", "정조", "경기도 남양주"));
		userList.add(new User("taeyo", "태종", "경기도 일산"));
		userList.add(new User("miss_hwang", "황진이", "대전시 유성구"));
		userList.add(new User("young", "영조", "경기도 구리시"));
		userList.add(new User("nononon", "논개", "서울시 마포구"));
	}
	
	public static UserListSingleton getInstance() {
		if(instance == null) {
			// 인스턴스가 없으면 새로 만들어라
			instance = new UserListSingleton();
		}
		
		return instance;
		
	}
	
	// 유저리스트를 돌려주는 리스트
	public List<User> getUserList(){
		return userList;
	}
}

package com.kh.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractController {
	/*
	 * 메모리 스택 영역에는 초기화 영역이 없다. 
	 * 지역변수는 반드시 초기화해서 써야 한다. 
	 * 그와 달리 힙, 스태틱 영역은 변수를 선언하기만 하면
	 * 이 데이터 타입에 맞는 0에 해당하는 값을 자동 세팅해준다. 
	 * 메모리 영역별로 특성이 다르다. 스택은 초기화를 직접 해줘야 하고, 
	 * 힙이랑 스택은 자동 초기화 된다. 
	 * 불린에서 0에 해당하는 값이 false이다. 
	 * char는 '', 참조형 변수들은 null이다.
	 */
	private boolean isRedirect; // 리다이렉트. 기본값 false
	private String view; // 뷰페이지. 기본값 null

	public AbstractController() {
		super();
	}

	public AbstractController(boolean isRedirect, String view) {
		super();
		this.isRedirect = isRedirect;
		this.view = view;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	// controller클래스에서 구현해야 할 추상메소드 생성
	public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;

}

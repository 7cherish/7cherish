package com.kh.common.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounterListener implements HttpSessionListener {
	// 세션 수 카운트하기 위해 필드 선언
	public static int activeSessions = 0;
	
	private void printSessionCount() {
		System.out.println("[현재 접속자 수 : " + activeSessions + "]");
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// 서버 입장에서 세션이 생기면 호출됨
		activeSessions++;
//		printSessionCount();
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// 서버 입장에서 세션이 반납됐을때 호출됨
		activeSessions--;
//		printSessionCount();
	}

}

package com.kh.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodeFilter implements Filter {
	
	/*
	 * init(FilterConfig config);
	 * 웹 컨테이너가 필터를 호출할 경우 해당 메소드가 호출되어
	 * 필터 객체를 생성하며 초기화한다.
	 * 매개변수 FilterConfig는 web.xml에 있는 <filter>정보를 가지고 있음
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
	
	/*
	 * doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
	 * 필터가 수행될 때 구동하는 메소드로, 요청 객체와 응답 객체를 사용해
	 * 일련의 작업을 수행한 뒤, chain을 통해 가공된 값을 목적지로 전송한다.
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 1. encoding처리
		request.setCharacterEncoding("utf-8");
		// 왔다 갔는지 확인하는 용
		System.out.println("utf-8 인코딩 처리됨@EncodeFilter");
		
		// (*중요*)
		chain.doFilter(request, response);
	}
	
	/*
	 * destroy();
	 * 역할이 끝난 필터는 웹 컨테이너에 의해 해당 메소드를 호출하고
	 * 소멸된다.
	 */
	@Override
	public void destroy() {

	}

}

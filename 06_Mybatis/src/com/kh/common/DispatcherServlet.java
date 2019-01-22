package com.kh.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
// 보내주는 Servlet
// 모든 요청을 받아야 하기 때문에 url매핑이 중요하다. 
// .do확장자를 가진 모든 요청을 처리하겠다.
// 실제 업무처리는 컨트롤러 클래스가 담당한다.
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// command.properties를 읽어서 메모리상에 보관
	Properties prop = new Properties();

	// Controller Class객체를 담아둘 Map객체 선언
	Map<String, Object> cmdMap = new HashMap<>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DispatcherServlet() {
		super();
	}

	/**
	 * WAS 구동시 딱 한 번, Servlet 생성시에만 호출된다.
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		String fileName = DispatcherServlet.class.getResource("/command.properties").getPath();

		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * Enumeration 인터페이스란?
		 * Enumeration 인터페이스는 객체들의 집합에서 각각의 객체들을 한순간에 하나씩 처리할 수 있는 메소드를 제공한다.
		 * boolean hasMoreElements() - 요소가 있으면 true, 없으면 false를 반환
		 * Object nextElement() - 다음 요소를 반환 
		 */
		
		// 실제 Controller Class객체를 생성 후 Map에 보관
		Enumeration<Object> keyen = prop.keys(); // 리턴형이 열거형
		// 키가 몇 개인지 모르겠지만 여러 개라 가정하고, 반복문을 돌린다.
		// 요소가 더 있으면 반복한다.
		while (keyen.hasMoreElements()) {
			// 리턴형이 Object이므로 String으로 형변환해준다.
			String key = (String) keyen.nextElement();
			String className = prop.getProperty(key).trim();

			// 클래스 네임이 널이 아니면서 클래스네임 값이 비어있지 않다면
			if (className != null && !className.isEmpty()) {
				// 컨트롤러 클래스 객체 생성
				// 클래스 객체를 먼저 만들어두고 설계도를 보고
				// 그 객체를 따라서 실제 클래스 객체를 만드는 것이다.
				Class cls = null;
				Object controller = null;

				try {
					// 클래스 객체에 패키지를 포함한 실제 클래스 이름을 주게 되면 클래스 객체가 만들어지면서 처리된다.
					cls = Class.forName(className);
					// 클래스 객체로부터 실제 일반객체를 만드는 방법이
					// newInstance()로 처리하는 것이다.
					controller = cls.newInstance();

					// properties에서 읽어온 문자를 key값
					cmdMap.put(key, controller);

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) { // 객체화
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

			}
		}
		System.out.println("최초 생성 : cmdMap@DispatcherServlet.init = " + cmdMap);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// http://localhost:9090/mybatis/student/studentEnroll.do?name=이순신
		// 위처럼 요청이 왔을때 /student/studentEnroll.do 이 부분을 가지고
		// 맵에 저장되어있을것을 찾아서 처리해줘야 한다.

		// uri걸러내기
		String uri = request.getRequestURI(); // /mybatis/student/studentEnroll.do
		String ctxName = request.getContextPath(); // /mybatis
		String urlKey = uri.substring(ctxName.length()); // /student/studentEnroll.do

		// cmdMap에 담긴 Object가 리턴된다.
		// 그것을 AbstractConroller로 형변환해서 사용한다.
		AbstractController controller = (AbstractController) cmdMap.get(urlKey);

		// 컨트롤러 분기
		// 준비되지 않은 url을 요청했을 경우
		if (controller == null) {
			System.out.println("[" + urlKey + "]에 매핑된 컨트롤러 객체가 없습니다.");
		} else {
			try {
				controller.execute(request, response);

				boolean isRedirct = controller.isRedirect();
				String view = controller.getView();

				// 1. redirect
				if (isRedirct) {
					// 리다이렉트 할 경우
					response.sendRedirect(view);
				}

				// 2. forwarding
				else if (view != null) {
					// 포워딩 할 경우
					request.getRequestDispatcher(view).forward(request, response);
				}

				// 3. json 등 출력스트림에 직접 쓴 경우
				else {

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

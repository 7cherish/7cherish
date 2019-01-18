<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>el : Object</title>
</head>
<body>
	<h2>el : 그밖의 객체</h2>
	<h3>cookie</h3>
	<!-- 브라우저에서 접속하면 sessionId가 자동으로 생성된다. -->
	쿠키에서 가져온 sessionId값 : ${cookie.JSESSIONID.value} <br />
	
	<!-- null에 대해 getValue하고 있는 것이다. -->
	<!-- NullPointerException 이어야 하는데 EL은 알려주지 않는다. (NULL처리 관대) -->
	존재하지 않는 쿠키값 요청 : [${cookie.saveId.value}] <br />
	
	<h3>header</h3>
	<!-- 서블릿에서 request.getHeader("속성명") 했었는데 -->
	<p>request.getHeader("속성명")</p>
	
	<!-- host : 요청주소 -->
	host : ${header["host"] } <br />
	
	<!-- 맵객체로 갖다쓰는것이다. -->
	host : ${header.host } <br />
	
	<!-- 특수문자가 포함된 경우 도트연산자 사용불가. "문자열"로 속성명 전달 -->
	user-agent : ${header["user-agent"]} <br />
	user-agent : ${header.user-agent} <br />
	
	<h3>실제 request객체(HttpServletRequest)에 접근하는 방법</h3>
	<p>
	requestScope는 request생존범위에 속한 속성의 key, value를 Map으로 바인딩한 것이다. <br />
	실제 request객체가 아니다. <br />
	EL의 pageContext를 통해서 jsp의 pageContext에 접근할 수 있다. <br />
	(메소드를 통해 실제 객체에 접근할 수 있다.) <br />
	</p>
	
	<p>jsp의 내장객체 pageContext가 가진 메소드</p>
	<ul>
		<li>getErrorData()</li>
		<li>getPage()</li>
		<li>getRequest()</li>
		<li>getResponse()</li>
		<li>getServletConfig()</li>
		<li>getServletContext()</li>
		<li>getSession()</li>
	</ul>
	<!-- 위의 메소드 get뒤의 것 소문자로 바꿔서 호출 -->
	<!-- pageContext.request -->
	<p>요청메소드</p>
	<ul>
		<li>scriptlet : <%=request.getMethod() %></li>
		<li>el : ${pageContext.request.method }</li>
	</ul>
	
	<p>ContextPath 가져오는 방법</p>
	<ul>
		<li>scriptlet : <%=request.getContextPath() %></li>
		<li>el : ${pageContext.request.contextPath}</li>
	</ul>
</body>
</html>
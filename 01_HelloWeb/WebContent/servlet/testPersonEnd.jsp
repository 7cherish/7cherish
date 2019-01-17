<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// java코드영역
	// jsp에는 request, response라는 내장객체가 존재한다.
	// return값이 Object이기 때문에 형변환이 필수이다.
	// request객체 속성에 담아둔 변수 추출
	String name = (String) request.getAttribute("name");
	String color = (String) request.getAttribute("color");
	String animal = (String) request.getAttribute("animal");
	String food = (String) request.getAttribute("food");
	String recommendation = (String) request.getAttribute("recommendation");

	System.out.printf("jsp에서 출력 >> %s %s %s %s %s\n", 
					name, color, animal, food, recommendation);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인취향검사 - jsp</title>
</head>
<body>
	<%-- 표현식을 통한 변수값 출력 --%>
	<!-- html주석 -->
	<h2>개인취향검사 - jsp</h2>
	<h4><%=name %></h4>
	<h4><%=color %></h4>
	<h4><%=animal %></h4>
	<h4><%=food %></h4>
	<h3>오늘 <%=recommendation %> 어떠세요?</h3>
</body>
</html>
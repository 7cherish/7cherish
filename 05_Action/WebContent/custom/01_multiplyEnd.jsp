<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- uri : c.tld파일의 uri와 일치하도록 할 것 
(WebAppLibraries / taglibs-standard-impl / META-INF / maven / c.tld (이건 core꺼) -->
<!-- prefix : 사용할때 쓸 이름. 주로 core라서 c라 한다. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>두 수를 입력받아 곱셈결과 출력</title>
</head>
<body>
	<h2>CustomAction - JSTL</h2>
	<!-- 커스텀액션의 이름이 JSTL이다. -->
	<p>JSTL - JSP Standard Tag Library</p>
	<p>
	자바코드와 HTML코드를 섞어서 사용할 때 <br />
	자바코드를 HTML태그처럼 사용할 수 있도록 지원해 주는 라이브러리 <br />
	</p>
	<!-- 1. 변수 선언 -->
	<!-- 
		var : 변수명
		value : 값
		page : 생명줄  
	-->
	<!-- 파라미터로 넘어온 num1 값을 받아서 이 c태그에서 다시 no1으로 선언한다. -->
	<c:set var="no1" value="${param.num1}" scope="request" />
	<c:set var="no2" value="${param.num2}" scope="request" />
	<%-- <c:set var="result" value="${param.num1 * param.num2}" scope="request" /> --%>
	<c:set var="result" value="${no1 * no2}" scope="request" />
	
	<h3>계산</h3>
	${no1}와 ${no2}의 곱은? ${result}
	<br />
	<c:out value="${no1}" />와
	<c:out value="${no2}" />의 곱은?
	<c:out value="${result}" />
	
</body>
</html>
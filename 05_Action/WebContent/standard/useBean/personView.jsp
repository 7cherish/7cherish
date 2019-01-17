<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8 " />
<title>jsp:useBean</title>
</head>
<body>
	<h2>jsp:useBean</h2>
	<!-- id : 속성명 -->
	<!-- class : 패키지포함 전체 이름 -->
	<!-- scope : 빈의 생존범위. 
	page(이 jsp파일 안에서만) < request(이번 사용자 요청동안) < session(이번 사용자 세션동안) < application(서버프로그램이 시작되고 끝날때까지) -->
	<!-- request객체의 속성에서 Person타입의 person이 존재하면 가져오고, 없으면 새로 생성한다. -->
	<!-- 자바스크립틀릿과 동일하게 Server-side에서 처리된다. 액션태그, EL도 마찬가지이다.-->
	<!-- 브라우저는 jsp액션태그를 볼 수 없다. 값으로 치환되서 넘어오기 때문이다. -->
	<!-- HTML만 브라우저에서 해석한다. -->
	<!-- 브라우저에서 소스보기를 할 때 보이는게 브라우저가 받은 데이터이다. -->
	<jsp:useBean id="person" class="com.kh.action.model.vo.Person" scope="request"></jsp:useBean>

	<!-- property : 클래스의 필드. get|set 뒤의 것 대문자 말고 소문자로 작성 -->
	<!-- name : useBean의 id값 -->
	성명 : <jsp:getProperty property="name" name="person" /> <br /> 
	성별 : <jsp:getProperty property="gender" name="person" /> <br /> 
	나이 : <jsp:getProperty property="age" name="person" />
	<hr />

	<!-- request속성에 없는 빈생성 -->
	<jsp:useBean id="p" class="com.kh.action.model.vo.Person" scope="page"></jsp:useBean>

	<!-- property값 설정 : setProperty -->
	<jsp:setProperty property="name" name="p" value="김기리" />
	<jsp:setProperty property="gender" name="p" value="남" />
	<jsp:setProperty property="age" name="p" value="30" />

	<hr />
	<!-- property값 출력 : getProperty -->
	<!-- setProperty를 해주지 않으면 해당 필드의 기본값이 출력된다. -->
	성명 : [<jsp:getProperty property="name" name="p" />] <br /> 
	성별 : [<jsp:getProperty property="gender" name="p" />] <br /> 
	나이 : [<jsp:getProperty property="age" name="p" />]
	
	<%-- 	
	표준액션과 커스텀액션으로 나뉘는데,
	<jsp:  이렇게 시작하는게 표준액션이고(라이브러리가 필요없다.),
	<c:, <fmt: 로 시작하는 것은 라이브러리가 필요하다. 
	--%>
	
</body>
</html>
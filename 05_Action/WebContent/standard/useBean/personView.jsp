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
	<!-- class : 패키지명 -->
	<!-- scope : 빈의 생존범위. page < request < session < application -->
	<!-- request객체의 속성에서 Person타입의 person이 존재하면 가져오고, 없으면 새로 생성한다. -->
	<!-- 스크립틀릿과 동일하게 Server-side에서 처리된다. 액션태그 EL도 마찬가지이다.-->
	<jsp:useBean id="person" class="com.kh.action.model.vo.Person" scope="request"></jsp:useBean>

	<!-- property : get|set 뒤의 것 대문자 말고 소문자로 작성 -->
	<!-- name : useBean의 id값 -->
	성명 : <jsp:getProperty property="name" name="person" /> <br /> 
	성별 : <jsp:getProperty property="gender" name="person" /> <br /> 
	나이 : <jsp:getProperty property="age" name="person" />

	<!-- request속성에 없는 빈생성 -->
	<jsp:useBean id="p" class="com.kh.action.model.vo.Person" scope="page"></jsp:useBean>

	<!-- property값 설정 : setProperty -->
	<jsp:setProperty property="name" name="p" value="김기리" />
	<jsp:setProperty property="gender" name="p" value="남" />
	<jsp:setProperty property="age" name="p" value="30" />

	<hr />
	<!-- property값 출력 : getProperty -->
	성명 : [<jsp:getProperty property="name" name="p" />] <br /> 
	성별 : [<jsp:getProperty property="gender" name="p" />] <br /> 
	나이 : [<jsp:getProperty property="age" name="p" />]
	
</body>
</html>
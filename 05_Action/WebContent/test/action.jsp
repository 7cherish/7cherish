<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입결과</title>
</head>
<body>
	<h2>회원가입결과(action)</h2>
	<jsp:useBean id="person" class="com.kh.action.model.vo.Person" scope="request"></jsp:useBean>
	<ul>
		<li>성명 : <jsp:getProperty property="name" name="person"/> </li>
		<li>성별 : <jsp:getProperty property="gender" name="person"/></li>
		<li>나이 : <jsp:getProperty property="age" name="person"/></li>
	</ul>
</body>
</html>
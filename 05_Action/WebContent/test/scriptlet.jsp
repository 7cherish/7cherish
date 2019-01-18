<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,
				 com.kh.action.model.vo.*" %>
<%
	Person person = (Person) request.getAttribute("person");
	String name = person.getName();
	char gender = person.getGender();
	int age = person.getAge();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입결과</title>
</head>
<body>
	<h2>회원가입결과(scriptlet)</h2>
	<ul>
		<li>성명 : <%=name %> </li>
		<li>성별 : <%=gender %></li>
		<li>나이 : <%=age %></li>
	</ul>
</body>
</html>
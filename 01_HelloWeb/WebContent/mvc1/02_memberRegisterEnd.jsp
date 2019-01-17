<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ page import="member.model.vo.Member,
				 member.model.service.MemberService" %>
<%
	// 1. request객체 Encoding처리
	request.setCharacterEncoding("utf-8");
	
	// 2. parameter 핸들링
	String memberId = request.getParameter("memberId");
	String password = request.getParameter("password");
	String memberName = request.getParameter("memberName");
	int age = Integer.parseInt(request.getParameter("age"));
	String gender = request.getParameter("gender");
	String email = request.getParameter("email");
	String phone = request.getParameter("phone");
	String address = request.getParameter("address");
	String[] hobbyArr = request.getParameterValues("hobby");
	String hobby = String.join(",", hobbyArr);
	
	System.out.printf("[%s %s %s %s %s %s %s %s %s]\n",
						memberId, 
						password, 
						memberName, 
						age, 
						gender, 
						email, 
						phone, 
						address, 
						hobby);
	
	// 3. 업무(비지니스)로직 : 회원가입 => service단에 회원가입요청
	Member m = new Member(memberId, 
			              password, 
			              memberName, 
			              gender, 
			              age, 
			              email, 
			              phone, 
			              address, 
			              hobby,
			              null);
	
	// 서비스단에 업무요청
	int result = new MemberService().insertMember(m);
			              
	// 4. view단 처리
	String msg = result > 0? "성공적으로 회원가입하셨습니다.":"회원가입에 실패했습니다.";
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 결과</title>
</head>
<body>
	<h2>회원가입 결과</h2>
	<h3><%=msg %></h3>
	
</body>
</html>
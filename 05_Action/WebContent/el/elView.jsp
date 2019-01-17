<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.kh.action.model.vo.*" %>
<%-- <%
	Person person = (Person) request.getAttribute("person");
	String name = person.getName();
	char gender= person.getGender();
	int age = person.getAge();
	
	String coffee = (String)request.getAttribute("coffee");
	int year = (int)request.getAttribute("year");
	List<String> items = (List<String>)request.getAttribute("items");
%> --%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>el</title>
</head>
<body>
	<h2>EL(Expression Language)</h2>
	<!-- 
	EL에서 사용가능한 내장객체
	괄호 사용한건 생략 가능
	* (pageScope)
	* (requestScope) : 
		servlet에서 작성한 HttpServletRequest객체가 아니라, (pageContext제외하고) 
		단지 EL에서 만들어서 사용하는 Map객체이다.
	* (sessionScope)
	* (applicationScope)
	
	* param
	* paramValues
	
	* header
	* headerValues
	
	* cookie
	
	* initParam : 어플리케이션 전체에서 공유하는 속성
	* pageContext : jsp의 pageContext객체
	 -->
	<%-- 	
	커피:  <mark><%=coffee %></mark> <br />
	올해년도 : <mark><%=year %></mark> <br />
	<h3>사람정보</h3>
	이름 : <mark><%=name %></mark><br />
	성별 : <mark><%=gender %></mark><br />
	나이 : <mark><%=age %></mark><br />
	<h3>아이템정보</h3>
	아이템1 : <mark><%=items.get(0) %></mark> --%>
	<hr />
	
	<%-- 	
	커피:  <mark>${requestScope.coffee }</mark> <br />
	올해년도 : <mark>${requestScope.year}</mark> <br />
	<h3>사람정보</h3>
	이름 : <mark>${requestScope.person.name}</mark><br />
	성별 : <mark>${requestScope.person.gender}</mark><br />
	나이 : <mark>${requestScope.person.age}</mark><br />
	<h3>아이템정보</h3>
	아이템1  : <mark>${requestScope.items[0]}</mark> --%>
	<hr />
	
	<!-- 	
	EL은 다음 순으로 속성을 검색한다.
	pageScope -> requestScope -> sessionScope -> applicationScope 
	-->
	커피:  <mark>${ coffee }</mark> <br />
	올해년도 : <mark>${ year}</mark> <br />
	<h3>사람정보</h3>
	이름 : <mark>${ person.name}</mark><br />
	성별 : <mark>${ person.gender}</mark><br />
	나이 : <mark>${ person.age}</mark><br />
	<h3>아이템정보</h3>
	아이템1 : <mark>${ items[0]}</mark>
	<hr />
	<!-- EL은 null에 대해 관대하다. -->
	스크립틀릿 request : <%=(String)request.getAttribute("coffee") %> <br />
	request : ${requestScope.coffee } <br />
	session : ${sessionScope.coffee } <br />
	application : ${applicationScope.coffee } <br />
</body>
</html>
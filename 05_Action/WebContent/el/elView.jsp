<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.kh.action.model.vo.*" %>
<%-- 
<%
	Person person = (Person) request.getAttribute("person");
	String name = person.getName();
	char gender= person.getGender();
	int age = person.getAge();
	
	String coffee = (String)request.getAttribute("coffee");
	int year = (int)request.getAttribute("year");
	List<String> items = (List<String>)request.getAttribute("items");
%> 
--%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>el</title>
</head>
<body>
	<h2>EL(Expression Language)</h2>
	다들 바뀌나 테스트
	<!-- 
	EL에서 사용가능한 내장객체
	괄호로 묶어놓은 스코프는 생략 가능
	* (pageScope)
	* (requestScope) : 서블릿에서 request객체에 속성으로 저장해놨으면,
					   requestScope 내장객체를 통해서 속성명에 바로 접근이 가능하다.

					   servlet에서 작성한 HttpServletRequest객체가 아니라,
					   단지 EL에서 만들어서 사용하는 Map객체이다.
					   HttpServletRequest 속성들만 긁어와서 새로 Map으로 만든 것이다.
					   pageContext만 빼고 마찬가지이다.
	* (sessionScope)
	* (applicationScope)
	
	* param : 파라미터
	* paramValues
	
	* header : 요청헤더. 
			   사용자가 서버쪽에 요청을 실제 보낼때, 
			   헤더에 여러 정보가 담겨있는 것에 접근이 가능하다.
	* headerValues
	
	* cookie
	
	* initParam : 어플리케이션 전체에서 공유하는 속성
	* pageContext : 실제로 jsp의 pageContext객체. 이걸로 접근할 수 있는게 많다.
	 -->
	<%-- 	
	커피 :  <mark><%=coffee %></mark> <br />
	올해년도 : <mark><%=year %></mark> <br />
	<h3>사람정보</h3>
	이름 : <mark><%=name %></mark><br />
	성별 : <mark><%=gender %></mark><br />
	나이 : <mark><%=age %></mark><br />
	<h3>아이템정보</h3>
	아이템1 : <mark><%=items.get(0) %></mark> 
	--%>
	<hr />
	
	<%--
	<-- 빈 규약에 의해 .으로 나누어 사용한다. -->
	커피 :  <mark>${requestScope.coffee }</mark> <br />
	올해년도 : <mark>${requestScope.year}</mark> <br />
	<h3>사람정보</h3>
	이름 : <mark>${requestScope.person.name}</mark><br />
	성별 : <mark>${requestScope.person.gender}</mark><br />
	나이 : <mark>${requestScope.person.age}</mark><br />
	<h3>아이템정보</h3>
	아이템1  : <mark>${requestScope.items[0]}</mark> 
	--%>
	<hr />
	
	<!-- 	
	EL은 다음 순으로 속성(값이 있는지)을 검색한다.
	pageScope -> requestScope -> sessionScope -> applicationScope 
	-->
	커피 :  <mark>${coffee}</mark> <br />
	올해년도 : <mark>${year}</mark> <br />
	<h3>사람정보</h3>
	이름 : <mark>${person.name}</mark><br />
	성별 : <mark>${person.gender}</mark><br />
	나이 : <mark>${person.age}</mark><br />
	<h3>아이템정보</h3>
	아이템1 : <mark>${items[0]}</mark>
	<hr />
	<!-- EL과 스크립틀릿은 없는 속성에 대한 처리방식이 다르다. -->
	<!-- 현재 ElServlet에서 request setAttribute 주석처리해놓은 상태라 속성이 없다. -->
	<!-- EL은 null에 대해 관대(관용적)하다. -->
	<!-- EL은 NullPointerException이 떠도 에러가 안 난다. -->
	스크립틀릿 request : <%=(String)request.getAttribute("coffee") %> <br />
	request : ${requestScope.coffee } <br />
	session : ${sessionScope.coffee } <br />
	application : ${applicationScope.coffee } <br />
</body>
</html>
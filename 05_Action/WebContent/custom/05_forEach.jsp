<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!-- 
uri : c.tld파일의 uri와 일치하도록 할 것 
(WebAppLibraries / taglibs-standard-impl / META-INF / maven / c.tld (이건 core꺼) 
-->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String[] friends = {"곽경국", "정현빈", "한동준", "김보람", "김병선"};
/* 	배열이든 컬렉션이든 다 사용 가능하다.
	밑에서 사용하기 위해 pageContext에 담아둔다. */
	pageContext.setAttribute("friends", friends);

	List<String> list = new ArrayList<>();
	list.add("곽경국");
	list.add("정현빈");
	list.add("한동준");
	list.add("김보람");
	list.add("김병선");
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forEach</title>
</head>
<body>
	<h2>반복문</h2>
	<!-- var : 증감변수 -->
	<!-- begin : 시작 -->
	<!-- end : 끝 -->
	<!-- step : 기본값은 1, 2는 2씩 증가 -->
	<!-- items : el로 배열이나 리스트를 전달하면 된다 -->
	<!-- varStatus :  -->
<%-- 	
	${status.current} 현재 for문의 해당하는 번호
	${status.index} 0부터의 순서
	${status.count} 1부터의 순서
	${status.first} 첫 번째인지 여부
	${status.last} 마지막인지 여부
	${status.begin} for문의 시작 번호
	${status.end} for문의 끝 번호
	${status.step} for문의 증가값 
--%>



	<c:forEach var="i" begin="1" end="6" step="2">
		<h${i }>반복문 ${i }</h${i }>
	</c:forEach>
	
	<h2>친구출력</h2>
	<!-- 한 명씩 출력하기(배열) -->
	<c:forEach var="name" items="${friends }" varStatus="vs">
		${vs.index } : ${name} <br />
<%-- 		${vs.count } : ${name} <br />	 --%>
	</c:forEach>
	<hr />
	<!-- 리스트 출력 -->
	<c:forEach var="name" items="${list }" varStatus="vs">
<%-- 		${vs.index!=0?',':''}${name }       --%>
<%-- 		${vs.index!=0?",":""}${name } 		--%>
	<c:if test="${vs.index != 0 }">, </c:if>
	${name }
	</c:forEach>

</body>
</html>
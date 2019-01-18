<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
uri : c.tld파일의 uri와 일치하도록 할 것 
(WebAppLibraries / taglibs-standard-impl / META-INF / maven / c.tld (이건 core꺼) 
-->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03_ifStrEnd</title>
</head>
<body>
<%
	String str1 = request.getParameter("str1");
	String str2 = request.getParameter("str2");
%>
	<h2>문자열 비교결과</h2>
	<ul>
		<li>스크립틀릿 : <%=str1 == str2 %></li>
		<li>
			<c:if test="${param.str1 == param.str2 }">
				${param.str1 }과 ${param.str2 }는 같습니다.
			</c:if>
			<c:if test="${param.str1 != param.str2 }">
				${param.str1 }과 ${param.str2 }는 다릅니다.
			</c:if>
		</li>
		<li>
			<c:if test="${param.str1 eq param.str2 }">
				${param.str1 }과 ${param.str2 }는 같습니다.
			</c:if>
			<c:if test="${param.str1 ne param.str2 }">
				${param.str1 }과 ${param.str2 }는 다릅니다.
			</c:if>			
		</li>
		<li>
			<c:if test="${param.str1.equals(param.str2) }">
				${param.str1 }과 ${param.str2 }는 같습니다.
			</c:if>
			<c:if test="${!param.str1.equals(param.str2) }">
				${param.str1 }과 ${param.str2 }는 다릅니다.
			</c:if>			
		</li>
	</ul>

</body>

	
</html>
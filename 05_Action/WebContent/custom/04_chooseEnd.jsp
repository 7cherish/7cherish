<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 
uri : c.tld파일의 uri와 일치하도록 할 것 
(WebAppLibraries / taglibs-standard-impl / META-INF / maven / c.tld (이건 core꺼) 
-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>c : choose</title>
</head>
<body>
	<h2>경품지급</h2>
	<c:choose>
		<c:when test="${param.num1 % 5 == 0}">
			인형을 뽑았습니다.
		</c:when>
		<c:when test="${param.num1 % 5 == 1}">
			오토바이 장난감을 뽑았습니다.
		</c:when>
		<c:when test="${param.num1 % 5 == 2}">
			라이터를 뽑았습니다.
		</c:when>
		<c:otherwise>
			꽝입니다.
		</c:otherwise>
	</c:choose>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>if</title>
</head>
<body>
	<h2>숫자크기비교</h2>
	<!-- 
	전달된 parameter는 문자형이므로, 사전순 비교를 하게 된다.
	그러므로 형변환이 필수이다. 
	-->
	<c:if test="${Integer.parseInt(param.num1) > Integer.parseInt(param.num2)}">
		${param.num1 }이 ${param.num2 }보다 큽니다.
	</c:if>
	<c:if test="${Integer.parseInt(param.num1) < Integer.parseInt(param.num2)}">
		${param.num1 }이 ${param.num2 }보다 작습니다.
	</c:if>
	<c:if test="${Integer.parseInt(param.num1) == Integer.parseInt(param.num2)}">
		${param.num1 }과 ${param.num2 }가 같습니다.
	</c:if>

</body>
</html>
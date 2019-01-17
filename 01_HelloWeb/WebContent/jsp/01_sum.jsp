<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- jsp directive : page --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1부터 10까지 합 구하기</title>
</head>
<body>
<%
	// 스크립틀릿
	/* 자바코드 구간 */
	int total = 0;
	for(int i = 1; i <= 10; i++){
		total += i;
	}
	System.out.println("total = " + total);
%>
	<p>
		expression출력 : 합은 <span style="color:red;"><%=total %></span>입니다.
		<br /><br />
		<!-- 자바코드구간이기때문에 ;써줘야함 -->
		scriptlet출력 : 합은 <span style="color:blue;"><% out.println(total); %></span>입니다.
	</p>
</body>
</html>
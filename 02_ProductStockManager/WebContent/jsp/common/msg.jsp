<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	/* getAttribute는 Object이기 때문에 String으로 형변환해줘야한다. */
	String msg = (String)request.getAttribute("msg");
	String loc = (String)request.getAttribute("loc");
	System.out.printf("msg=%s, loc=%s\n", msg, loc);
%>
<script>
/* 하나의 문자열이기 때문에 " " 로 꼭 닫아줘야 한다. */
alert("<%=msg %>");
/* 반드시 DML요청후에는 페이지 이동을 시켜줘야 한다. */
location.href = "<%=request.getContextPath()+loc %>";
</script>
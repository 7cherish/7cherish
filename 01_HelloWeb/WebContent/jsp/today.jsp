<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%
	Date d = new Date();
	// String today = String.format("%tY/%tm/%td %tT", d, d, d, d);
	// 2018/12/04 20:00:00
	
	String today2 = String.format("%tY년 %tm월 %td일 %tA", d, d, d, d);
%>
<%-- 
<%=today%>
 --%>
<%=today2%>
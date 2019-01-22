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
<title>INDEX</title>
<style>
body{
	margin: 0 auto;
	text-align: center;
}

a{
	display: block;
	color: black;
	text-decoration: none;
	padding: 5px;
}

a:hover{
	color: red;
}
</style>
</head>
<body>
	<h1>INDEX</h1>
	<h2>student</h2>
	<a href="http://localhost:9090/mybatis/student/studentEnroll.do">학생 등록 : studentEnroll.do</a>
	<a href="http://localhost:9090/mybatis/student/selectOne.do">학생정보검색 : selectOne.do</a>
	<a href="http://localhost:9090/mybatis/student/selectList.do">학생리스트 : selectList.do</a>
	<hr />
	<h2>emp</h2>
	<a href="http://localhost:9090/mybatis/emp/search1.do">search1.do</a>
	
</body>
</html>
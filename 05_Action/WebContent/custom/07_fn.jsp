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
<title>07_fn</title>
</head>
<body>
	<h2>jstl : fn</h2>
	<c:set var="str" value="How are you?"/>
	str : ${str } <br />
	모두 대문자로 : ${fn:toUpperCase(str) } <br />
	모두 소문자로 : ${fn: toLowerCase(str) } <br />
	are의 index : ${fn: indexOf(str, "are") } <br />
	
</body>
</html>
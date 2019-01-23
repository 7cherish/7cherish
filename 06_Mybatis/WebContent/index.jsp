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
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<!-- <script type="text/javascript" src="/js/jquery.gdocsviewer.min.js"></script> -->
<script>
function showPdf(n){
	$(".pdf").eq(n-1).toggle();
}

</script>
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

.pdf{
	width: 90%;
	min-height: 500px;
	display: none;
}

.label{
	font-size: 20px;
	display: block;
	cursor: pointer;
	border: 1px solid;
	border-radius: 10px 10px 10px 10px;
	width: 400px;
	margin: 0 auto;
	margin-bottom: 20px;
	margin-top: 20px;
}

.label:hover{
	background: #48d2f9;
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
	<a href="http://localhost:9090/mybatis/emp/empList.do">empList.do</a>
	<hr />
	<h2>PDF</h2>
	<label for="pdf" class="label" onclick="showPdf(1);">1_Framework.pdf</label>
	<iframe id="framework" name="pdf" class="pdf" src="./수업자료/pdf/1_Framework.pdf"></iframe>
	<br />
	<label for="pdf" class="label" onclick="showPdf(2);">2_Mybatis.pdf</label>
	<iframe id="mybatis" name="pdf" class="pdf" src="./수업자료/pdf/2_Mybatis.pdf"></iframe>
	<br />
	<label for="pdf" class="label" onclick="showPdf(3);">3_Mybatis-동적쿼리.pdf</label>
	<iframe id="mybatis-query" name="pdf" class="pdf" src="./수업자료/pdf/3_Mybatis-동적쿼리.pdf"></iframe>
	<!-- <iframe src="http://docs.google.com/viewer?embedded=true&url=' + encodeURIComponent(file) + '" width="' + settings.width + '" height="' + settings.height + '" style="border: none;"></iframe> -->


</body>
</html>
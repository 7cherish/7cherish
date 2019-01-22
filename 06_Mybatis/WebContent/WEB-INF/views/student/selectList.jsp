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
<title>학생리스트</title>
<style>
div#student-container{
	text-align: center;
}

table.tbl-student{
	margin: 0 auto;
	border: 1px solid;
	border-collapse: collapse;
}

table.tbl-student th, table.tbl-student td{
	border: 1px solid;
	padding: 5px;
}
</style>
</head>
<body>
	<div id="student-container">
		<h2>학생리스트(Student)</h2>
		<table class="tbl-student">
			<tr>
				<th>학번</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>이메일</th>
				<th>주소</th>
				<th>등록일</th>
			</tr>
			<!-- list가 비어있지 않다면 -->
			<c:if test="${not empty list }">
			<!-- 리스트의 요소(<Student>)가 s에 담긴다. -->
				<c:forEach var="s" items="${list }">
				<tr>
					<td>${s.studentNo }</td>
					<td>${s.studentName }</td>
					<td>${s.studentTel }</td>
					<td>${s.studentEmail }</td>
					<td>${s.studentAddr }</td>
					<td>${s.regDate }</td>
				</tr>
				</c:forEach>
			</c:if>
			<!-- list가 비어있다면 -->
			<c:if test="${empty list }">
				<tr>
					<td colspan="6" align="center">
						등록된 학생이 없습니다.
					</td>
				</tr>
			</c:if>
		</table>
		
		<hr />
		
		<h2>학생리스트(Map)</h2>
		<table class="tbl-student">
			<tr>
				<th>학번</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>이메일</th>
				<th>주소</th>
				<th>등록일</th>
			</tr>
			<!-- list가 비어있지 않다면 -->
			<c:if test="${not empty mapList }">
			<!-- <Map<String, String>> mapList에서 요소를 하나 꺼내온다 => map -->
			<!-- 키값으로 밸류를 가져올 수 있다. -->
				<c:forEach var="m" items="${mapList }">
				<tr>
				<!-- 
				Map은 대소문자를 구분하기 때문에 대문자로 적어준다. 
				sql은 가리지 않는다. 
				student-mapper.xml 참조
				-->
					<td>${m.STUDENT_NO }</td>
					<td>${m.STUDENT_NAME }</td>
					<td>${m.STUDENT_TEL }</td>
					<td>${m.STUDENT_EMAIL }</td>
					<td>${m.STUDENT_ADDR }</td>
					<td>${m.REG_DATE }</td>
				</tr>
				</c:forEach>
			</c:if>
			<!-- list가 비어있다면 -->
			<c:if test="${empty mapList }">
				<tr>
					<td colspan="6" align="center">
						등록된 학생이 없습니다.
					</td>
				</tr>
			</c:if>
		</table>
	</div>

</body>
</html>
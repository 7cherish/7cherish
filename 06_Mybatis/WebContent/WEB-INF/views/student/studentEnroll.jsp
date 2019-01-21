<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 등록</title>
<style>
#enroll-container {
	text-align: center;
}

table#tbl-student {
	margin: 0 auto;
	border: 1px solid;
	border-collapse: collapse;
}

th, td {
	border: 1px solid;
	padding: 5px;
}

th {
	text-align: right;
}

td {
	text-align: left;
}

td:last-of-type {
	text-align: center;
}
</style>
</head>
<body>
	<div id="enroll-container">
		<h2>학생등록(Student vo)</h2>
		<!-- 
		/student/studentEnrollEnd.do를 command.properties에 키 밸류 형식으로 값을 정해준다.
		어느 클래스가 처리할 것인지 담아준다.
		 -->
		<form
			action="${pageContext.request.contextPath}/student/studentEnrollEnd.do"
			method="post">
			<table id="tbl-student">
				<tr>
					<th>학생이름</th>
					<td><input type="text" name="studentName" required /></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="tel" name="studentTel" maxlength="11" /></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" name="studentEmail" /></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" name="studentAddr" required /></td>
				</tr>
				<tr>

					<td colspan="2"><input type="submit" value="전송" /></td>
				</tr>
			</table>
		</form>

		<hr />

		<h2>학생등록(Map)</h2>
		<form
			action="${pageContext.request.contextPath}/student/studentMapEnrollEnd.do"
			method="post">
			<table id="tbl-student">
				<tr>
					<th>학생이름</th>
					<td><input type="text" name="studentName" required /></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="tel" name="studentTel" maxlength="11" /></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" name="studentEmail" /></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" name="studentAddr" required /></td>
				</tr>
				<tr>

					<td colspan="2"><input type="submit" value="전송" /></td>
				</tr>

			</table>
		</form>

	</div>
</body>
</html>
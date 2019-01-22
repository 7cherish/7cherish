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
<title>학생정보검색</title>
<script src="${pageContext.request.contextPath }/js/jquery-3.3.1.js"></script>
<style>
div#student-container{
	text-align: center;
}

table#tbl-student{
	margin: 0 auto;
	border: 1px solid;
	border-collapse: collapse;
}

table#tbl-student th, table#tbl-student td{
	border: 1px solid;
	padding: 5px;
	line-height: 2em;
}

table#tbl-student th{
	text-align: right;
}

table#tbl-student td{
	text-align: left;
}

table#tbl-student tr:last-of-type td{
	text-align: center;
}

/* 안 보여주다가 성공할때만 보여주기 */
table#tbl-ajax-student{
	margin-top: 15px;
	display: none;
	border: 1px solid;
	border-collapse: collapse;
}

table#tbl-ajax-student th, table#tbl-ajax-student td{
	border: 1px solid;
	padding: 5px;
	line-height: 2em;
}

table#tbl-ajax-student th{
	text-align: center;
}

table#tbl-ajax-student td{
	text-align: center;
}

table#tbl-ajax-student tr:last-of-type td{
	text-align: center;
}
</style>
</head>
<body>
	<div id="student-container">
		<h2>학생정보검색</h2>
		<!-- 현재 count를 request속성에 담아줬다. -->
		<%-- <p>총 학생 수는 ${requestScope.count }명 입니다.</p> requestScope는 생략해도 됨--%>
		<p>총 학생 수는 ${count }명 입니다.</p>
		<!-- 같은 곳으로 이동시킬 것이다. -->
		<!-- form에 메소드를 작성하지 않았으므로 기본값인 get이다. -->
		<form action="${pageContext.request.contextPath }/student/selectOne.do">
			<table id="tbl-student">
				<tr>
					<th>학생번호</th>
					<td>
						<input type="number" name="stdtNo" required />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="검색" />
					</td>
				</tr>
			</table>
		</form>
		
		<hr />
		
		<!-- 커스텀 액션태그를 이용해서 작성 -->
		<!-- 커스텀 액션태그에서 if는 else절이 없기 때문에 또 적어줘야 한다. -->
		<!-- 속성에 studentName이 비어있지 않다면 -->
		<c:if test="${not empty studentName }">
			<p>조회한 ${param.stdtNo }번 학생의 이름은 ${studentName }입니다.</p>
		</c:if>
		<!-- stdtNo가 있으면서 studentName이 비어있을때 -->
		<c:if test="${not empty param.stdtNo && empty studentName }">
			<p>${param.stdtNo }번 학생은 존재하지 않습니다.</p>
		</c:if>
		
		<hr />
		
		<form action="${pageContext.request.contextPath }/student/deleteStudent.do">
			<table id="tbl-student">
				<tr>
					<th>삭제할 학생번호</th>
					<td>
						<input type="number" name="stdtNo" required />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="삭제" />
					</td>
				</tr>
			</table>
		</form>
		
		<hr />
		
		<h2>ajax를 이용해서 학생 한 명 정보 가져오기</h2>
		<table id="tbl-student">
			<tr>
				<th>학생번호</th>
				<td>
					<input type="number" id="ajax-stdtNo" required />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" id="btn-ajax" value="검색" />
				</td>
			</tr>
		</table>
		<div>
			<table id="tbl-ajax-student"></table>
		</div>

	</div>
<script>
$("#btn-ajax").on('click', function(){
	var param = {
			stdtNo : $("#ajax-stdtNo").val()
	}
	$.ajax({
		url : "${pageContext.request.contextPath}/student/selectOneAjax.do",
		data : param,
		type : "get",
		dataType : "json",
		success : function(data){
			console.log("success = ", data);
			var html;
			if(data != null){
				/* JAVA Student 객체의 필드값이 자바스크립트객체의 키값으로 들어와있다. */
				html += "<tr><th>학생번호</th><td>"+ data.studentNo + "</td></tr>";
				html += "<tr><th>학생이름</th><td>"+ data.studentName + "</td></tr>";
				html += "<tr><th>전화번호</th><td>"+ data.studentTel + "</td></tr>";
				html += "<tr><th>이메일</th><td>"+ data.studentEmail + "</td></tr>";
				html += "<tr><th>주소</th><td>"+ data.studentAddr + "</td></tr>";
			}
			else{
				html += "<tr><td>조회된 학생이 없습니다.</td></tr>"
			}
			$("#tbl-ajax-student").html(html).show();
		},
		error : function(jqxhr, textStatus, errorThrown){
			console.log("ajax 처리 실패!");
			console.log("jqxhr = ", jqxhr);
			console.log("textStatus = ", textStatus);
			console.log("errorThrown = ", errorThrown);
		}
	});
});
</script>
</body>
</html>
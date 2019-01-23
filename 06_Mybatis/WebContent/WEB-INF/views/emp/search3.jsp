<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<!-- 
uri : c.tld파일의 uri와 일치하도록 할 것 
(WebAppLibraries / taglibs-standard-impl / META-INF / maven / c.tld (이건 core꺼) 
-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
	String[] jobCodeArr = request.getParameterValues("jobCode");
	String[] deptCodeArr = request.getParameterValues("dept_code");
	
	List<String> jobList = null;
	List<String> deptList = null;
	/* 잡코드를 아무것도 입력하지 않으면 null이기 때문에 조건 추가 */
	if(jobCodeArr != null){
		/* 배열을 리스트로 리턴해주는 Arrays.asList() */
		jobList = Arrays.asList(jobCodeArr);
	}
	
	if(deptCodeArr != null){
		/* 배열을 리스트로 리턴해주는 Arrays.asList() */
		deptList = Arrays.asList(deptCodeArr);
	}
	
	/* el로 처리하기 위해 pageContext에 속성으로 저장한다. */
	pageContext.setAttribute("jobList", jobList);
	pageContext.setAttribute("deptList", deptList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis실습</title>
<style>
div#emp-container {
	text-align: center;
}

table.tbl-emp {
	margin: 0 auto;
	border: 1px solid;
	border-collapse: collapse;
}

table.tbl-emp th, table.tbl-emp td {
	border: 1px solid;
	padding: 5px;
	background: #ffd6d6;
}

table.tbl-emp td{
	background: white;
}

div#search-container {
	padding: 15px 0;
}

div#search-container form select {
	vertical-align: middle;
}

input#btn-search {
	width: 350px;
	background: #ff0057;
	opacity: 0.5;
	color: white;
	box-shadow: 0px 3px 15px grey;
}

table#tbl-search {
	margin: 0 auto;
}

table#tbl-search th, table#tbl-search td {
	padding: 5px 15px;
}

table#tbl-search td {
	text-align: left;
}
</style>
</head>
<body>
	<div id="emp-container">
		<h2>사원정보3</h2>
		<div id="search-container">
			<!-- 
			웬만한 검색기능은 
			사용자가 공유할수도 있으므로 get방식으로 하는 것이 좋다.
			-->
			<form action="search3.do">
				<p>
				<h3>검색</h3>
				<input type="button" value="초기화"
					onclick="location.href='search3.do';" />
				</p>
				<table id="tbl-search">
					<tr>
						<th>직급</th>
						<td>
							<!-- jobList가 null이 아니면서 jobList가 J1이라는 값을 가지고 있으면 체크 -->
							<input type="checkbox" name="jobCode" id="j1" value="J1" ${jobList != null && jobList.contains('J1')?'checked':'' } />
							<label for="j1">대표</label>
							<input type="checkbox" name="jobCode" id="j2" value="J2" ${jobList != null && jobList.contains('J2')?'checked':'' } />
							<label for="j2">부사장</label>
							<input type="checkbox" name="jobCode" id="j3" value="J3" ${jobList != null && jobList.contains('J3')?'checked':'' }  />
							<label for="j3">부장</label>
							<input type="checkbox" name="jobCode" id="j4" value="J4" ${jobList != null && jobList.contains('J4')?'checked':'' }  />
							<label for="j4">차장</label>
							<input type="checkbox" name="jobCode" id="j5" value="J5" ${jobList != null && jobList.contains('J5')?'checked':'' }  />
							<label for="j5">과장</label>
							<input type="checkbox" name="jobCode" id="j6" value="J6" ${jobList != null && jobList.contains('J6')?'checked':'' }  />
							<label for="j6">대리</label>
							<input type="checkbox" name="jobCode" id="j7" value="J7" ${jobList != null && jobList.contains('J7')?'checked':'' }  />
							<label for="j7">사원</label>
						</td>
					</tr>
					<tr>
						<th>부서명</th>
						<td>
							<!-- (input:checkbox[name=dept_code][value=d$]#d$+label[for=d$])*9 -->
							<input type="checkbox" name="dept_code" id="d1" value="D1" ${deptList != null && deptList.contains('D1')?'checked':'' } />
							<label for="d1">인사관리부</label>
							<input type="checkbox" name="dept_code" id="d2" value="D2" ${deptList != null && deptList.contains('D2')?'checked':'' } />
							<label for="d2">회계관리부</label>
							<input type="checkbox" name="dept_code" id="d3" value="D3" ${deptList != null && deptList.contains('D3')?'checked':'' } />
							<label for="d3">마케팅부</label>
							<br />
							<input type="checkbox" name="dept_code" id="d4" value="D4" ${deptList != null && deptList.contains('D4')?'checked':'' } />
							<label for="d4">국내영업부</label>
							<input type="checkbox" name="dept_code" id="d5" value="D5" ${deptList != null && deptList.contains('D5')?'checked':'' } />
							<label for="d5">해외영업1부</label>
							<input type="checkbox" name="dept_code" id="d6" value="D6" ${deptList != null && deptList.contains('D6')?'checked':'' } />
							<label for="d6">해외영업2부</label>
							<br />
							<input type="checkbox" name="dept_code" id="d7" value="D7" ${deptList != null && deptList.contains('D7')?'checked':'' } />
							<label for="d7">해외영업3부</label>
							<input type="checkbox" name="dept_code" id="d8" value="D8" ${deptList != null && deptList.contains('D8')?'checked':'' } />
							<label for="d8">기술지원부</label>
							<input type="checkbox" name="dept_code" id="d9" value="D9" ${deptList != null && deptList.contains('D9')?'checked':'' } />
							<label for="d9">총무부</label>
							<br />
							<input type="checkbox" name="dept_code" id="d0" value="D0" ${deptList != null && deptList.contains('D0')?'checked':'' } />
							<label for="d0">부서없음</label>
						</td>
					</tr>
					<tr>
						<th colspan="2">
							<input type="submit" id="btn-search"
							value="검색" />
						</th>
					</tr>
				</table>
			</form>
		</div>

		<table class="tbl-emp">
			<tr>
				<th></th>
				<th>사번</th>
				<th>사원명</th>
				<th>주민번호</th>
				<th>성별</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>부서코드</th>
				<th>직급코드</th>
				<th>급여레벨</th>
				<th>급여</th>
				<th>보너스율</th>
				<th>매니져 사번</th>
				<th>입사일</th>
			</tr>
			<c:if test="${not empty list}">
				<c:forEach var="e" items="${list}" varStatus="vs">
					<tr>
						<td>${vs.count}</td>
						<td>${e["EMP_ID"]}</td>
						<td>${e["EMP_NAME"]}</td>
						<td>${fn:substring(e["EMP_NO"], 0, 8).concat("******")}</td>
						<td>${e["GENDER"]}</td>
						<td>${e["EMAIL"]}</td>
						<td>${e["PHONE"]}</td>
						<td>${e["DEPT_CODE"]}</td>
						<td>${e["JOB_CODE"]}</td>
						<td>${e["SAL_LEVEL"]}</td>
						<td><fmt:formatNumber value="${e['SALARY']}" type="currency" /></td>
						<td><fmt:formatNumber value="${e['BONUS']}" type="percent" /></td>
						<td>${e["MANAGER_ID"]}</td>
						<td><fmt:formatDate value="${e['HIRE_DATE']}" type="date"
								pattern="yyyy/MM/dd" /></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list}">
				<tr>
					<td colspan="14">검색결과가 없습니다.</td>
				</tr>
			</c:if>
		</table>
	</div>

</body>
</html>
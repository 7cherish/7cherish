<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 
uri : c.tld파일의 uri와 일치하도록 할 것 
(WebAppLibraries / taglibs-standard-impl / META-INF / maven / c.tld (이건 core꺼) 
-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		<h2>사원정보</h2>
		<div id="search-container">
			<!-- 
			웬만한 검색기능은 
			사용자가 공유할수도 있으므로 get방식으로 하는 것이 좋다.
			-->
			<form action="${pageContext.request.contextPath }/emp/search1.do">
				<select name="searchType" required>
					<option value="emp_id" <c:if test="${'emp_id' eq param.searchType}">selected</c:if>>사번</option>
					<option value="emp_name" ${'emp_name' eq param.searchType?"selected":"" }>사원명</option>
					<option value="email" ${"email".equals(param.searchType)?"selected":""}>이메일</option>
					<option value="phone" ${"phone" == param.searchType?"selected":"" }>전화번호</option>
				</select> 
				<input type="search" name="searchKeyword" required />
				<input type="submit" value="검색" />
			</form>
		</div>

		<table class="tbl-emp">
        <tr>
            <th></th>
            <th>사번</th>
            <th>사원명</th>
            <th>주민번호</th>
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
        <c:forEach var="e" items="${list}"  varStatus="vs">
            <tr>
                <td>${vs.count}</td>
                <td>${e["EMP_ID"]}</td>
                <td>${e["EMP_NAME"]}</td>
                <td>${fn:substring(e["EMP_NO"], 0, 8).concat("******")}</td>
                <td>${e["EMAIL"]}</td>
                <td>${e["PHONE"]}</td>
                <td>${e["DEPT_CODE"]}</td>
                <td>${e["JOB_CODE"]}</td>
                <td>${e["SAL_LEVEL"]}</td>
                <td><fmt:formatNumber value="${e['SALARY']}" type="currency" /></td>
                <td><fmt:formatNumber value="${e['BONUS']}" type="percent" /></td>
                <td>${e["MANAGER_ID"]}</td>
                <td><fmt:formatDate value="${e['HIRE_DATE']}" type="date" pattern="yyyy/MM/dd" /></td>
            </tr>
        </c:forEach>
        </c:if>
        <c:if test="${empty list}">
            <tr>
                <td colspan="13">
                    검색결과가 없습니다.
                </td>
            </tr>
        </c:if>
    </table>
	</div>

</body>
</html>
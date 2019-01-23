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

div#search-container form select{
   vertical-align: middle;
}
</style>
</head>
<body>
	<div id="emp-container">
		<h2>사원정보(paging)</h2>

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
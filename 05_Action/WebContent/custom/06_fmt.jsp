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
<title>fmt</title>
</head>
<body>
	<h2>jstl fmt테스트</h2>
	<p>날짜, 시간, 숫자데이터의 출력형식을 지정할때 사용한다.</p>
	
	<h3>숫자</h3>
	<p>
		# : 해당 자리수가 없으면, 공란. <br />
		0 : 해당 자리수가 없어도 0으로 치환
	</p>
	# : <fmt:formatNumber value="123.456" pattern="#.#####" /> <br />
	0 : <fmt:formatNumber value="123.456" pattern="0.00000" /> <br />
	
	<p>숫자 : 천자리 단위 콤마 찍기</p>
	<fmt:formatNumber value="123456789" groupingUsed="true" /> <br />
	
	<p>type속성을 통한 백분율, 통화표시</p>
	<!-- type - persent : value를 퍼센트 단위로 환산해서 보여준다. -->
	<fmt:formatNumber value="0.12" type="percent"/> <br />
	<fmt:formatNumber value="123456789" type="currency" currencySymbol="\\"/> <br />
	
	<p>지역대설정을 통한 원화표시(영문버전 설치된 OS에서 필수)</p>
	<fmt:setLocale value="ko_kr"/>
	
	
	<h3>날짜/시간</h3>
	<!-- 최상단에 하나 두면 밑에서 날짜나 시간정보 얻을때 꺼내 쓰기 편해진다. -->
	<c:set var="today" value="<%=new java.util.Date() %>" />
	오늘 날짜 : <fmt:formatDate value="${today }" type="date"/> <br />
	현재 시간 : <fmt:formatDate value="${today }" type="time"/> <br />
	날짜 시간 : <fmt:formatDate value="${today }" type="both"/> <br />
	yy/MM/dd(E) : <fmt:formatDate value="${today }" type="date" pattern="yy/MM/dd(E)"/> <br />
	hh:mm:ss : <fmt:formatDate value="${today }" type="time" pattern="hh:mm:ss"/> <br />
	
<!-- 
	숫자1 : <fmt:formatNumber value="123456789" type="number"/><br>
	숫자2 : <fmt:formatNumber value="1000" type="currency" currencySymbol="￦"/><br>
	숫자3 : <fmt:formatNumber value="0.3" type="percent"/><br>
	숫자4 : <fmt:formatNumber value="12345.678" pattern=".00"/><br>

	<결과값>
	
	숫자1 : 123,456,789  <- 기본 천단위 끊어서 표현
	숫자2 : ￦ 1,000.00  <- 원표시 붙이고 (,)붙이고 소수점 2개 까지 표현
	숫자3 : 30%   <- %로 표현
	숫자4 : 12345.68  <- 설정 소수점에서 반올림으로 표현 

 

	2. 날짜 포맷 변환
	
	라이브러리로 아래와 같이 추가
	
	<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> --%>


	<fmt:formatDate value="${board.registDate}" pattern="yyyy-MM-dd"/>
-->
</body>
</html>
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
	0 : <fmt:formatNumber value="123.456" pattern="0.00000" />
	
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
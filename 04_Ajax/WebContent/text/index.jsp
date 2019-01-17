<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- jquery를 통한 ajax구현 --%>
<%--
* XMLHttpRequest객체의 핸들링을 보다 간결하게 할 수 있다.
* 브라우저별 이슈를 jquery에서 대신 해결해준다.
* $.ajax api에 관련된 모든 사항이 있다. (http://api.jquery.com/jquery.ajax/)
* 
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax : text</title>
<!-- 서버가 텍스트로 돌려줄때 예를 들어 해보겠습니다. -->
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<style>
div#myDiv{
	width: 300px;
	min-height: 100px;
	border: 1px solid red;
	margin: 30px 10px;
}
</style>
</head>
<body>
	<h2>ajax : text</h2>
	<button id="btn1">sample.txt 요청</button>
	<div id="myDiv"></div>
<script>
$("#btn1").on("click", function(){
	$.ajax({
		url: "<%=request.getContextPath()%>/text/sample.txt",
		type: "get", // 기본값은 get. post와 큰 차이가 없어 생략해도 된다.
		dataType: "text", // text, html, script, xml, json -> 생략시 자동으로 타입지정(jquery에서 알아서 해줌)
		success: function(data){ // 서버에서 전송받은 데이터 처리
			//$("#myDiv").text(data); // myDiv에 text로 data를 넣어주세요.
			$("#myDiv").html(data);
		},
		error: function(){ // 에러사항발생시
			console.log("ajax error!");
		},
		complete: function(){ // 어떤 상황에서도 실행되는 함수
			console.log("ajax 요청 끝!");
		}
	});
});
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax : html</title>
<!-- 서버가 HTML로 돌려줄때 예를 들어 해보겠습니다. -->
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<style>
#myDiv{
	padding: 10px;
}
</style>
<script>
$(function() {

	$("#btn").click(function() {
		$.ajax({
			url: "<%=request.getContextPath()%>/html.do",
			type: "get",
			dataType: "html",
			success: function(data){ // 서버로부터 받아온 값을 data로 받아서 처리
				$("#myDiv").html(data);
			},
			error: function(jqxhr, textStatus, errorThrow){
				console.log("ajax 처리실패!");
				console.log(jqxhr);
				console.log(textStatus);
				console.log(errorThrow);
			},
			complete: function(){}
		});
	});

});
</script>
</head>
<body>
	<h2>ajax : html</h2>
	<button id="btn">실행</button>
	<div id="myDiv"></div>
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax : xml</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<style>
table, th, td{
	border: 1px solid;
	border-collapse: collapse;
}
</style>
</head>
<body>
	<h2>ajax : xml</h2>
	<button id="btn">실행</button>
	<div id="fiction">
		<h3>소설</h3>
		<table id="fictionInfo"></table>
	</div>
	<div id="it">
		<h3>프로그래밍</h3>
		<table id="itInfo"></table>
	</div>
<script>
$("#btn").click(function(){
	$.ajax({
		url: "sample.xml",
		type: "get",
		dataType: "xml",
		success: function(data){
			console.log("data@index.jsp=", data);
			// 최상위 태그찾기
			var root = $(data).find(":root");
			
			// 하위 태그찾기
			var bookArr = root.find("book");
			console.log("bookArr@index.jsp=", bookArr);
			
			// html태그로 parsing
			var fictionInfo = "<tr><th>제목</th><th>저자</th></tr>";
			var itInfo = "<tr><th>제목</th><th>저자</th></tr>";
			
			bookArr.each(function(){
				// book태그 밑에 title값
				var info = "<tr><td>" + $(this).find("title").text() + "</td>";
				info += "<td>"+$(this).find("author").text()+"</td></tr>";
				
				if($(this).find("subject").text() === "소설"){
					// 소설일 경우 fictionInfo에 붙여주기
					fictionInfo += info;
				}
				else{
					// 소설이 아닐 경우 itInfo에 붙여주기
					itInfo += info;
				}
			}); // end of bookArr.each
			
			$("#fictionInfo").html(fictionInfo);
			$("#itInfo").html(itInfo);
			
		} // end of success
	}); // end of ajax
}); // end of #btn click
</script>
</body>
</html>
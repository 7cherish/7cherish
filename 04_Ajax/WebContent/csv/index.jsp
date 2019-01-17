<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax : csv</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<style>
#myDiv{
	padding: 10px;
}

img{
	width: 100px;
}

table{
	border: 1px solid;
	border-collapse: collapse;
}

th, td{
	border: 1px solid;
	padding: 10px;
}
</style>
</head>
<body>
<!-- 
(심화) CSV형식의 예제
CSV(Comma Separated Values)는 여러 데이터를 쉼표로 구분해서 표현하는 방법

CSV 데이터

	홍길동,20,서울
	김말똥,30,경기

	var responseData = xhttp.responseText;
	var rows = responseData.split("\n");  	// 개행문자로 행을 구분
	var cols = rows[0].split(",");		// 쉼표(,)로 열을 구분
	var rowData = cols[0] + "\t" + cols[1] +"\t\ + cols[2]";
	alert(rowData);
 -->
	<h2>ajax : csv</h2>
	<p>CSV : Comma Seperated Values</p>
	<button id="btn">실행</button>
	<div id="myDiv"></div>

<script>
$("#btn").click(function(){
	// ajax 호출 코드
	$.ajax({
		url: "<%=request.getContextPath()%>/csv.do",
		type: "get",
		dataType: "text",
		success: function(data){
			// console.log("data@index.jsp", data);
			// csv@AjaxCSVServlet=박보검,01007890789,parkBogum.jpg§줄리아 로버츠,01012341234,juliaRoberts.jpg§맷 데이먼,01045674567,mattDamon.jpg
			
			var memberArr = data.split("§");
			console.log("memberArr@index.jsp", memberArr);
			
			var table = $("<table></table>");
			for(var i=0; i<memberArr.length; i++){
				var m = memberArr[i].split(",");
				var html = "<tr><td>";
				html += "<img src='<%=request.getContextPath()%>/images/"+m[2]+"'/></td>";
				html += "<td>"+m[0]+"</td>";
				html += "<td>"+m[1];
				html += "</td></tr>";
				table.append(html); // table에 한 행마다 추가
			}
			$("#myDiv").html(table);
		}
	});
});
</script>
</body>
</html>
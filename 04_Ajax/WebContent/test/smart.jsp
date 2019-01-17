<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src = "<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<title>스마트 가전센터</title>
<script>
function time(){

	 var now = new Date();
	 document.getElementById("time").innerHTML = now.getFullYear()+"년"+(now.getMonth()+1)+"월"+now.getDate()+"일"+ 
	 now.getHours()+":"+now.getMinutes()+":"+now.getSeconds();
	 
	
	setTimeout("time()", 1000);
	
}
window.onload = time;

</script>
<style>
div.container{
	float: left;
	width: 29%;
	height:300px;
    margin: 10px;
    padding: 10px;
	background:lightgrey;
	text-align:center;
}
table {
	border:1px solid;
	margin:auto;
}
td,th {
	border:1px solid;
}
span#time{
	text-decoration:underline;
	margin: 15px;
    display: block;
}
</style>
</head>
<body>
<h1>스마트 가전 센터 주문페이지</h1>
<p>
	1. ajax를 이용해서 제품주문을 처리하고, 실시간 판매현황(최근5건)에 출력하세요. <br />
	2. 10초마다 판매랭킹리스트(상위5개제품과 누적판매량)를 갱신하는 ajax함수를 작성하세요. <br />
	(bonus) 현재시각을 표시하세요.
</p>
<div id="order-container" class="container">
	<h2>주문</h2>
	<table>
		<tr>
			<th>제품명</th>
			<td>
				<select name="pname" id="pname" required>
					<option value="iPhoneX">iPhoneX</option>
					<option value="iPhone7">iPhone7</option>
					<option value="iPhone7Plus">iPhone7Plus</option>
					<option value="GalaxyNote8">GalaxyNote8</option>
					<option value="Galaxy8">Galaxy8</option>
					<option value="샤오미맥스2">샤오미맥스2</option>
					<option value="LGV30">LGV30</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>주문량</th>
			<td><input type="number" id="amount" name="amount" min="1" value="1" required></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" id="btn-order" value="주문" />
			</td>
		</tr>
	</table>
</div>
<div class="container">
	<h2>주문리스트</h2>
	<div id="order-list"></div>
</div>
<div class="container">
	<h2>인기순위</h2>
	<span id="time"></span>
	<div id="rank-list"></div>
</div>

<script>
$("#btn-order").on("click", function(){
	var param = {
			pname : $("#pname").val(),
			amount : $("#amount").val()
	}
	//판매정보를 insert하는 ajax
	$.ajax({
		url: "<%=request.getContextPath()%>/smart/order.do", //여기 도차끄★
		data: param,
		success: function(data){
			console.log("btn-order_data@smart.jsp=", data); //
			
			
			var table = $("<table></table>"); 
			var html = "<tr><th>제품명</th><th>수량</th><th>주문일자</th></tr>"; //헤더 지정
			
			//반복문을 통해 테이블의 각 행마다 값을 넣어준다.
			for(var i in data){
				// 배열을 for in문으로 돌리면 index가 담긴다.
				var smart = data[i];
				html += "<tr><td>" + smart.pname+"</td>";
				html += "<td>"+ smart.amount +"</td>";
				html += "<td>"+ smart.pdate +"</td></tr>"; //smart.pdate = "12월 29, 2018";
			} // end of for in
			
			table.append(html);
			$("#order-list").html(table);
			
		}
	});
});

$(function(){
	getTop5();
	//10초
	setInterval(getTop5, 10000);
});

function getTop5(){
	$.ajax({
		url: "<%=request.getContextPath()%>/smart/getTop5.do",
		success: function(data){
			console.log("getTop5()@smart.jsp=", data); //list가 담겨있음
			var table = $("<table></table>");
			var html = "<tr><th>순위</th><th>제품명</th><th>판매량</th></tr>"; //헤더 지정
			
			//반복문을 통해 테이블의 각 행마다 값을 넣어준다.
			for(var i=0; i<data.length; i++){ 
				// 배열을 for in문으로 돌리면 index가 담긴다.
				var smart = data[i];
				html += "<tr><td>" +(i+1) +"</td>"; //순위
				html += "<td>"+ smart.pname +"</td>";
				html += "<td>"+ smart.amount +"</td></tr>";
			} // end of for in
			
			table.append(html);
			$("#rank-list").html(table);
		
			
		}
	});
}

</script>
</body>
</html>
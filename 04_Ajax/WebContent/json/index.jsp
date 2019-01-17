<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax : json</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<style>
.area{
	border: 1px solid red;
	width: 300px;
	min-height: 50px;
	margin: 10px;
	padding: 10px;
}

table, td{
	border: 1px solid;
}
</style>
</head>
<body>
	<h2>ajax : json</h2>
	<h3>gson라이브러리의 활용</h3>
	
	<hr />
	<h3>1. 전체회원조회</h3>
	<button id="btn1">실행</button>
	<div id="area1" class="area"></div>
	<script>
	$("#btn1").on("click", function(){
		$.ajax({
			url: "<%=request.getContextPath()%>/gson/selectAll.do",
			type: "get",
			dataType: "json",
			success: function(data){
				console.log("btn1_data@index.jsp=", data); // data는 이미 javascript배열객체이다.
				// json과 javascript객체간 왔다갔다할때 데이터를 변경했었다.
				// json <---> javascript
				//      <---- JSON.stringify
				//      ----> JSON.parse()
				var table = $("<table></table>");
				for(var i in data){
					// 배열을 for in문으로 돌리면 index가 담긴다.
					var user = data[i];
					var html = "<tr><td>" + user.userId+"</td>";
					html += "<td>"+ user.userName +"</td>";
					html += "<td>"+ user.userAddr +"</td></tr>";
					table.append(html);
				} // end of for in
				
				$("#area1").html(table);
				
			}, // end of success
			error: function(jqxhr, textStatus, errorThrow){
				console.log("btn1_ajax처리실패!@index.jsp");
				console.log("btn1_jqxhr@index.jsp=", jqxhr);
				console.log("btn1_textStatus@index.jsp=", textStatus);
				console.log("btn1_errorThrow@index.jsp=", errorThrow);
			} // end of error
		}); // end of ajax
	}); // end of btn1 click
	</script>
	<hr />
	<!-- 지금 별도로 부여된 회원번호는 없고 리스트에 저장 돼 있으니까 -->
	<h3>2. 리스트인덱스로 회원조회</h3>
	<input type="number" id="input2" />
	<button id="btn2">실행</button>
	<div id="area2" class="area"></div>
	<script>
	$("#btn2").click(function(){
		var index = $("#input2").val();
		$.ajax({
			url: "<%=request.getContextPath()%>/gson/selectOneByIndex.do",
			type: "get",
			data: "index=" + index, // 직렬화된 문자열가능
			// data: "index=" + index +"&name=" + name, // 여러 건이라면 이런 식으로
			// data: {index: index}, // 객체로 전달가능
			success: function(data){
				console.log("btn2_data@index.jsp=", data);
				var table = $("<table></table>");
				
				if(data!=null){
					var user = data;
					var html = "";
					html = "<tr><td>아이디</td><td>" + user.userId+"</td></tr>";
					html += "<td>이름</td><td>" + user.userName + "</td></tr>";
					html += "<td>주소</td><td>" + user.userAddr + "</td></tr>";
				}
				else{
					var html = "<tr><td>없어</td></tr>";
				}
				
				table.append(html);
				$("#area2").html(table);
				
			}, // end of success
			error: function(jqxhr, textStatus, errorThrow){
				console.log("btn2_ajax처리실패!@index.jsp");
				console.log("btn2_jqxhr@index.jsp=", jqxhr);
				console.log("btn2_textStatus@index.jsp=", textStatus);
				console.log("btn2_errorThrow@index.jsp=", errorThrow);
			}
		}); // end of ajax
	}); // end of btn2 click
	</script>
	
	<hr />
	<h3>@실습문제 : 사용자 정보 등록하기</h3>
	<p>
		입력받은 사용자 정보를 ajax를 통해서 
		회원목록(UserListSingleton)에 추가하세요. <br />
		그리고, 추가된 회원전체목록을 리턴해서 출력하세요.
	</p>
	<input type="text" id="userId" placeholder="아이디" />
	<br />
	<input type="text" id="userName" placeholder="이름" />
	<br />
	<input type="text" id="userAddr" placeholder="주소" />
	<br />
	<button id="btn3">사용자 등록</button>
	<div id="area3" class="area"></div>
	<script>
	// 호출 url : /gson/insertUser.do
	$("#btn3").click(function(){
		
		$.ajax({
			url: "<%=request.getContextPath()%>/gson/insertUser.do",
			type: "get",
			data: {userId:$("#userId").val(),
				   userName:$("#userName").val(),
				   userAddr:$("#userAddr").val()},
			success: function(data){
				console.log("btn3_data@index.jsp=", data);
				
				var table = $("<table></table>");
				for(var i in data){
					// 배열을 for in문으로 돌리면 index가 담긴다.
					var user = data[i];
					var html = "<tr><td>" + user.userId+"</td>";
					html += "<td>"+ user.userName +"</td>";
					html += "<td>"+ user.userAddr +"</td></tr>";
					table.append(html);
				} // end of for in
				
				$("#area3").html(table);
			}
			
		});
	});
	</script>
	
</body>
</html>
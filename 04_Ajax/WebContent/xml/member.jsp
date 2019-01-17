<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax : xml</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<style>
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
	<h2>회원정보 xml로 처리하기</h2>
	<button id="btn">실행</button>
	
	<div id="member-container">
		<h3>회원정보</h3>
		<!-- 
		서버에 요청해서 xml로 돌려주고, 
		xml을 파싱해서 memberInfo테이블에 데이터를 넣어준다. -->
		<table id="memberInfo"></table>
	</div>
<script>
$("#btn").on("click", function(){
	$.ajax({
		url: "<%=request.getContextPath()%>/xml/member.do",
		type: "get",
		dataType: "xml",
		success: function(data){
			console.log("data@member.jsp=", data);
			
			//xml 파싱하기 
	         //1. 최상위 태그
	         var root = $(data).find(":root");
	         //2. 하위태그
	         var memberArr = root.find("member");
	         console.log(memberArr);
	         var info = "";
	         var memberInfo = "<tr><th>프로필</th><th>이름</th><th>전화번호</th></tr>";
	         //3. html 태그 꺼내기
	         memberArr.each(function(){
	            info += "<tr><td>"
	                + "<img src='"+"<%=request.getContextPath()%>/images/"
	                + $(this).find("profile").text() + "'/></td><td>"
	                 + $(this).find("name").text() +"</td><td>" 
	                 + $(this).find("phone").text()
	                 + "</td></tr>";    
	         });
	         
	         //4. html 입력
	         $("#memberInfo").html(memberInfo + info);
			
			
		} // end of success
		
	}); // end of ajax
}); // end of btn click
</script>
</body>
</html>
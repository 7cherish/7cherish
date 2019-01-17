<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//1. request Encoding
	request.setCharacterEncoding("utf8");

	// 2. request Parameter 처리
	String name = request.getParameter("name");
	String color = request.getParameter("color");
	String animal = request.getParameter("animal");

	// 복수 개 이상의 데이터 처리 : getParameterValues, 배열에 담아준다.
	String foodArr[] = request.getParameterValues("food");

	// 확인
	System.out.println("name" + name);
	System.out.println("color" + color);
	System.out.println("animal" + animal);

	// 배열은 반복문을 통해 출력
	for (int i = 0; i < foodArr.length; i++) {
		System.out.println("foodArr[" + i + "] = " + foodArr[i]);
	}

	// 3. 비지니스 로직 처리
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인취향검사(사진)</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<script>
	$(function() {
		console.log("jquery loading 완료!!");
		// scriptlet이 먼저 처리된다.
		
		// 사용자가 빨간색을 선택하면 빨간색과 관련된 이미지를 보여주는 것을 동적으로 처리
		// 1. color
		<%
			switch(color){
			case "빨강":			
		%>
			$("#color").append("<img src='<%=request.getContextPath() %>/images/red.png'/>");
		<%
				break;		
			case "노랑":
		%>
			$("#color").append("<img src='<%=request.getContextPath() %>/images/yellow.png'/>");
		<%				
				break;		
			case "파랑":
		%>
			$("#color").append("<img src='<%=request.getContextPath() %>/images/blue.png'/>");
		<%				
				break;				
			case "초록":
		%>
			$("#color").append("<img src='<%=request.getContextPath() %>/images/blue.png'/>");
		<%
				break;
			}
			
		// 2. animal
			switch(animal){
			case "강아지":
		%>
			$("#animal").append("<img src='<%=request.getContextPath() %>/images/dog.png'/>");
		<%
				break;
			case "고양이":
		%>
			$("#animal").append("<img src='<%=request.getContextPath() %>/images/cat.png'/>");
		<%
		 		break;
			case "병아리":
		%>
			$("#animal").append("<img src='<%=request.getContextPath() %>/images/fish.png'/>");
		<% 
				break;
		}
		
		// 3. food
		for(int i=0; i < foodArr.length; i++){
			
			switch(foodArr[i]){
			case "짜장면":
		%>
			$("#food").append("<img src='<%=request.getContextPath() %>/images/jjm.png'/>");
		<%
				break;
			case "짬뽕":
		%>
			$("#food").append("<img src='<%=request.getContextPath() %>/images/jjbong.png'/>");
		<%
				break;
			case "탕수육":
		%>
			$("#food").append("<img src='<%=request.getContextPath() %>/images/tangsy.png'/>");		
		<%
				break;
			case "양장피":
		%>
			$("#food").append("<img src='<%=request.getContextPath() %>/images/yang.png'/>");		
		<%
				break;
			case "팔보채":
		%>
			$("#food").append("<img src='<%=request.getContextPath() %>/images/palbc.png'/>");		
		<%
			break;
			
			}
		
		}
		%>
	});
</script>
<style>
#container {
	width: 800px;
	margin: 0 auto;
	padding: 10px;
	text-align: center;
}

#container div {
	margin: 50px 0;
}

#container img {
	width: 150px;
	height: 110px;
	padding-right: 10px;
}
</style>
</head>
<body>
	<div id="container">

		<div id="header">
			<h2><%=name%>님의 개인취향결과
			</h2>
		</div>

		<div id="color">
			<span class="title">색깔</span>
			<br /><br />
		</div>

		<div id="animal">
			<span class="title">동물</span>
			<br /><br />
		</div>

		<div id="food">
			<span class="title">음식</span>
			<br /><br />
		</div>

	</div>

</body>
</html>
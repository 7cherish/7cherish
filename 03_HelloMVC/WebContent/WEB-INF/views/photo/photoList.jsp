<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int totalPage = (int)request.getAttribute("totalPage");
%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/photo.css" />
<section id="photoList-container">
	<h2>사진게시판</h2>
	<div id="photo-container"></div>
	<div id="btn-more-container">
		<button id="btn-more" value="1">더보기(1/<%=totalPage %>)</button>
	</div>
</section>
<script>
// onload함수
$(function(){
	// 페이지 로딩시 첫 페이지 호출
	photoMore(1);
	
	// 더보기 버튼 이벤트 핸들러
	$("#btn-more").on("click", function(){
		var nextPage = Number($(this).val()) + 1; // Number로 형변환해주기
		photoMore(nextPage);
	});
});

function photoMore(cPage){
	var param = {
		cPage : cPage,
		/* 속성(이름) : 속성값(변수값으로 치환) */
	};
	
	$.ajax({
		url: "<%=request.getContextPath()%>/photo/photoMore",
		data: param,
		type: "post",
		dataType: "json", /* 서버로부터 전달받을 타입 */
		success: function(data){
			console.log("data@photoList.jsp=", data); // 자바스크립트배열(5개 들어있는)
			
			var html = "";
			for(var i in data){
				var p = data[i];
				console.log("p@photoList.jsp=", p);
				html += "<div class='polaroid'>";
				html += "<img src='<%=request.getContextPath()%>/upload/photo/";
				html += p.renamedFileName + "' width='300px'/>"; // 이미지 태그 여기서 닫음
				html += "<p class='info'>";
				html += "<span class='writer'>" + p.photoWriter + "</span>&nbsp;";
				html += "<span class='photoDate'>" + p.photoDate + "</span></p>";
				html += "<p class='caption'>" + p.photoContent + "</p></div>";
			}
			
			$("#photo-container").append(html);
			
			// 요청한 페이지를 현재페이지로 저장
			$("#btn-more").val(cPage)
						  .text("더보기(" + cPage + "/<%=totalPage%>)");
			
			// 만약 마지막 페이지인 경우, 더보기 버튼을 비활성화
			if(cPage == <%=totalPage%>){
				$("#btn-more").attr("disabled", "disabled")
							  .css("cursor", "not-allowed");
			}
		} // end of success
	}); // end of ajax
} // end of photoMore(cPage)
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
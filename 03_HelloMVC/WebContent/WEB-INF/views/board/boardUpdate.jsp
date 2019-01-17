<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.board.model.vo.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<%
	Board b = (Board)request.getAttribute("board");
%>
<script>
	function validate() {
		//내용을 작성하지 않은 경우에 대한 유효성 검사하세요.
		//공백만 작성한 경우도 폼이 제출되어서는 안됨.

		var content = $("textarea[name=content]");

		console.log(content);
		if (content.val().trim().length == 0) {
			alert("내용을 작성하세요.");
			content.focus();
			return false; // 폼전송 방지
		}
		return true;
	}

$(function(){
	/* 첨부파일이 바뀌면 체인지 */
	$("[name=up_file]").change(function(){
		console.log($(this).val()); // 선택된 경우 가짜 파일 경로가 나온다. 보안적인 이유로 실제 디렉토리가 안 나옴 또는 "" 이게 나옴
		if($(this).val() != ""){
			/* 값이 없으면 숨기기 */
			$("span#fname").hide();
		}
		else{
			/* 값이 있으면 보여주기 */
			$("span#fname").show();
		}
		
	});
});
</script>
<section id="board-container">
	<h2>게시판 작성</h2>
	<form action="<%=request.getContextPath()%>/board/boardUpdateEnd"
		method="post"
		enctype="multipart/form-data" > <!-- 파일업로드를 위한 필수속성(이게 빠지면 파일이 안 넘어감) -->
		<input type="hidden" name="boardNo" value="<%=b.getBoardNo() %>"/>
		<table id="tbl-board-view">
			<tr>
				<th>제 목</th>
				<td>
					<input type="text" 
						   name="title" 
						   value="<%=b.getBoardTitle() %>" 
						   required>
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					<!--로그인한 회원명출력--> 
					<%=memberLoggedIn.getMemberName() %>
					<input type="text" 
						   name="writer"
						   value="<%=b.getBoardWriter() %>"
						   readonly />
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td style="position:relative;">
					<input type="file" name="up_file">
					<span id="fname"><%=b.getOriginalFileName()!=null?b.getOriginalFileName():"" %></span>
					
					<!-- 파일 변경시 삭제 -->
					<input type="hidden" name="old_renamed_file" 
						   value="<%=b.getRenamedFileName() %>" />
					<input type="hidden" name="old_original_file"
						   value="<%=b.getOriginalFileName() %>" />
					
					<!-- 실제 파일이 있는 경우만 보여주기 -->
					<%if(b.getOriginalFileName() != null){ %>
					<br />
					<input type="checkbox" 
						   name="del_file" id="del_file" />
					<label for="del_file">첨부파일 삭제</label>
					<%} %>
				</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td>
					<textarea rows="5" cols="50" name="content"><%=b.getBoardContent() %></textarea>
				</td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="등록하기"
					onclick="return validate();">
				</th>
			</tr>
		</table>
	</form>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
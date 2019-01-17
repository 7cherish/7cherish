<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/board.css" />

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
</script>
<section id="board-container">
	<h2>게시판 작성</h2>
	<form action="<%=request.getContextPath()%>/board/boardFormEnd"
		method="post"
		enctype="multipart/form-data" > <!-- 파일업로드를 위한 필수속성(이게 빠지면 파일이 안 넘어감) -->
		<table id="tbl-board-view">
			<tr>
				<th>제 목</th>
				<td><input type="text" name="title" required></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					<!--로그인한 회원명출력--> 
					<%=memberLoggedIn.getMemberName() %>
					<input type="hidden" 
						   name="writer"
						   value="<%=memberLoggedIn.getMemberId() %>" />
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<input type="file" 
						   name="up_file">
				</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td><textarea rows="5" cols="50" name="content"></textarea></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="등록하기"
					onclick="return validate();"></th>
			</tr>
		</table>
	</form>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
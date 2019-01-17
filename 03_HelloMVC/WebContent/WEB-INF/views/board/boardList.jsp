<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, 
				 com.kh.board.model.vo.Board"%>
<%
	List<Board> bList = (List<Board>)request.getAttribute("bList");
	System.out.println("bList@boardList.jsp=" + bList);
	
	String pageBar = (String)request.getAttribute("pageBar");

%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/board.css" />
<script>
function goToBoardForm(){
	location.href="<%=request.getContextPath()%>/board/boardForm?memberId=<%=memberId%>";
}

</script>
<section id="board-container">
	<h2>게시판</h2>
	<input type=<%=memberLoggedIn != null?"button":"hidden"%>
		   value="글쓰기" 
		   id="btn-add"
		   onclick="goToBoardForm();" />
	<!-- 글내용은 안 보이고 제목만 보임 => 리스트이기 때문에 -->
	<table id="tbl-board">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>첨부파일</th>
			<th>조회수</th>
		</tr>
		<!-- 컨텐츠 영역 => 스크립틀릿으로 처리 -->
		<%
			if(bList == null || bList.isEmpty()){
		%>
		<tr>
			<td colspan="6" align="center">
			게시글이 없습니다.
			</td>
		</tr>		
		<%	
			} else {
				for (Board b : bList){
		%>						
		<tr>
			<td><%=b.getBoardNo() %></td>
			<td>
				<a href="<%=request.getContextPath() %>/board/boardView?boardNo=<%=b.getBoardNo() %>">
				<%=b.getBoardTitle() %> 
				<!-- 댓글이 있는 경우만 개수 표시 -->
				<%if(b.getBoardCommentCnt() > 0){ %>
				[<%=b.getBoardCommentCnt() %>]
				<% }%>
				</a>
			</td>
			<td><%=b.getBoardWriter() %></td>
			<td><%=b.getBoardDate() %></td>
			<td>
			<% if(b.getOriginalFileName() != null){ %>
				<img src="<%=request.getContextPath() %>/images/file.png" 
					 alt=""
					 width="16px" />
			<%} %>
			</td>
			<td><%=b.getReadCount() %></td>
		</tr>
		<%
				}
			}
		%>
	</table>
	
	<!-- 스크립틀릿으로 처리 -->
	<div id="pageBar">
	<%=pageBar %>
	</div>
</section>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>
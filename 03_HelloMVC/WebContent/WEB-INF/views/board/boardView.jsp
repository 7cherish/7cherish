<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.board.model.vo.*, java.util.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	Board b = (Board)request.getAttribute("board");
	List<BoardComment> commentList = (List<BoardComment>)request.getAttribute("commentList");
	System.out.println("commentList=" + commentList);
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/board.css" />
<script>
function fileDownload(oName, rName){
	var url = "<%=request.getContextPath() %>/board/fileDownload";
	// oName에는 한글, 공백 등 문자가 섞여 있을 수 있기 때문에 인코딩 처리가 필요하다.
	// 크롬 브라우저는 별도의 인코딩처리가 필요없지만,
	// IE는 암묵적인 인코딩을 지원하지 않으므로,
	// 명시적인 인코딩 처리가 필요하다.
	oName = encodeURIComponent(oName);
	console.log("oName=", oName);
	location.href = url + "?oName=" + oName + "&rName=" + rName;
}
</script>
<section id="board-container">
	<h2>게시판</h2>
	<table id="tbl-board-view">
		<tr>
			<th>글번호</th>
			<td><%=b.getBoardNo() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=b.getBoardTitle() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=b.getBoardWriter() %></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><%=b.getReadCount() %></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<!-- 첨부파일이 있는 경우만 표시돼야 함 -->
				<%if(b.getOriginalFileName()!=null) { %>
				<a href="javascript:fileDownload(
						  '<%=b.getOriginalFileName() %>',
						  '<%=b.getRenamedFileName() %>');">
					<img src="<%=request.getContextPath() %>/images/file.png" 
						 alt="첨부파일"
						 width="16px" />
					<!-- 파일명 -->
					<%=b.getOriginalFileName() %>
				</a>
				
				<% } %>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><%=b.getBoardContent() %></td>
		</tr>
		<!-- 글작성자/관리자인 경우만 표시돼야 함 -->
		<%if(memberLoggedIn!= null && 			 
			(b.getBoardWriter().equals(memberLoggedIn.getMemberName()) 
			|| "admin".equals(memberLoggedIn.getMemberId())))
		{%>
		<tr>	
			<th colspan="2">
				<input type="button" value = "수정하기" onclick="updateBoard()" />
				<input type="button" value = "삭제하기" onclick="deleteBoard()" />
			</th>
		</tr>
		<%} %>
	</table>
	<hr style="margin-top: 30px;"/>
	<!-- 댓글시작 -->
	<div id="comment-container">
		<div class="comment-editor">
			<!-- 댓글 작성할 수 있는 폼작성 -->
			<form action="<%=request.getContextPath()%>/board/boardCommentInsert"
				  method="post"
				  name="boardCommentFrm">
				<!-- 게시글 참조 -->
				<input type="hidden" 
					   name="boardRef"
					   value="<%=b.getBoardNo() %>" />
				<!-- 작성자(현재 로그인한 사람) -->
				<input type="hidden" 
					   name="boardCommentWriter"
					   value="<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():"" %>" />
				<!-- 댓글 번호 -->
				<input type="hidden" 
					   name="boardCommentLevel"
					   value="1" /><!-- 댓글레벨 : 1 -->
				<input type="hidden" 
					   name="boardCommentRef"
					   value="0" />
				<textarea name="boardCommentContent" 
						  cols="60" 
						  rows="3"></textarea>
				<button type="submit"
						id="btn-insert">
					등록
				</button>
			</form>
		</div><!-- end of .comment-editor -->
		
		<!-- 댓글목록테이블 -->
		<table id="tbl-comment">
			<%
				for (BoardComment bc : commentList) {
					if (bc.getBoardCommentLevel() == 1) {
			%>
				<tr class="level1">
					<td>
						<sub class="comment-writer">
							<%=bc.getBoardCommentWriter() %>
						</sub>
						<sub class="comment-date">
							<%=bc.getBoardCommentDate() %>
						</sub>
						<br />
						<%=bc.getBoardCommentContent() %>
					</td>
					<td>
						<!-- 대댓글 달 수 있는 버튼 -->
						<!-- 댓글에 대한 고유번호를 버튼이 가지고 있게 -->
						<!-- 조건1. 본인과 관리자만 삭제버튼이 보이게 -->
						<!-- 조건2. 삭제 후에는 댓글 페이지 다시 보이게 -->
						<button class="btn-reply"
								value="<%=bc.getBoardCommentNo()%>">
								답글
						</button>
						<%if(memberLoggedIn!= null && 			 
							(bc.getBoardCommentWriter().equals(memberLoggedIn.getMemberId()) 
						|| "admin".equals(memberLoggedIn.getMemberId())))
						{%>
						<form action="<%=request.getContextPath()%>/board/boardCommentDelete">
						<input type="hidden" name="boardNo" value="<%=b.getBoardNo() %>" />
						<input type="hidden" name="boardCommentNo" value="<%=bc.getBoardCommentNo() %>" />
						<input type="hidden" name="boardCommentLevel" value="1" />
						<button type="submit" class="btn-delete" onclick="return validate();">삭제</button>
						</form>
						<% }%>
					</td>
				</tr>
			<%
				} else {
			%>
				<tr class="level2">
					<td>
						<sub class="comment-writer">
							<%=bc.getBoardCommentWriter() %>
						</sub>
						<sub class="comment-date">
							<%=bc.getBoardCommentDate() %>
						</sub>
						<br />
						<%=bc.getBoardCommentContent() %>
					</td>
					<td>
						<%if(memberLoggedIn!= null && 			 
							((bc.getBoardCommentWriter().equals(memberLoggedIn.getMemberId()) 
						|| "admin".equals(memberLoggedIn.getMemberId()))))
						{%>
						<form action="<%=request.getContextPath()%>/board/boardCommentDelete">
						<input type="hidden" name="boardNo" value="<%=b.getBoardNo() %>"/>
						<input type="hidden" name="boardCommentNo" value="<%=bc.getBoardCommentNo()%>"/>
						<input type="hidden" name="boardCommentLevel" value="2"/>
						<button type="submit" class="btn-delete" onclick="return validate();">삭제</button>
						</form>
						<% }%>
					</td>
				</tr>
			
			<%
				}
				}
			%>
		</table>
		<!-- 댓글 끝 -->
	</div><!-- end of .comment-container -->
	<script>
		// textarea click이벤트핸들러(로그인여부검사)
		$("[name=boardCommentContent]").on('click', function() {
			/* 자바스크립틀릿보다 자바코드가 먼저 읽기 때문에
			로그인했다면 false 로그인하지않았다면 true*/
			if (<%=memberLoggedIn == null%>) {
				loginAlert();
			}
		});
		// 댓글form submit이벤트핸들러
		$("[name=boardCommentFrm]").submit(function(e) {
			if (<%=memberLoggedIn == null%>) {
				loginAlert();
				e.preventDefault(); // submit방지
				return;
			}
			// boardCommentContent 유효성검사
			var len = $("[name=boardCommentContent]").val().trim().length;
			if (len == 0) {
				alert("댓글을 작성하세요.");
				e.preventDefault();
			}
		});
		function loginAlert() {
			alert("로그인 후 이용할 수 있습니다.");
			/* 화면 상단에 있는 아이디에 포커스 주기 */
			$("#memberId").focus();
		}
		
		$(".btn-reply").on("click", function(){
			<% if(memberLoggedIn != null){ %>
			/* 로그인한 경우 */
				 var tr = $("<tr></tr>");
		         var html = '<td style="display:none; text-align:left;" colspan="2">';
		         html+= '<form action = "boardCommentInsert" method="post">';
		         html+= '<input type="hidden" name= "boardRef" value="<%=b.getBoardNo()%>">';
		         html+= '<input type="hidden" name= "boardCommentWriter" value="<%=memberLoggedIn.getMemberId()%>">';
		         html+= '<input type="hidden" name= "boardCommentLevel" value="2">';
		         html+= '<input type="hidden" name= "boardCommentRef" value="'+$(this).val()+'">';
		         html+= '<textarea name="boardCommentContent" cols="60" rows="1"></textarea>';
		         html+= '<button type="submit" class="btn-insert2">답글</button>';
		         html+= '</form></td>';
		         tr.html(html);/* 답글버튼을 누르면 동적으로 위 태그들이 나타나게 함 */
		         
		         console.log("boardCommentRef value=", $(this).val());
				
				tr.insertAfter($(this).parent().parent())
				  .children("td").slideDown(800);
				
				// 한 번 실행후에는 이벤트 핸들러 제거
				$(this).off('click');
				
				// 생성된 폼에 대해 submit이벤트핸들러 (유효성검사)
				tr.find('form').sumbmit(function(e){
					var len = $(this).find("textarea").val().trim().length;
					if(len == 0){
						// 길이가 0이면 제출하지말것
						e.preventDefault();
					}
				});
				
			<% } else { %>
			/* 로그인하지 않은 경우 */
				loginAlert();
			
			<% } %>
		});
		
		function validate(){
			if(confirm("이 댓글을 정말 삭제하시겠습니까?")){
				return true;
			}
			else{
				return false;
			}
		}
		
	</script>
</section>
<!-- 글작성자/관리자인 경우만 수정삭제가능 -->
<%if(memberLoggedIn!= null && 			 
	(b.getBoardWriter().equals(memberLoggedIn.getMemberName()) 
	|| "admin".equals(memberLoggedIn.getMemberId())))
{%>
<form action="<%=request.getContextPath() %>/board/boardDelete"
	  name="boardDelFrm"
	  method="post" >
	  <input type="hidden" 
	  		 name="boardNo"
	  		 value="<%=b.getBoardNo() %>" />
	  <!-- 첨부파일이 있는 경우에는 파일도 함께 삭제 -->
	  <!-- 서버에 저장된 실제 파일명이 필요하다 -->
	  <input type="hidden" 
	  		 name="rName"
	  		 value="<%=b.getRenamedFileName() %>" />
</form>
<script>
function updateBoard(){
	location.href = "<%=request.getContextPath() %>/board/boardUpdate?boardNo=<%=b.getBoardNo() %>";
}

function deleteBoard(){
	if(!confirm("이 게시글을 정말 삭제하시겠습니까?")){
		/* false인 경우만 리턴처리 */
		return;
	}
	$("[name=boardDelFrm]").submit();
}
</script>
<% } %>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
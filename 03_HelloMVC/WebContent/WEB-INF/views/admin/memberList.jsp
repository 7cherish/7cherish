<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
	List<Member> list = (List<Member>)request.getAttribute("list");
	System.out.println("list@memberList.jsp=" + list);
	
	int cPage = (int)request.getAttribute("cPage");
	int numPerPage = (int)request.getAttribute("numPerPage");
	String pageBar = (String)request.getAttribute("pageBar");
	
%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css" />
<script>
$(function(){
	var sid = $("#search-memberId");
	var sname = $("#search-memberName");
	var sgender = $("#search-gender");
	
	$("select#searchType").change(function(){
		sid.hide();
		sname.hide();
		sgender.hide();
		
		$("#search-" + $(this).val()).css("display", "inline-block");
	});
});
</script>
<section id="memberList-container">
	<h2>회원관리</h2>
	<!-- 검색 시작 -->
	<div id="search-container">
		검색타입 :
		<select id="searchType">
			<option value="memberId">아이디</option>
			<option value="memberName">회원명</option>
			<option value="gender">성별</option>
		</select>
		<div id="search-memberId">
			<!-- 아이디 검색폼 -->
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden"
					   name="numPerPage"
					   value="<%=numPerPage%>" />		   						   
				<input type="hidden" 
					   name="searchType"
					   value="memberId" />
				<input type="search"
					   name="searchKeyword"
					   size="25"
					   placeholder="검색할 아이디를 입력하세요." />
				<button type="submit">검색</button>
			</form>
			</div>
			<div id="search-memberName">
			<!-- 회원명 검색폼 -->
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden"
					   name="numPerPage"
					   value="<%=numPerPage%>" />
				<input type="hidden" 
					   name="searchType"
					   value="memberName" />
				<input type="search"
					   name="searchKeyword"
					   size="25"
					   placeholder="검색할 회원명을 입력하세요." />
				<button type="submit">검색</button>
			</form>
			</div>
			<div id="search-gender">
			<!-- 성별 검색폼 -->
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden"
					   name="numPerPage"
					   value="<%=numPerPage%>" />
				<input type="hidden" 
					   name="searchType"
					   value="gender" />
				<input type="radio"
					   name="searchKeyword"
					   value="M"
					   id="gender0" />
				<label for="gender0">남</label>
				<input type="radio"
					   name="searchKeyword"
					   value="F"
					   id="gender1" />
				<label for="gender1">여</label>
				<button type="submit">검색</button>
			</form>
		</div>
	</div>
	<!-- 검색 끝 -->
	<table id="tbl-member">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>성별</th>
				<th>나이</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>취미</th>
				<th>가입일</th>
			</tr>
		</thead>
		<tbody>
			<%
				if (list == null || list.isEmpty()) {
			%>
			<tr>
				<td colspan="9" align="center">
				검색결과가 없습니다.
				</td>
			</tr>
			<%
				} else {
					for (Member m : list) {
			%>
			<tr>
				<td>
					<a href="<%=request.getContextPath()%>/member/memberView?memberId=<%=m.getMemberId()%>">
						<%=m.getMemberId() %>
					</a>
				</td>
				<td><%=m.getMemberName() %></td>
				<td><%="M".equals(m.getGender())?"남":"여" %></td>
				<td><%=m.getAge() %></td>
				<td><%=m.getEmail()!=null?m.getEmail():"" %></td>
				<td><%=m.getPhone() %></td>
				<td><%=m.getAddress()!=null?m.getAddress():"" %></td>
				<td><%=m.getHobby()!=null?m.getHobby():"" %></td>
				<td><%=m.getEnrollDate() %></td>
			</tr>
			<%
				}
				}
			%>

		</tbody>
	</table>
	<div id="pageBar">
	<%=pageBar %>
	</div>
</section>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>
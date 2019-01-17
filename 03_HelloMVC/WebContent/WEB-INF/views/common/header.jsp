<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.member.model.vo.*" %>
<%
	/* Member memberLoggedIn = (Member)request.getAttribute("memberLoggedIn"); */
	Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
	
	// 전송된 쿠키확인
	boolean saveId = false;
	String memberId = "";
	Cookie[] cookies = request.getCookies(); // 배열 타입을 리턴한다.
	// System.out.println("브라우저가 전송한 쿠키목록@header.jsp");
	// System.out.println("-------------------------------------");
	for(Cookie c : cookies){
		String key = c.getName();
		String value = c.getValue();
	// System.out.printf("%s = %s\n", key, value);
		if("saveId".equals(key)){
			saveId = true;
			memberId = value; // abcde
		}
	}
	System.out.println("-------------------------------------");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello MVC</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<script src="<%=request.getContextPath() %>/js/jquery-3.3.1.js"></script>
<script>
function loginValidate(){
	if($("#memberId").val().trim().length == 0){
		alert("아이디를 입력하세요.");
		$("#memberId").focus();
		return false; // 폼 전송 방지
	}
	if($("#password").val().trim().length == 0){
		alert("비밀번호를 입력하세요.");
		$("#password").focus();
		return false; // 폼 전송 방지
	}
		return true;
}

/* 마이페이지 이동함수 */
function goMyPage(){
	<%-- location.href="<%=request.getContextPath() %>/member/memberView?memberId=<%=memberLoggedIn.getMemberId() %>"; --%>
	// null인 객체(memberLoggedIn)에 대해 메소드를 실행시키려고 했기 때문에 에러 발생
	// 로그인 하기 전에는 memberLoggedIn가 null이기 때문
}

</script>
</head>
<body>
   <div id="container">
      <header>
         <h1>Hello MVC</h1>
         <!--로그인 시작  -->
         <div class="login-container">
         <%if(memberLoggedIn == null){ %>
            <form action="<%=request.getContextPath()%>/member/login" method="post" id="loginFrm">
               <table>
                  <tr>
                  <td>
                     <input type="text" 
                     	    name="memberId"
                     	    id="memberId"
                     	    value="<%=memberId %>"
                     	    placeholder="아이디" />
                  </td>
                  <td></td>
                  </tr>
                  <tr>
                     <td>
                        <input type="password" 
                        	   name="password"
                        	   id="password"
                        	   placeholder="비밀번호" />
                     </td>
                     <td>
                     	<input type="submit" 
                     		   value="로그인" 
                     		   onclick="return loginValidate();"/>
                     </td>
                  </tr>
                  <tr>
                  	<td colspan="2">
                  		<input type="checkbox" 
                  			   name="saveId"
                  			   id="saveId" 
                  			   <%=saveId?"checked":"" %>/>
                  		<label for="saveId">
                  		아이디저장
                  		</label>
                  		&nbsp;&nbsp;
                  		<input type="button" 
                  			   value="회원가입"
                  			   onclick="location.href='<%=request.getContextPath() %>/member/memberEnroll'" />
                  	</td>
                  </tr>
               </table>
         
         </form>
         <% }
         else {%>
         	<table id="logged-in">
         		<tr>
         			<td>
         				<%=memberLoggedIn.getMemberName() %>님,
         				안녕하세요 :)
         			</td>
         		</tr>
         		<tr>
         			<td>
         				<input type="button" 
         					   value="내정보보기"
         					   onclick='location.href="<%=request.getContextPath() %>/member/memberView?memberId=<%=memberLoggedIn.getMemberId() %>";' />
         						<!-- // 물음표 기준 왼쪽은 url, 오른쪽은 (사용자)data -->
         				&nbsp;
         				<input type="button" 
         					   value="로그아웃" 
         					   onclick="location.href='<%=request.getContextPath() %>/member/logout'" />
         			</td>
         		</tr>
         	</table>
         <% } %>
         </div>
         <!-- 로그인끝 -->
         <!-- 메인메뉴 시작 -->
         <!-- nav>ul.main-nav>li*3 -->
         <nav>
         	<ul class="main-nav">
         		<li><a href="#">Home</a></li>
         		<li><a href="#">공지사항</a></li>
         		<li><a href="<%=request.getContextPath() %>/board/boardList">게시판</a></li>
         		<li><a href="<%=request.getContextPath() %>/photo/photoList">사진게시판</a></li>
         		
         		<%-- 관리자인 경우만 보이기 --%>
					<%
						if (memberLoggedIn != null && "admin".equals(memberLoggedIn.getMemberId())) {
					%>
					<li><a href="<%=request.getContextPath()%>/admin/memberList">회원관리</a></li>
					<%
						}
					%>
				</ul>
         </nav>
         <!-- 메인메뉴 끝 -->
         
      </header>
      
      <section id="content">
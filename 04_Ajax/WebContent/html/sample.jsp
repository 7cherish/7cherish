<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.kh.model.vo.*" %>
<%
	List<Member> list = (List<Member>)request.getAttribute("list");
%>
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
<table id="tbl-member">
	<tr>
		<th>프로필</th>
		<th>이름</th>
		<th>전화번호</th>
	</tr>
	<% if(!list.isEmpty()){
		for(Member m: list){
	%>
		<tr>
			<td>
				<img src="<%=request.getContextPath() %>/images/<%=m.getProfile() %>"/>
			</td>
			<td><%=m.getName() %></td>
			<td><%=m.getPhone() %></td>
		</tr>
	<%   } 
		}%>

</table>


<!-- 웹서버 프로그램(개발환경)을 만들기 위해 필요한 것?
java jdk 설치 -> 통합개발환경(IDE) -> 브라우저

java se(standard edition) 
java ee(enterprise edition) 자바보다 지원되는게 더 많다.

dao클래스를 인터페이스제어

인터페이스 제어 : 멤버 서비스를 만들어 했을때 멤버 서비스 인터페이스를 만들고
실제 만든 일반클래스에서 멤버 서비스를 구현하는 것이다.
구현 한다는 것은 인터페이스가 틀을 구현해준다는것
어떤 메소드를 만들라고

그때 기본적으로 들어가는게 CRUD INSERT(CREATE), SELECT(READ OR RETRIEVE), UPDATE(UPDATE), DELETE(OR DESTROY)

MYSQL MSSQL SYBAS => DBMS

DAO 에서 STATEMENT 객체를 만들었었죠
우리는 PREPAREDSTATEMENT를 사용했었는데

STATEMENT에서
EXCUTEQUERY(DQL) RETURN타입 : RESULTSET, EXCUTEUPDATE(DML) : INT

SERVLET ----------------->  DAO
LOGINCHECK -------------->
selectOne --------------->
User를 리턴
실패했을때 비밀번호가 틀려서 실패했는지, 아이디가 존재해서 실패했는지 알 수 없다.
null이 되면 로그인 실패

로그인을 했을때 비밀번호 변경한지 90일이 지났는지 확인하고
사용자에게 할 수 있도록 구현해보기

날짜 계산 number
하루를 1로 쳐서 number 타입이 리턴됐다
boolean으로 돌려주고
오라클에 데이터 타입

SELECT TRUNC(SYSDATE) - TO_DATE('20171110', 'YYYYMMDD') FROM DUAL; -->

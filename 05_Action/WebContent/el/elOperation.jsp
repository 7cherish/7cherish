<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.action.model.vo.Person,
				 java.util.*" %>
<%
	String str = "안녕";
	String str_ = new String("안녕");
	// 자바에서 리터럴과 리터럴을 비교하면 true이지만,
	// 리터럴과 객체를 비교하기 때문에 false이다.
	System.out.println(str==str_);
	
	int big = 10;
	int small = 3;
	
	Person p = new Person("홍길동", '남', 30);
	Person p_ = new Person("홍길동", '남', 30);
	// 객체의 주소값을 비교하기 때문에 false이다.
	System.out.println(p==p_);
	
	List<String> list = new ArrayList<>();
	list.add(str);
	
	pageContext.setAttribute("str", str);
	pageContext.setAttribute("str_", str_);
	pageContext.setAttribute("big", big);
	pageContext.setAttribute("small", small);
	pageContext.setAttribute("p", p);
	pageContext.setAttribute("p_", p_);
	pageContext.setAttribute("list", list);
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>el : Operation</title>
</head>
<body>
	<h2>el을 통한 간단한 연산처리</h2>
	<p>
	왜 간단한 연산처리인가? <br />
	el은 본디 연산처리목적이 아니라, 속성값을 화면단에 뿌리기 위한 용도이다. <br />
	단, 산술, 논리연산등 지원한다.
	</p>
	<h3>산술연산</h3>
	10 * 7 = ${10 * 7} <br />
	100 / 3 = ${100/3} <br />
	100 / 3 = ${100 div 3 } <br />
	15 % 4 = ${15 % 4 } <br />
	15 % 4 = ${15 mod 4 } <br />
	
	<h3>논리연산</h3>
	str==str_ => 자바비교에서는 false <br />
	el은 값비교를 한다. <br /><br />
	str==str_ => <%=str==str %> <br />
	str==str_ => ${str==str} <br />
	str==str_ => ${str eq str_ } <br />
	
	<br />
	
	<h3>비교연산</h3>
	big > small : ${big > small } <br />
	big > small : ${big gt small } <br />
	
	big &lt; small : ${big < small } <br />
	big &lt; small : ${big lt small } <br />
	
	big >= small : ${big >= small } <br />
	big >= small : ${big ge small } <br />
	
	big &lt;= small : ${big <= small } <br />
	big &lt;= small : ${big le small } <br />
	
	<br />
	p==p_ : ${p==p_ } <br />
	p==p_ : ${p eq p_ } <br />
	
	<br />
	
	<p>empty : 객체가 null인지 또는 null은 아닌데 비어있는지 확인하는 연산자</p>
	\${empty list } = ${empty list } <br />
	
	<p>논리연결연산자/부정연산자</p>
	${true and true} 또는 ${true && true} <br />
	${true or true} 또는 ${true || true} <br />
	${!(big < small)}
	
</body>
</html>
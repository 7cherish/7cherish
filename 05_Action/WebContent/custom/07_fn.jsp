<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
uri : c.tld파일의 uri와 일치하도록 할 것 
(WebAppLibraries / taglibs-standard-impl / META-INF / maven / c.tld (이건 core꺼) 
-->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07_fn</title>
</head>
<body>
	<h2>jstl : fn</h2>
	<c:set var="str" value="How are you?"/>
	str : ${str } <br />
	모두 대문자로 : ${fn:toUpperCase(str) } <br />
	모두 소문자로 : ${fn: toLowerCase(str) } <br />
	are의 index : ${fn: indexOf(str, "are") } <br />
	are -> were : ${fn:replace(str, "are", "were") } <br />
	<!-- replace는 대상 문자열을 실제로 바꾸는게 아니다. -->
	replace후 str : ${str } <br />
	문자열 길이 : ${fn:length(str) }
	
<!-- 
<%-- 	Function, 함수

기능 : 컬렉션 처리,  String 처리

접두어(Prefix) : fn

directive : <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



JSTL functions은 JSTL에서 제공하는 각종 함수를 사용해서 문자열이나, 컬렉션들을 처리한다.

fn태그는 단독으로 사용할 수 없고 EL 표현식 내에서 사용한다.



● boolean contains(String sting, String substring) : string이 substring을 포함하면 true값을  리턴 한다.

● boolean containsIgnoreCase(String string, String substring) : 대소문자에 관계없이, string이 substring을 포함하면 true 리턴

${fn:contains("helloworld", "world")} // true

${fn:containsIgnoreCase("hello world!", "WoRLd")} // true

      

● boolean startsWith(String string, String prefix) : string이 prefix로 시작하면 true값을 리턴 한다.

● boolean endsWith(String string, String substring) : string이 suffix로 끝나면 true값을 리턴 한다. 

${fn:startsWith("hello world!", "ld!")} // false

${fn:endsWith("hello world!", "ld!")} // true



● String escapeXml(String string)

 : string에서 XML, HTML의 < >& ' " 문자들을 각각 &lt; &gt; &amp; &#039; &#034;로 바꿔준 뒤 문자열을 리턴 한다. 

<c:out value="${fn:escapeXml('<>')}"/> // &lt;&gt;



● int indexOf( java.lang.String  string, java.lang.String  substring) 

 : string에서 substring이 처음으로  나타나는 인덱스를 리턴 한다.

${fn:indexOf("abcdefg", "f")} // 5



● String[] split(String string, String separator) : string 내의 문자열을 separator에 따라 잘라내서 잘려진 문자열들을 배열로 리턴한다.

● String join(String[], String separator) : 배열 요소들을 separator를 구분자로 하여 모두 연결해서 리턴 한다.

<c:set var="texts" value="${fn:split('Hi My name is waldo', ' ')}"/>

<c:out value="${fn:join(texts, '-')}"/> // Hi-My-name-is-waldo



// jstl이나 el에 문자열 리터럴로 배열을 직접 생성하는 예제는 검색결과가 없다. 대부분 fn:split이나 jsp:useBean을 사용했다.



● int length(Object  item)

 : item이 배열이나 컬렉션이면 요소의 개수를, 문자열이면 문자의 개수를 리턴 한다.

<c:set var="texts" value="${fn:split('Hi My name is waldo', ' ')}"/>

${fn:length(texts)} // 5

${fn:length("123456")} // 6



● String replace(String string, String before, String after)

 : string 내에 있는 before 문자열을 after 문자열로 모두 바꿔서 리턴 한다.

${fn:replace("hi hello", "hello", "hi")} // hi hi



// replace 함수는 HTML에서 공백과 줄바꿈을 표현할 때 사용할 수 있다.

${fn:replace("hell            o          o       ~", " ", "&nbsp;")} // hell            o          o       ~



<% pageContext.setAttribute("enter","\n"); %>

${fn:replace(info.text, enter, '<br/>') 엔터처리



<%-- <% pageContext.setAttribute("enter","\n"); %> --%>

${fn:replace(fn:replace(fn:escapeXml(info.content_txt), enter, '<br/>') , ' ', '&nbsp;')} // 엔터와 공백 처리



● String substring(String string, int begin, int end) : string에서 begin 인덱스에서 시작해서 end 인덱스에 끝나는 부분의 문자열을 리턴.

● String substringAfter(String string, String substring) : string에서 substring이 나타나는 이후의 부분에 있는 문자열을 리턴 한다.

● String substringBefore(String string, String substring) : string에서 substring이 나타나기 이전의 부분에 있는 문자열을 리턴 한다.

${fn:substring(text, 3, 19)} // My name is waldo

${fn:substringAfter(text, "Hi ")} // My name is waldo

${fn:substringBefore(text, "waldo")} // Hi My name is



● String toLowerCase(String string) : string을 모두 소문자로 바꿔 리턴한다.

● String toUpperCase(String string) : string을 모두 대문자로 바꿔 리턴한다.

<c:set var="text" value="Hi My name is waldo"/>

${fn:toLowerCase(text)} // hi my name is waldo

${fn:toUpperCase(text)} // HI MY NAME IS WALDO



● String trim(String string)

 : string 앞뒤의 공백을 모두 제거하여 리턴 한다. 

<c:set var="text" value="          blank spcae          "/>

${fn:length(text)}  // 31

<c:set var="text" value="${fn:trim(text)}"/>

 

${fn:length(text)}  // 1 --%>
 -->
</body>
</html>


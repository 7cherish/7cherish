<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>INDEX</title>
<style>
body {
	margin: 0px auto;
	text-align: center;
}

h1{
	position: relative;
	top: 160px;
	font-size: 50px;
}

h2{
	margin: 0px auto;
	text-align: center;
}

ul{
	margin: 0px auto;
	text-align: center;
}

li {
	margin: 0px 130px auto;
	list-style-type: none;
	padding: 0px 10px;
}

a {
	text-decoration: none;
	color: gray;
	font-size: 13pt;
}

a:hover {
	color: red;
}

#wrapper{
	padding: 34px 36px 44px 17px;
	border: 1px solid;
	border-radius: 10px 10px 10px 10px;
	width: 500px;
	margin: 200px auto;
}

#Action_EL{
	margin-top: 10px;
}
</style>
</head>
<body>
		<h1>INDEX</h1>
	<section id="wrapper">
	<div id="Action_EL">
		<h2>Action & EL</h2>
		<br />
		<ul>
			<li><a href="standard/useBean/personView.jsp">personView.jsp</a></li>
			<li><a href="el/elView.jsp">elView.jsp</a></li>
			<li><a href="el/elParam.jsp">elParam.jsp</a></li>
			<li><a href="el/elParamEnd.jsp">elParamEnd.jsp</a></li>
			<li><a href="el/elObject.jsp">elObject.jsp</a></li>
			<li><a href="el/elOperation.jsp">elOperation.jsp</a></li>
			<li><a href="test/personBeanHandler.jsp">personBeanHandler.jsp</a></li>
		</ul>
	</div>
	<br /><br />
	<div id="CustomAction">
		<h2>CustomAction & JSTL</h2>
		<br />
		<ul>
			<li><a href="custom/01_multiply.html">01_multiply.html</a></li>
			<li><a href="custom/01_multiplyEnd.jsp">01_multiplyEnd.jsp</a></li>
			<li><a href="custom/02_if.jsp">02_if.jsp</a></li>
			<li><a href="custom/03_ifStr.jsp">03_ifStr.jsp</a></li>
			<li><a href="custom/04_choose.html">04_choose.html</a></li>
			<li><a href="custom/04_chooseEnd.jsp">04_chooseEnd.jsp</a></li>
			<li><a href="custom/05_forEach.jsp">05_forEach.jsp</a></li>
			<li><a href="custom/06_fmt.jsp">06_fmt.jsp</a></li>
			<li><a href="custom/07_fn.jsp">07_fn.jsp</a></li>
		</ul>
	</div>
	</section>
</body>
</html>
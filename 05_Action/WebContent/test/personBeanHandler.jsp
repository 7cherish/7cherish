<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>personBeanHandler</title>
</head>
<body>
	<h2>회원가입</h2>
	<form action="${pageContext.request.contextPath }/personBeanHandler" name="personFrm" method="post">
		<input type="text" name="name" placeholder="성명" />
		<br />
		<input type="radio" name="gender" id="male" value="남" />
		<label for="male">남</label>
		<input type="radio" name="gender" id="female" value="남" />
		<label for="female">여</label>
		<br />
		<input type="number" name="age" placeholder="나이" />
		
		<input type="hidden" name="view" />
		<br />
		<input type="button" value="전송:scriptlet" onclick="goSubmit(0);" />
		<input type="button" value="전송:jsp:useBean" onclick="goSubmit(1);" />
		<input type="button" value="전송:el" onclick="goSubmit(2);" />
		
	</form>
<script>
function goSubmit(flag) {
	var frm = document.personFrm;
	if (flag == 0) {
		frm.view.value = 'scriptlet.jsp';
	} else if (flag == 1) {
		frm.view.value = 'action.jsp';
	} else {
		frm.view.value = 'el.jsp';
	}
	frm.submit();
}
</script>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script>
/* 회원가입 유효성검사 */
function enrollValidate(){
	// 아이디 자리수는 4글자 이상 검사
	var $memberId_ = $("#memberId_");
	if($memberId_.val().length < 4){
		alert("아이디는 4글자 이상 입력하세요.");
		$memberId_.select();
		return false;
	}
	
	// 아이디중복검사여부 체크
	var idValid = $("#idValid").val();
	if(idValid == 0){
		alert("아이디 중복검사해주세요.");
		return false;
	}
	
	return true;
}

$(function(){
	// 패스워드 일치여부 검사 -> 이벤트핸들러 작성
	var $password_ = $("#password_");
	var $password__ = $("#password__");
	$password__.on("focusout", function(){
		if($password_.val() != $password__.val()){
			alert("패스워드가 일치하지 않습니다.");
		}
	});
});

function checkIdDuplicate(){
	// 아이디 중복검사폼을 전송
	var memberId = $("#memberId_").val().trim();
	if(memberId.length < 4){
		alert("아이디는 4글자 이상 가능합니다.");
		return;
	}
	
	// 팝업창을 target으로 폼전송
	var target = "checkIdDuplicate";
	// 첫 번째 인자 url은 생략, form의 action값이 이를 대신한다.
	var popup = open("", target, "left=300px, top=100px, height=200px, width=300px");

	checkIdDuplicateFrm.memberId.value = memberId;
	
	// 폼의 대상을 작성한 popup을 가리키게 한다. 이때 이용하는게 popup창의 이름 => target
	checkIdDuplicateFrm.target = target;
	checkIdDuplicateFrm.submit();
}
</script>
<form action="<%=request.getContextPath() %>/member/checkIdDuplicate"
	  method="post"
	  name="checkIdDuplicateFrm">
	<input type="hidden" name="memberId" />
</form>
<section id="enroll-container">
	<h2>회원가입 정보 입력</h2>
	<form action="<%=request.getContextPath() %>/member/memberEnrollEnd" 
		  method="post"
		  name="memberEnrollFrm"
		  onsubmit="return enrollValidate();">
		  <!-- table>tr>th+td -->
		  <table>
		  	<tr>
		  		<th>아이디</th>
		  		<td>
		  			<input type="text" 
		  				   name="memberId" 
		  				   id="memberId_"
		  				   placeholder="4글자 이상 입력하세요."
		  				   required />
		  			<input type="button"
		  				   value="중복검사" 
		  				   onclick="checkIdDuplicate();" />
		  			<!-- 검사여부 알려주는 태그 -->
		  			<input type="hidden"
		  				   name="idValid"
		  				   id="idValid"
		  				   value="0" />
		  		</td>
		  	</tr>
		  	<tr>
		  		<th>패스워드</th>
		  		<td>
		  			<input type="password" 
		  				   name="password" 
		  				   id="password_"
		  				   required />
		  		</td>
		  	</tr>
		  	<tr>
		  		<th>패스워드 확인</th>
		  		<td>
		  			<input type="password"
		  				   id="password__"
		  				   required />
		  		</td>
		  	</tr>
		  	<tr>
		  		<th>이름</th>
		  		<td>
		  			<input type="text" 
		  				   name="memberName" 
		  				   id="memberName"
		  				   required />
		  		</td>
		  	</tr>
		  	<tr>
		  		<th>나이</th>
		  		<td>
		  			<input type="number" 
		  				   name="age" 
		  				   id="age"
		  				   required />
		  		</td>
		  	</tr>
		  	<tr>
		  		<th>이메일</th>
		  		<td>
		  			<input type="email" 
		  				   name="email" 
		  				   id="email"
		  				   placeholder="ex)abc@xyz.com" />
		  		</td>
		  	</tr>
		  	<tr>
		  		<th>휴대폰</th>
		  		<td>
		  			<input type="tel" 
		  				   name="phone" 
		  				   id="phone"
		  				   placeholder="ex)01012345678"
		  				   required />
		  		</td>
		  	</tr>
		  	<tr>
		  		<th>주소</th>
		  		<td>
		  			<input type="text" 
		  				   name="address" 
		  				   id="address" />
		  				   <!-- DB에서 not null인건 반드시 required속성 적어줄것! -->
		  		</td>
		  	</tr>
		  	<tr>
		  		<th>성별</th>
		  		<td>
		  			<input type="radio" 
		  				   name="gender" 
		  				   id="gender0"
		  				   value="M" checked />
		  			<label for="gender0">남</label>
		  			<input type="radio" 
		  				   name="gender" 
		  				   id="gender1"
		  				   value="F" checked />
		  			<label for="gender1">여</label>
		  		</td>
		  	</tr>
		  	<tr>
		  		<th>취미</th>
		  		<td>
		  			<input type="checkbox" 
		  				   name="hobby" 
		  				   id="hobby0"
		  				   value="운동" />
		  			<label for="hobby0">운동</label>
		  			<input type="checkbox" 
		  				   name="hobby" 
		  				   id="hobby1"
		  				   value="등산" />
		  			<label for="hobby1">등산</label>
		  			<input type="checkbox" 
		  				   name="hobby" 
		  				   id="hobby2"
		  				   value="독서" />
		  			<label for="hobby2">독서</label>
		  			<br />
		  			<input type="checkbox" 
		  				   name="hobby" 
		  				   id="hobby3"
		  				   value="게임" />
		  			<label for="hobby3">게임</label>
		  			<input type="checkbox" 
		  				   name="hobby" 
		  				   id="hobby4"
		  				   value="여행" />
		  			<label for="hobby4">여행</label>
		  		</td>
		  	</tr>
		  </table>
		  <input type="submit" value="회원가입" />
		  <input type="reset" value="초기화" />
	</form>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
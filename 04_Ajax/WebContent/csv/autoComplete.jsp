<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax : csv : autoComplete</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<style>
ul#autoComplete {
	min-width: 171px;
	border: 1px solid gray;
	display: none;
	padding: 0;
	margin: 0;
}

ul#autoComplete li {
	padding: 0 10px;
	list-style: none; /* 리스트앞의 불릿 제거 */
	cursor: pointer;
}

ul#autoComplete li.selected {
	background: lightgreen;
	color: white;
}

span.srchVal {
	color: red;
}
</style>
</head>
<body>
	<h2>ajax : csv : autoComplete</h2>
	<h3>우리반 친구 검색하기</h3>
	<input type="text" id="srchName" />
	<br />
	<ul id="autoComplete">
		<!-- 테스트 목록 -->
		<li>가나다</li>
		<li>나다라</li>
		<li>다라마</li>
		<li>라마바</li>
		<li>마바사</li>
	</ul>
<script>
//실시간검색
$("#srchName").keyup(function(e){
   console.log(e.key);
   var selected = $(".selected"); 
   var li = $("#autoComplete li");
   
   // 키버튼 이벤트에 따라 클래스 추가/삭제
   if(e.key=='ArrowDown'){ 
      
      if(selected.length == 0){ //하나도 선택되어지지 않음
          $("#autoComplete li:first").addClass("selected");  //선택되는 순간 해당 태그에 class="selected" 를 생성한다
      }else if(selected.is(li.last())){
         //처리코드 없음
      }else{ //무언가 선택되면 > 다음요소로 넘긴다
         selected.removeClass("selected")  //선택되면 해당 클래스를 지우고
                  .next().addClass("selected"); //다음요소를 찾아서 더해주어라 
      }
      
   }else if(e.key=='ArrowUp'){
      if(selected.length==0){
         //처리코드 없음
      }else if(selected.is(li.first())){
         //맨 위의 li 태그이면 아무것도 하지 않음
         selected.removeClass("selected");
      }else{
         selected.removeClass("selected").prev().addClass("selected");
      }
      
      
   }else if(e.key=='Enter'){ //엔터를 치면 선택되어있는 값을 val에 넣는다
      //1. 값선택 
      $(this).val(selected.text());
         //2. 검색어 목록은 감추고. li 태그 모두 삭제
         $("#autoComplete").hide().children().remove();
      
   }else{   // 이때만 서버로 보낸다(사용자 입력값이라 간주)
    
      var srchName=$(this).val().trim();
      
      //사용자가 공백만을 입력한 경우 ajax 요청 하지 않는다
      if(srchName.length==0)  return;      
           
      $.ajax({
         url : "<%=request.getContextPath()%>/csv/autoComplete.do",
         type : "post",
         data : "srchName=" + srchName,
         //date: {srchName:srchName},  //속성보내기 2가지 방법
                       
           success: function(data){
              console.log(data);
              
              var nameArr = data.split(",");
              var html = "";
              for(var i=0; i<nameArr.length; i++){
                 html += "<li>"+nameArr[i].replace(srchName,"<span class='srchVal'>"+srchName+"</span>")+"</li>";
              }
              //서버로 부터 전달된 값이 있는 경우에만 보이게 처리 
              if(data.length != 0)
                 $("#autoComplete").html(html).css('display','inline-block'); //집어넣기//ajax 다녀온 후 보여주기(show)                         
                                  
                 /* success 함수에서 적용 
                 //마우스 클릭 이벤트에 따라 추가/삭제 : 새로 생성된 요소에 대하여 이벤트 핸들러 추가
               $("#autoComplete li").click(function(){   
                  //1. 클릭하면 해당 정보를 인풋태그의 값으로 넣어주기  
                  $("#srchName").val($(this).text());
                    //2. 검색어 목록은 감추고. li 태그 모두 삭제
                  $("#autoComplete").hide().children().remove();   
               });  
            
               //마우스 호버 이벤트에 따라 클래스 추가/삭제 
               $("#autoComplete li").hover(
                 function(){ //마우스 엔터
                  $(this).siblings().removeClass("selected");    
                  $(this).addClass("selected");
               },function(){ //마우스 오버
                  $(this).removeClass("selected");               
               });
               */
              
           }//success 함수
     
      }); //end of ajax  
     
   }//end of else  
}); //#srchName key up function

$(function(){
   
   $("#autoComplete").on("click","li",function(){   
         //1. 클릭하면 해당 정보를 인풋태그의 값으로 넣어주기  
         $("#srchName").val($(this).text());
           //2. 검색어 목록은 감추고. li 태그 모두 삭제
         $("#autoComplete").hide().children().remove();   
   }); 
   
   $("#autoComplete").on("mouseenter","li",function(){ //마우스 엔터
             $(this).siblings().removeClass("selected");    
             $(this).addClass("selected");
    });
   
   $("#autoComplete").on("mouseleave","li",function(){ //마우스 엔터
            $(this).removeClass("selected");    
   });
   
});

</script>   
</body>
</html>
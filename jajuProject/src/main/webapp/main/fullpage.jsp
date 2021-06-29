<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

ul {
	list-style: none;
}
/* 전체 감싸지는 부분 */
.slider-wrap {
	background: skyblue;
	height: 100vh;
	    margin-top: -50px;
}

</style>
<link rel="stylesheet" href="/jaju/css/test_index.css">

</head>
<body>
					
			<div class="test_index_div stagger-item">
				<div style="margin-top:0px;"
					class="hero-full-container background-image-container white-text-container stagger-item">
					<div class="container stagger-item">
						<div class="row stagger-item">
							<div class="col-xs-12 stagger-item">
			
								<input type="hidden" id="typing-txt" value="자주마켓에 오신 걸 환영합니다!">
			
								<h3 id="typing"></h3>
			
								<h1>
									<a class="highlight stagger-item">자주마켓</a>
								</h1>
								<p class="stagger-item">우리 더 자주, 거래해요!</p>
			
								<br>
								<!-- 자주마켓 소개로 이동하게하기. -->
								<a href=""
									class="btn btn-default btn-lg stagger-item" title="">자주마켓 소개</a>
							</div>
						</div>
					</div>
				</div>
			</div>


	<!-- 슬라이드 영역  -->
	<div class="section-container">
		<div class="container text-center">
			<div class="section" style="padding-top: 0;">
				<input type="radio" name="slide" id="slide01" checked> 
				<input type="radio" name="slide" id="slide02">
				<input type="radio"	name="slide" id="slide03">
				<div class="slidewrap">

					<ul class="slidelist">
						<!-- 슬라이드 영역 -->
						
						<li class="slideitem"><a>
								<div class="textbox">
									<h3></h3>
									<p></p>
								</div> <img src="/jaju/jajuImage/jaju_advertise.png">
						</a></li>
						
						<li class="slideitem"><a>

								<div class="textbox">
									<h3>당신 근처의 자주마켓</h3>
									<p>
										중고 거래부터 동네 정보까지, 이웃과 함께해 <br>가깝고 따뜻한 당신의 근처를 만들어요.
									</p>
								</div> <!-- <img src="/jaju/jajuImage/slide1.png"> -->
						</a></li class="slideitem">

						<li class="slideitem"><a>

								<div class="textbox">
									<h3></h3>
									<p></p>
								</div> <img src="/jaju/jajuImage/slide2.png">
						</a></li>



						<!-- 좌,우 슬라이드 버튼 -->
						<div class="slide-control">
							<div>
								<label for="slide03" class="left"></label> <label for="slide02"
									class="right"></label>
							</div>
							<div>
								<label for="slide01" class="left"></label> <label for="slide03"
									class="right"></label>
							</div>
							<div>
								<label for="slide02" class="left"></label> <label for="slide01"
									class="right"></label>
							</div>
						</div>

					</ul>
					<!-- 페이징 -->
					<ul class="slide-pagelist">
						<li><label for="slide01"></label></li>
						<li><label for="slide02"></label></li>
						<li><label for="slide03"></label></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
<!-- 슬라이드 테스트 중  -->




<!-- 
	<button type='button' id="modal_btn">모달창아 나와랏</button>
	<div class="black_bg"></div>
	<div class="modal_wrap">
		<div class="modal_close">
			<a href="#">close</a>
		</div>
		<div>모달창 내용</div>
	</div> -->

<script>
//타이핑 기법
$(function(){
	typingStart();
	typing();
});
var typingEnd=false;
//highlight stagger-item
function typingStart(){   //typingStart() 함수를 실행시키면 타이핑 효과가 시작되게 된다. 
  if(!typingEnd){
    var typingBool = false;
    var typingIdx=0;
    var typingTxt = $("#typing-txt").val(); // 타이핑될 텍스트를 가져온다
    typingTxt=typingTxt.split(""); // 한글자씩 자른다.
    if(typingBool==false){ // 타이핑이 진행되지 않았다면
       typingBool=true;

       var tyInt = setInterval(typing,100); // 반복동작(타이핑 시간을 제어할 수 있음)
     }

    function typing(){
      if(typingIdx<typingTxt.length){ // 타이핑될 텍스트 길이만큼 반복
        $("#typing").append(typingTxt[typingIdx]); // 한글자씩 이어준다.
        typingIdx++;
      } else{
        clearInterval(tyInt); //끝나면 반복종료
        typingEnd=true;
      }
    }
  }
}

</script>
</body>
</html>

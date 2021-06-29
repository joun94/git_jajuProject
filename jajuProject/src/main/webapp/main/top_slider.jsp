<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

.single-item {
	width: 100%;
	margin: 0 auto;
}
.single-item img{
 width: 100%;
 height: auto;
 align-content: center;
}
.single-item .slick-slide img {
    display: block;
    margin-top: 70px;
}
.single-item .slick-prev.slick-arrow {
	position: absolute;
	display: none!important;
	/*left: -85px;*/
	top: 50%;
	transform: translateY(-50%);
	background: url(/img/bx_left.png);
	display: hidden;
	text-indent: -9999px;
	width: 60px;
	height: 60px;
	background-size: cover;
}

.single-item .slick-next.slick-arrow {
	position: absolute;
	display: none!important;
	right: -85px;
	top: 50%;
	transform: translateY(-50%);
	background: url(/img/bx_right.png);
	display: hidden;
	text-indent: -9999px;
	width: 60px;
	height: 60px;
	background-size: cover;
	color:#333;
}
.slick-next:before, .slick-prev:before {
	font-family: slick;
	font-size: 20px;
	line-height: 1;
	opacity: .75;
	color: #333 !important;
	-webkit-font-smoothing: antialiased;
}

</style>
<!-- 제이쿼리 불러오기 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<!-- Slick 불러오기 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.min.css">
</head>

<body>

	<div class="single-item">
		<div>
			<img src="/jaju/jajuImage/top_slider02.png">
		</div>

		<div>
			<img src="/jaju/jajuImage/top_slider01.png">
		</div>

		<div>
			<img src="/jaju/jajuImage/top_slider03.png">
		</div>

		<div>
			<img src="/jaju/jajuImage/top_slider04.png">
		</div>
		
		
		<div>
			<img src="/jaju/jajuImage/top_slider05.png">
		</div>
		
		
		<div>
			<img src="/jaju/jajuImage/top_slider06.png">
		</div>
		

	</div>

	<script type="text/javascript"
		src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script type="text/javascript"
		src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
	<script>
		$(document)
				.ready(
						function() {
							/*// 옵셥 사용하지 않을 경우 
							$('.single-item').slick(); 
							
							}); */

							// 옵셥 사용할 경우
							$('.single-item')
									.slick(
											{
												slide : 'div', //슬라이드 되어야 할 태그 ex) div, li 
												infinite : true, //무한 반복 옵션	 
												slidesToShow : 1, // 한 화면에 보여질 컨텐츠 개수
												slidesToScroll : 1, //스크롤 한번에 움직일 컨텐츠 개수
												speed : 100, // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
												arrows : true, // 옆으로 이동하는 화살표 표시 여부
												dots : true, // 스크롤바 아래 점으로 페이지네이션 여부
												autoplay : true, // 자동 스크롤 사용 여부
												autoplaySpeed : 3000, // 자동 스크롤 시 다음으로 넘어가는데 걸리는 시간 (ms)
												pauseOnHover : true, // 슬라이드 이동	시 마우스 호버하면 슬라이더 멈추게 설정
												vertical : false, // 세로 방향 슬라이드 옵션
												prevArrow : "<button type='button' class='slick-prev'>Previous</button>", // 이전 화살표 모양 설정
												nextArrow : "<button type='button' class='slick-next'>Next</button>", // 다음 화살표 모양 설정
												dotsClass : "slick-dots", //아래 나오는 페이지네이션(점) css class 지정
												draggable : true, //드래그 가능 여부 
												enterPadding : '60px',
												responsive : [ // 반응형 웹 구현 옵션
												{
													breakpoint : 960, //화면 사이즈 960px
													settings : {
														//위에 옵션이 디폴트 , 여기에 추가하면 그걸로 변경
														slidesToShow : 3
													}
												}, {
													breakpoint : 768, //화면 사이즈 768px
													settings : {
														//위에 옵션이 디폴트 , 여기에 추가하면 그걸로 변경
														slidesToShow : 2
													}
												} ]

											});
						})
	</script>

</body>
</html>
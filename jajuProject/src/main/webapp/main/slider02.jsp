<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

@font-face {
	font-family: 'S-CoreDream-4Regular';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-4Regular.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

* {
	font-family: 'S-CoreDream-4Regular';
	-webkit-text-size-adjust: 100%;
}

html {
	-ms-text-size-adjust: 100%;
	-webkit-text-size-adjust: 100%
}

body {
	margin: 0
}

.slick-next:before, .slick-prev:before {
	font-family: slick;
	font-size: 20px;
	line-height: 1;
	opacity: .75;
	color: #333 !important;
	-webkit-font-smoothing: antialiased;
}

.slideWrap .col-md-12 {
	position: relative;
	min-height: 1px;
	padding-left: 15px;
	padding-right: 15px;
	text-align: center;
	
    margin-top: 50px;
	/*margin-bottom: 70px;*/
	width: 100%;
	height: 50px;
}

.slick-dotted.slick-slider {
    margin-bottom: 30px;
    margin-top: -25px;
}
h2 {
	font-family: 'S-CoreDream-4Regular';
	font-weight: 900;
	line-height: 1.1;
	color: #4457C0;
	margin-top: 25px;
	margin-bottom: 12.5px;
	font-size: 31px;
}

p {
	margin: 0 0 12.5px;
}

#slider-div02 div {
	margin-bottom: 10px;
	
}

#slider-div02 img {
	border: 1px solid #fff;
	border-radius: 6px;
}

#slider-div02 img {
	width: 100%;
	height: 100%;
}

.slideWrap {
	margin-top: -100px;
	width: 1505px;
	height: 560px;
	margin: 0 auto;
}

/*슬라이드 체크*/
.item-wrap{
	border: 1px solid #ededed;
	width:210px;
}
.item_image_div {
	position: relative;
	width: 100%;
	height: 220px;
}

.item_image_div>img {
	vertical-align: bottom;
}

.eKeqot {
	position: absolute;
	top: 0px;
	left: 0px;
	width: 100%;
	height: 100%;
}

.item_detail {
	padding: 15px 10px;
	height: 80px;
}

.item_subject {
	position: relative;
	font-size: 16px;
	padding-bottom: 5px;
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
}

.item_price_div {
	display: flex;
	-webkit-box-pack: justify;
	justify-content: space-between;
	-webkit-box-align: center;
	align-items: center;
	height: 20px;
}

.item_price {
	font-size: 14px;
	font-weight: 600;
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
}

.item_price::after {
	content: "원";
	font-size: 13px;
	margin-left: 3px;
}

*, ::before, ::after {
	box-sizing: border-box;
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

<div class="slideWrap">
		<!-- stlye 은 slick 영역 확인용 -->
		<div class="col-xs-12 col-md-12">
			<h2>MD 추천!</h2>
			<p>MD 추천 거래 물품이에요.</p>
		</div>

		<div style="padding: 200px; background-color: #fff;">



			<div id="slider-div02">

				<div class="item-wrap">
					<div class="item_image_div">
						<img src="/jaju/jajuImage/candle_warm.jpg">
					</div>
					<div class="item_detail">
						<div class="item_subject">제목</div>
						<div class="item_price_div">
							<div class="item_price">가격</div>
						</div>
					</div>
				</div>

				<div class="item-wrap">
					<div class="item_image_div">
						<img src="/jaju/jajuImage/chair02.jpg">
					</div>
					<div class="item_detail">
						<div class="item_subject">제목</div>
						<div class="item_price_div">
							<div class="item_price">가격</div>
						</div>
					</div>
				</div>
				
				<div class="item-wrap">
					<div class="item_image_div">
						<img src="/jaju/jajuImage/radio.jpg">
					</div>
					<div class="item_detail">
						<div class="item_subject">제목</div>
						<div class="item_price_div">
							<div class="item_price">가격</div>
						</div>
					</div>
				</div>		
						
				<div class="item-wrap">
					<div class="item_image_div">
						<img src="/jaju/jajuImage/candle.jpg">
					</div>
					<div class="item_detail">
						<div class="item_subject">제목</div>
						<div class="item_price_div">
							<div class="item_price">가격</div>
						</div>
					</div>
				</div>	
							
				<div class="item-wrap">
					<div class="item_image_div">
						<img src="/jaju/jajuImage/chair.jpg">
					</div>
					<div class="item_detail">
						<div class="item_subject">제목</div>
						<div class="item_price_div">
							<div class="item_price">가격</div>
						</div>
					</div>
				</div>		
						
				<div class="item-wrap">
					<div class="item_image_div">
						<img src="/jaju/jajuImage/radio.jpg">
					</div>
					<div class="item_detail">
						<div class="item_subject">제목</div>
						<div class="item_price_div">
							<div class="item_price">가격</div>
						</div>
					</div>
				</div>		
						
				<div class="item-wrap">
					<div class="item_image_div">
						<img src="/jaju/jajuImage/hat.jpg">
					</div>
					<div class="item_detail">
						<div class="item_subject">제목</div>
						<div class="item_price_div">
							<div class="item_price">가격</div>
						</div>
					</div>
				</div>		
						
				<div class="item-wrap">
					<div class="item_image_div">
						<img src="/jaju/jajuImage/chair.jpg">
					</div>
					<div class="item_detail">
						<div class="item_subject">제목</div>
						<div class="item_price_div">
							<div class="item_price">가격</div>
						</div>
					</div>
				</div>		
						
				<div class="item-wrap">
					<div class="item_image_div">
						<img src="/jaju/jajuImage/radio.jpg">
					</div>
					<div class="item_detail">
						<div class="item_subject">제목</div>
						<div class="item_price_div">
							<div class="item_price">가격</div>
						</div>
					</div>
				</div>		


			</div>
		</div>
	</div>
	<script>
		/*자동 재생 되는 슬라이드.
		 $(document).ready(function(){

		 $('#slider-div').slick({
		 slidesToShow: 4,
		 slidesToScroll: 1,
		 autoplay: true,
		 autoplaySpeed: 1000,
		 responsive: [
		 {
		 breakpoint: 1024,
		 settings: {
		 slidesToShow: 3,
		 slidesToScroll: 1
		 }
		 },
		 {
		 breakpoint: 600,
		 settings: {
		 slidesToShow: 2,
		 slidesToScroll: 1
		 }
		 },
		 {
		 breakpoint: 480,
		 settings: {
		 slidesToShow: 1,
		 slidesToScroll: 1
		 }
		 }
		 ]
		 });
		 });*/

		/* 이건 좌 우 스크롤 방향키 있는 슬라이드.  */
		$(function() {
			$('#slider-div02')
					.slick(
							{
								slide : 'div', //슬라이드 되어야 할 태그 ex) div, li 
								infinite : true, //무한 반복 옵션     
								slidesToShow : 5, // 한 화면에 보여질 컨텐츠 개수
								slidesToScroll : 1, //스크롤 한번에 움직일 컨텐츠 개수
								speed : 100, // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
								arrows : true, // 옆으로 이동하는 화살표 표시 여부
								dots : true, // 스크롤바 아래 점으로 페이지네이션 여부
								autoplay : true, // 자동 스크롤 사용 여부
								autoplaySpeed : 5000, // 자동 스크롤 시 다음으로 넘어가는데 걸리는 시간 (ms)
								pauseOnHover : true, // 슬라이드 이동    시 마우스 호버하면 슬라이더 멈추게 설정
								vertical : false, // 세로 방향 슬라이드 옵션
								prevArrow : "<button type='button' class='slick-prev'>Previous</button>", // 이전 화살표 모양 설정
								nextArrow : "<button type='button' class='slick-next'>Next</button>", // 다음 화살표 모양 설정
								dotsClass : "slick-dots", //아래 나오는 페이지네이션(점) css class 지정
								draggable : true, //드래그 가능 여부 

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
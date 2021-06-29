<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

/*상단 가로 메뉴*/
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

.category_type .bx-controls-direction .bx-next, .category_type .bx-controls-direction .bx-prev
	{
	bottom: 272px;
}

.bx-controls-direction .disabled {
	opacity: 0;
}

.bx-controls-direction .bx-prev {
	left: -30px;
	background:
		url(https://res.kurly.com/pc/service/main/1908/btn_prev_default.png?v=1)
		no-repeat 50% 50%;
	transition: background 0.5s;
}

.bx-controls-direction .bx-next, .page_main .bx-controls-direction .bx-prev
	{
	overflow: hidden;
	position: absolute;
	bottom: 316px;
	width: 60px;
	height: 60px;
	border: 0 none;
	font-size: 0;
	line-height: 0;
	text-indent: -9999px;
	transition: opacity 0.5s;
}

.list_goods a {
	cursor: pointer;
}

.category_type {
	padding-bottom: 100px;
}

.tit_goods {
	padding: 79px 0 35px;
}

.tit_goods .tit {
	font-weight: 700;
	font-size: 28px;
	line-height: 32px;
	letter-spacing: -0.3px;
	text-align: center;
}

.tit_goods .name {
	position: relative;
	font-weight: 700;
}

.category_type .category .list_category {
	width: 1050px;
	max-width: 1050px;
	padding: 0 0 20px;
	margin: 0 auto;
	text-align: center;
	font-size: 0;
}

.category_type .category .list_category .menu {
	display: inline-block;
	height: 40px;
	padding: 9px 20px 0 19px;
	border: 1px solid #f7f7f6;
	border-radius: 20px;
	background-color: #f7f7f7;
	font-size: 14px;
	line-height: 18px;
}

.category_type .category .list_category li {
	display: inline-block;
	padding: 0 35px 2px;
}

.category_type .list_goods {
	height: 462px;
}

.category_type .list_goods li {
	height: 462px;
}

.main_type2 .list_goods {
	width: 1050px;
	height: 506px;
	margin: 0 auto;
}

.main_type2 .list_goods .thumb {
	width: 249px;
	height: 320px;
}

.category_type .link_cate {
	width: 516px;
	margin: 0 auto;
}

.bx_viewport {
	width: 100%;
	overflow: hidden;
	position: relative;
	height: 462px;
}

.list_goods .thumb_goods .thumb {
	display: block;
	margin: 0 auto;
	background-position: 50% 50%;
	background-size: cover;
	transform: scale(1);
	transition: all 0.3s ease-in-out;
	width: 100%;
}

.list_goods .thumb_goods {
	display: block;
	overflow: hidden;
	position: relative;
	background-color: #eee;
	height: 300px;
}

.category_type .link_cate .link {
	display: block;
	overflow: hidden;
	height: 56px;
	padding-top: 16px;
	border: 1px solid #e3e3e3;
	border-radius: 3px;
	font-size: 16px;
	line-height: 20px;
	text-align: center;
	letter-spacing: -0.3px;
	cursor: pointer;
}

a:active, a:hover {
	outline: 0;
}
.category_type:after {

	content: "";
	display: block;
	clear: both;

}
</style>
</head>
<body>

	<div class="category_type">
		<div class="tit_goods">
			<h3 class="tit">
				<!---->
				<span class="name"> MD의 추천 <!----></span>
			</h3>
		</div>
		<div class="category_menu">
			<div class="bg_category">
				<span class="bg_shadow shadow_before"></span> <span
					class="bg_shadow shadow_after"></span>
			</div>
			<div class="category">
				<ul class="list_category">
				
					<li data-start="32" data-end="74">
					<a data-no="907" href="#none" class="menu" id="digital">디지털/가전</a>
					</li>

					<li data-start="100" data-end="196">
					<a data-no="908" href="#none" class="menu" id="life">생활</a>
					</li>

					<li data-start="220" data-end="342">
					<a data-no="909" href="#none" class="menu" id="instant_food">가공식품</a>
					</li>

					<li data-start="367" data-end="442">
					<a data-no="910" href="#none" class="menu" id="women_cloth">여성의류</a>
					</li>

					<li data-start="467" data-end="589">
					<a data-no="911" href="#none" class="menu" id="beauty">뷰티 / 미용</a>
					</li>

					<li data-start="614" data-end="715">
					<a data-no="912" href="#none" class="menu" id="furniture">가구 / 인테리어</a>
					</li>
						
					<li data-start="740" data-end="836">
					<a data-no="913" href="#none" class="menu" id="sport">스포츠 / 레저</a>
					</li>
						
					<li data-start="862" data-end="1004">
					<a data-no="914" href="#none" class="menu cut" id="men_choth">남성 / 패션잡화</a>
					</li>
						
					<li data-start="1029" data-end="1125">
					<a data-no="249" href="#none" class="menu" id="dogCat">반려동물용품</a>
					</li>
						
					<li data-start="1151" data-end="1285">
					<a data-no="915" href="#none" class="menu" id="kids">유아동 / 유아도서</a>
					</li>
						
					<li data-start="1311" data-end="1378">
					<a data-no="032"	href="#none" class="menu" id="womenAcc">여성잡화</a>
					</li>
						
					<li data-start="1403" data-end="1504">
					<a data-no="918"	href="#none" class="menu" id="game">게임 / 취미</a>
					</li>
						
					<li data-start="1529" data-end="1656">
					<a data-no="233"	href="#none" class="menu" id="kitchen">주방용품</a>
					</li>
						
					<li data-start="1681" data-end="1790">
					<a data-no="012" href="#none" class="menu on" id="book">도서 / 티켓 / 음반</a>
					</li>
						
					<li data-start="1816" data-end="1883">
					<a data-no="916" href="#none" class="menu" id="else">기타 중고물품</a>
					</li>
					<!---->
				</ul>
			</div>
		</div>
		<div class="list_goods">
			<div class="bx-wrapper" style="max-width: 1050px; margin: 0px auto;">
				<div class="bx-viewport" style="width: 100%; overflow: hidden; position: relative; height: 462px;">
					
					<ul data-title="MD의 추천" data-section="md_choice" class="list" 	style="width: 815%; position: relative; transition-duration: 0s; transform: translate3d(0px, 0px, 0px);">
						
						<li data-index="1" class="" data-name="md_choice"
							style="float: left; list-style: none; position: relative; width: 249px; margin-right: 18px;"><a
							class="thumb_goods"> <!----> 
							<img src=""	alt="상품이미지" class="thumb"
								style="background-image: url();">
							</a>
							<div class="info_goods">
								<span class="name">
								<a class="txt">여기에 제목넣기<br></a></span> 
								<span class="price">10,800원</span> 
							</div>
						</li>
							
						<li data-index="2" class="" data-name="md_choice"
							style="float: left; list-style: none; position: relative; width: 249px; margin-right: 18px;"><a
							class="thumb_goods"> <!----> 
							<img src=""	alt="상품이미지" class="thumb"
								style="background-image: url();">
							</a>
							<div class="info_goods">
								<span class="name">
								<a class="txt">여기에 제목넣기<br></a></span> 
								<span class="price">10,800원</span> 
							</div>
						</li>
						
						
						<li data-index="3" class="cut" data-name="md_choice"
							style="float: left; list-style: none; position: relative; width: 249px; margin-right: 18px;"><a
							class="thumb_goods"> <!----> 
							<img src=""	alt="상품이미지" class="thumb"
								style="background-image: url();">
							</a>
							<div class="info_goods">
								<span class="name">
								<a class="txt">여기에 제목넣기<br></a></span> 
								<span class="price">10,800원</span> 
							</div>
						</li>
						
						<li data-index="4" class="" data-name="md_choice"
							style="float: left; list-style: none; position: relative; width: 249px; margin-right: 18px;"><a
							class="thumb_goods"> <!----> 
							<img src=""	alt="상품이미지" class="thumb"
								style="background-image: url();">
							</a>
							<div class="info_goods">
								<span class="name">
								<a class="txt">여기에 제목넣기<br></a></span> 
								<span class="price">10,800원</span> 
							</div>
						</li>

						<!---->
					</ul>
				</div>
				
				<div class="bx-controls bx-has-controls-direction">
					<div class="bx-controls-direction">
						<a class="bx-prev disabled" href="">Prev</a><a class="bx-next"
							href="">Next</a>
					</div>
				</div>
			</div>
		</div>
		<div class="link_cate">
			<a class="link"><span class="ico">헤어·바디·구강 전체보기</span></a>
		</div>
	</div>

</body>
</html>
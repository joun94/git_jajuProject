<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/jaju/css/mypage.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>

<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>


</head>
<body>
	<!-- 가로메뉴바,세로메뉴바 고정 -->
	<!-- 프로필, 스크랩, 팔로우, 메세지 -->
	
	<!-- controller에서 넘어온 id/pg ,id는 임의로 만들어둔 변수명. 나중에 session값 처리-->
	<input type="hidden" id="id" name="id" value="${memId}">

	<div id="myPageTop" class="page_aticle mypage_top">
		<h2 class="screen_out">마이페이지</h2>
		
		<div class="mypagetop_user">

			<div class="inner_mypagetop">
			
					<!--프로필 이미지 사진 동적태그-->					
					<div class="profile_image_div">
						<img src="" alt="프로필" class="profile_image">
					<!--이름 동적태그-->					
					<h2 class="name" id="name"></h2>
					<button class="change_image_Btn" id="change_image">프로필 변경</button>
				</div>

				<div class="grade_user">

					<div class="grade_wrap" >
						<div class="grade_logo class0">회원</div>
					</div>


					<!--매너온도 동적태그-->					
					<div class="next">
						<a href="#" class="total_grade" id="total_grade"></a>
					</div>
					
					<!--판매중 동적태그-->					
					<div id="deal0">
						<a id="deal" href="/jaju/mypage/myDealRecode" class="link_wrap"></a>
					</div>
					
							<p class="info"></p>
							<div class="spacer"></div>
					
				</div>
				
				<div class="list_mypage">
					<div class="list">
						
						<div id="deal_count" href="/jaju/mypage/myScrap" class="link_wrap">
							<div class="link_title">거래내역</div>
							
								<a href="/jaju/mypage/mySaleRecode" id="sale" ></a>
								<a href="/jaju/mypage/myBuyRecode" id="buy" ></a>
						
						</div>
						
						
						  <!--  <div class="link_title">
							<a  class="link_wrap_deal">
								<div class="link_title" >거래내역</div>
									<div class="spacer">
								</div>
							</a>
							 
							<div>
							<span id="sale"></span>
							<span id="buy"></span>
							</div>		 
							
							-- 판매 *건 동적태그 --
							<a id="sale"  href="/jaju/mypage/mySaleRecode" class="link_wrap_sale"></a>
							-- 구매 *건 동적태그 --	
							<a id="buy" href="/jaju/mypage/myBuyRecode" class="link_wrap_buy"></a>
						</div>-->

						
						<a id="scrap_count" href="/jaju/mypage/myScrap" class="link_wrap">
							<div class="link_title">찜목록</div>
							<p class="info"></p>
							<div class="spacer"></div>
						</a>

						<!-- 팔로우 창은 window open으로 띄우기 -->
						<a id="follow_count" href="/jaju/mypage/myFollow" class="link_wrap">
							<div class="link_title">팔로우</div>
							<p class="info"></p>
							<div class="spacer"></div>
						</a> 
						
						<a id="message_count" href="#" class="link_wrap" onclick="javascript:messageMove()">
							<div class="link_title">쪽지</div>
							<div class="spacer"></div>
							<p class="info">
								<span class="expire" id="expire"></span>
							</p>
						</a>
						
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="page_aticle aticle_type2">
		<div id="snb" class="snb_cc">
			<h2 class="tit_snb">마이페이지</h2>

			<div class="inner_snb">
				<ul class="list_menu">
					<li><a href="/jaju/mypage/myKeywordForm" onclick="">관심 키워드
							등록</a></li>
					<li><a href="/jaju/mypage/myLocationForm" onclick="">관심 지역
							등록</a></li>
					<li><a href="/jaju/member/modifyForm">회원 정보 수정</a></li>
					<li><a href="/jaju/serviceCenter/reportList" onclick="">1:1
							문의 내역</a></li>
				</ul>
			</div>
			<a href="/jaju/serviceCenter/reportWriteForm" class="link_inquire" style="margin-bottom: 50px;">
				<span class="emph" >도움이 필요하신가요?</span> 1:1 문의하기
			</a>
		</div>

	</div>
	<!-- <div class="container"></div> -->
 
 
 
	<script>
 

	  /*
	 function messageMove() {
		//팝업 창 가운데 띄워주기
		var popWidth  = '500'; // 파업사이즈 너비
		var popHeight = '500'; // 팝업사이즈 높이
		
		var winWidth  = document.body.clientWidth;  // 현재창의 너비
		var winHeight = document.body.clientHeight; // 현재창의 높이
		var winX      = window.screenX || window.screenLeft || 0;// 현재창의 x좌표
		var winY      = window.screenY || window.screenTop || 0; // 현재창의 y좌표
		var w = winX + (winWidth - popWidth) / 2;
		var h = winY + (winHeight - popHeight) / 2;

		window.open("/jaju/mypage/myMessage", "my_Message", "width="+popWidth+", height="+popHeight+", left="+winX+", top="+winY+", menubar=yes, status=yes, titlebar=yes, resizable=yes");
		}
 */
	 
	  function messageMove(){
	 //팝업 창 가운데 띄워주기
	 var popupWidth=500;
	 var popupHeight=500;

	 var popupX = (window.screen.width/2)-(popupWidth/2);
	 // 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음

	 var popupY= (window.screen.height/2)-(popupHeight/2);
	 // 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음

	
	
	window.open("/jaju/mypage/myMessage", 
			 "my_Message", 
			 'status=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY); } 
 

 </script>
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="/jaju/js/mypageMain.js"></script>
</body>
</html>
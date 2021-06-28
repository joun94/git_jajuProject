<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<link rel="stylesheet" href="/jaju/css/serviceMenu.css" />

</head>
<body>

<div class="page_aticle aticle_type2">
	<div id="snb" class="snb_cc">
		<h2 class="tit_snb">고객센터</h2>
		
		<div class="inner_snb">
			<ul class="list_menu">
				<li class="on">
					<a href="#none" onclick="KurlyTrackerLink('/shop/board/list.php?id=notice', 'select_service_notice_list')">공지사항</a>
				</li>
				<li>
					<a href="#none" onclick="KurlyTrackerLink('/shop/service/faq.php', 'select_service_frequently_qna')">자주하는 질문</a>
				</li>
				<li>
					<a href="#none" onclick="KurlyTrackerLink('/shop/mypage/mypage_qna.php', 'select_service_personal_inquiry_history')">1:1 문의</a>
				</li>
				<li>
					<a href="#none" onclick="KurlyTrackerLink('/shop/mypage/offer.php', 'select_service_product_offer')">상품 제안</a>
				</li>
			</ul>
		</div>
	</div>
</div>

</body>
</html>
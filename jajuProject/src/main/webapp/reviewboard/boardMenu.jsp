<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<link rel="stylesheet" href="/jaju/css/boardMenu.css" />
<style type="text/css">
#snb .list_menu li .type{
	background: #fafafa url(https://res.kurly.com/pc/ico/2008/ico_arrow_6x11_on.svg) no-repeat 174px 52%;
    background-size: 6px 11px;
    font-weight: 700;
    color: #47597e;
}
</style>

<div class="page_aticle aticle_type2">
	<div id="snb" class="snb_cc">
		<h2 class="tit_snb">게시판</h2>
		
	<div class="inner_snb">
		<ul class="list_menu">
			<li id="type1">
				<a id="type" class="type1" href="#"><strong>자유 게시판</strong></a>
			</li>
			<li id="type2">
				<a id="type" class="type2" href="/jaju/freeboard/freeboardList?pg=1">목록보기</a>
			</li>
			<li id="type3">
				<a id="type" class="type3" href="/jaju/freeboard/freeboardWriteForm">글쓰기</a>
			</li>
			<li id="type4">
				<a id="type" class="type4" href="#"><strong>후기 게시판</strong></a>
			</li>
			<li id="type5">
				<a id="type" class="type5" href="/jaju/reviewboard/reviewboardList">목록보기</a> <!-- pg값 주기 -->
			</li>
			<li id="type6">
				<a id="type" class="type6" href="/jaju/reviewboard/review_saleReport?pg=1">글쓰기</a>
			</li>
		</ul>
	</div>
	</div>
</div>
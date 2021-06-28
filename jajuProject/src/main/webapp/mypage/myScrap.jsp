<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.image-center {
	margin: 0 auto;
	width: 300px;
	height: 250px;
}

.sale_subject, .sale_state, .scrap_date {
	margin: 0 0 0 0;
}
</style>
<link rel="stylesheet" href="/jaju/css/myScrap.css" />

<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
</head>

<body>

	<jsp:include page="/mypage/mypageMain.jsp" />


	<div class="myScrapWrap">
		<!-- 찜목록 -->
		<form id="myScrapForm" method="get" action="deleteMyScrap">
		
		<!-- controller에서 넘어온 id/pg ,id는 임의로 만들어둔 변수명. 나중에 session값 처리-->
		
		<input type="hidden" id="id" name="id" value="${memId}">
		<input type="hidden" id="pg" name="pg" value="${pg}">

		<div class="head_aticle">
			<h2 class="tit">&emsp;찜목록</h2>
			
			
		</div>

		<div class="board-header-container">
			<ul class="list-description">
				<li>사진을 누르면 해당 페이지로 이동합니다.</li>
			</ul>
		</div>

		<div class="delete_btn_div" >
			<button type="button" class="delete_btn" id="delete_btn">삭제</button>
			<button type="button" class="check_items" id="check_items">편집</button>
		</div>

		<table border="1" id="scrapListTable" cellpadding="5" width="750px" bordercolor="#ededed" align="center" frame="hsides" rules="rows">
		</table>
				
		<!-- 스크랩 jquery이용해서 each로 받아온 사진 뿌리기. 형태만 잡기 -->
		<section class="scrap_row" id="scrap_row">
			<!-- 사람이미지. -->
			<section class="add_scrap_pic" id="add_scrap_pic">

			</section>
		</section>
		
		<div id="ScrapPagingDiv" align="center">
			<div style="margin: 25px; width: 700px; text-align: center;"></div>
		</div>		
		</form>

	</div>

	<script type="text/javascript"
		src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="/jaju/js/myScrap.js"></script>
	
	<script type="text/javascript">

	function mypagePaging(pg){
		location.href="/jaju/mypage/myScrap?pg="+pg;
	}

	</script>
</body>
</html>
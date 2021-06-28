<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>follow</title>
<link rel="stylesheet" href="/jaju/css/myFollow.css" />
</head>
<body>


<!-- 팔로우 리스트 : 팝업으로 띄우기 -->
<jsp:include page="/mypage/mypageMain.jsp" />

<div class="myFollowDiv">

	<form id="myFollowForm" action="/jaju/mypage/deleteMyFollowList">
		
		<!-- controller에서 넘어온 id/pg ,id는 임의로 만들어둔 변수명. 나중에 session값 처리-->
		<input type="hidden" id="id" name="id" value="${memId}">	
		<input type="hidden" id="pg" name="pg" value="${pg}">	
			
	
		<div class="head_aticle">
			<h2 class="tit">&emsp;팔로우 목록</h2>
		</div>
	
		<div class="board-header-container">
			<ul class="list-description">
				<li>팔로우 한 유저의 아이디를 눌러<br> 판매상품을 확인해보세요!</li>
			</ul>
		</div>
		

		
		<!-- 스크랩 jquery이용해서 each로 받아온 사진 뿌리기. 형태만 잡기 -->
		<div class="followListDiv">
		
		<table border="0" id="followListTable" cellpadding="5" width="800px" bordercolor="#ededed" align="center" frame="hsides" rules="rows">
			<tr class="title">
				<th><input type="checkbox" id="all" name="all" value="all"></th>
				<th width="200px">프로필이미지</th>
				<th width="200px">아이디</th>
				<th width="200px">매너온도</th>
			</tr>
		</table>
		</div>

		<div class="follow_btn_div">
			<table bordercolor=""  align="right" style="margin-top: 10px;">
					<tr>
						<td class="follow_td" align="right" >
						<p class="deleteBtn" id="deleteBtn" ><a href="#" style="color:white;">&emsp;선택삭제&emsp;</a></p>
						</td>
					</tr>
			</table>
		</div>
		
		<div id="followPagingDiv" align="center">
			<div style="margin: 25px; width: 700px; text-align: center;"></div>
		</div>	
		
	</form>
</div>

	<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>	
	<script src="/jaju/js/myFollow.js"></script>
	
	<script type="text/javascript">

	function mypagePaging(pg){
		location.href="myFollow?pg="+pg;
	}

	</script>
</body>
</html>
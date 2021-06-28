<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/jaju/css/myMessage.css" />
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
</head>
<body>

<div class="myMessageWrap">
 
    <form id="messageForm" method="get" action="deleteMyMessage" >
    
    <input type="hidden" name="pg" id="pg" value="${pg}">
    <input type="hidden" name="id" id="id" value="${memId}">
    
    

	<div class="head_aticle">
		<h2 class="tit">쪽지</h2>
	</div>
	
	<div class="board-header-container">
		<ul class="list-description">
			<li>받은 쪽지함 입니다.</li>
		</ul>
	</div>
	
	<div class="mainMessage">
	
		<div class="message_Btn">
			<span class="deleteBtn" id="deleteBtn"><a href="#">&emsp;삭제&emsp;</a></span>
		</div><!-- message_Btn -->
		
		<table border="0" id="followListTable" cellpadding="5" width="750px" bordercolor="#fff" align="center" frame="hsides" rules="rows">
		
		<!-- 쪽지 틀 -->
			<tr class="message_title" style="height:35px;">
				<td align="center"><input type="checkbox" id="all" name="all" value="all"></td>
				<td align="center">보낸이</td>
				<td align="center" style="width: 200px;">제목</td>
				<td align="center">작성일</td>
				<td align="center">답변상태</td>
			</tr>
		</table>
				
		<!-- <ul class="board-list">
			<li class="inquiry-empty">받은 쪽지가 없습니다.</li>
		</ul> -->
				
		<!-- 스크랩 jquery이용해서 each로 받아온 사진 뿌리기. 형태만 잡기 -->
		<div class="messageListDiv">
				<table border="1" id="messageListTable" cellpadding="5" width="750px" bordercolor="#ededed" align="center" frame="hsides" rules="rows">
				</table>

			<div id="myMessagePagingDiv" align="center">
				<div style="width: 750px;  text-align: center;"></div>
			</div>		

		</div>
		
		<!-- <div class="board-inquiry-area">
			<div class="paging-navigation">
				<button type="button" class="paging-prev" >
					<span>이전</span>
				</button>
				<button type="button" class="paging-next">
					<span>다음</span>
				</button>
			</div>
		</div>board-inquiry-area -->
		
	</div><!-- mainMessage -->
	</form>
</div><!-- myMessageWrap -->
		
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>	
<script src="/jaju/js/myMessage.js"></script>
</body>
</html>
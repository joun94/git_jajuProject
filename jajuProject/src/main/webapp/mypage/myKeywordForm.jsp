<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/jaju/css/myKeyword.css" />
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
</head>
<body>
<!-- 키워드 등록  -->

<form id="myKeywordForm">

<!-- 히든 값으로 id받아오기 ( 세션 으로 로그인 시 아이디 생성. db가서 키워드 가져 올 때 사용하기 )  -->
	<jsp:include page="/mypage/mypageMain.jsp" />

	<input type="hidden" id="id" name="id" value="${memId}">	
	<!-- controller에서 넘어온 id ,memId 는 임의로 만들어둔 변수명.-->



	<div id="myKeywordDiv" align="left">
	
		<div class="head_aticle">
			<h2 class="tit">관심 키워드 등록</h2>
		</div>
		
		<div class="board-header-container">
			<ul class="list-description">
				<li>관심 키워드를 등록하는 곳 입니다.</li>
				<li>관심 키워드는 5개까지 등록 가능합니다.</li> 
			</ul>
		</div>
	
		<table border="1" id="myKeywordTable" cellpadding="5" width="550px" bordercolor="#263343" align="left" frame="hsides" rules="rows">
				<tr class="text">
					
					<td colspan="3" align="left" width="150px">
					<input type="text" placeholder="키워드를 선택해주세요.">
					</td>
				</tr>
	
				<tr class="keywordList">
					<td width="150px">
					<input type="checkbox" id="digital" class="digital" name="check" value="digital"> 
					<label for="digital">디지털/가전</label></td>
					<td width="150px"><input type="checkbox" id="life" class="life" name="check" value="life">
					<label for="life">생활</label></td>
					<td width="150px"><input type="checkbox" id="instant_food" class="instant_food" name="check"  value="instant_food">
					<label for="instant_food">가공식품</label></td>
				</tr>
				
				<tr class="keywordList">
					<td width="150px"><input type="checkbox" id="women_cloth" class="women_cloth" name="check" value="women_cloth" >
					<label for="women_cloth">여성의류</label></td>
					<td width="150px"><input type="checkbox" id="beauty" class="beauty" name="check" value="beauty">
					<label for="beauty">뷰티/미용</label></td>
					<td width="150px"><input type="checkbox" id="furniture" class="furniture" name="check" value="furniture">
					<label for="furniture">가구/인테리어</label></td>
				</tr>
			
				<tr class="keywordList">
					<td width="150px"><input type="checkbox" id="sport" class="sport" name="check" value="sport" >
					<label for="sport">스포츠/레저</label></td>
					<td width="150px"><input type="checkbox" id="men_choth" class="men_choth" name="check" value="men_choth">
					<label for="men_choth">남성/패션잡화</label></td>
					<td width="150px"><input type="checkbox" id="dogCat"  class="dogCat" name="check" value="dogCat">
					<label for="dogCat">반려동물용품</label></td>
				</tr>
	
				<tr class="keywordList">
					<td width="150px"><input type="checkbox" id="kids" class="kids" name="check" value="kids">
					<label for="kids">유아동/유아도서</label></td>
					<td width="150px"><input type="checkbox" id="womenAcc" class="womenAcc" name="check" value="womenAcc">
					<label for="womenAcc">여성잡화</label></td>
					<td width="150px"><input type="checkbox" id="game" class="game" name="check" value="game">
					<label for="game">게임/취미</label></td>
				</tr>
	
				<tr class="keywordList">
					<td width="150px"><input type="checkbox" id="kitchen" class="kitchen" name="check"  value="kitchen">
					<label for="kitchen">주방용품</label></td>
					<td width="150px"><input type="checkbox" id="book" class="book" name="check" value="book" >
					<label for="book">도서/티켓/음반</label></td>
					<td width="150px"><input type="checkbox" id="else" class="else" name="check" value="else" >
					<label for="else">기타 중고물품</label></td>
				</tr>		
	</table>	
	
	<div class="mainMessage">
		<div class="message_Btn">
			<button class="sendBtn" id="sendBtn" >등록</button>
			<button type="reset" class="resetBtn" id="resetBtn" >다시선택</button>
		</div>
	
		<!--<div class="message_Btn">
			<input type="button" value="등록" class="deleteBtn"/>
			<input type="reset" value="다시선택" class="sendBtn"/>
		</div> message_Btn -->	
	</div>
</div>
</form>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>	
<script src="/jaju/js/myKeyword.js"></script>
</body>
</html>
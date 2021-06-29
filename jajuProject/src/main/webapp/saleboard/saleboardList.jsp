<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/jaju/css/saleboardList.css" />
</head>
<body>

<div id="saleboardListDiv">

		<div class="search_bt">
			
			<select id="sortinSelect" class="selectBox" >
				<option value="sale_date">최신순</option>
				<option value="sale_hit">인기순</option>
				<option value="low_sale_price">낮은가격순</option>
				<option value="sale_price">높은가격순</option>
			</select> 
			
			<select id="selectBox" class="selectBox">
				<option value="*">전체</option>
				<option value="digital">디지털/가전</option>
				<option value="life">생활</option>
				<option value="instant_food">가공식품</option>
				<option value="women_cloth">여성의류</option>
				<option value="beauty">뷰티 / 미용</option>
				<option value="furniture">가구 / 인테리어</option>
				<option value="sport">스포츠 / 레저</option>
				<option value="men_choth">남성 / 패션잡화</option>
				<option value="dogCat">반려동물용품</option>
				<option value="kids">유아동 / 유아도서</option>
				<option value="womenAcc">여성잡화</option>
				<option value="game">게임 / 취미</option>
				<option value="kitchen">주방용품</option>
				<option value="book">도서 / 티켓 / 음반</option>
				<option value="else">기타 중고물품</option>
			</select> 
			<input type="text" name="searchText" id="searchText" value="" required="">
			<input type="image" src="/jaju/image/search.webp" align="absmiddle" width="30" height="30" id="searchBtn">
		</div>

		<!-- 리스트 -->
	<div id="goodsList" class="page_section section_goodslist">
		
		<div class="list_goods">
			<div class="inner_listgoods">
				<ul class="list">
					
				</ul>
			</div>
		</div>
		
		<!-- 페이징 -->
		<div id="pagingDiv">
			
			
		</div>
		
		<div style="position:relative; margin-top: -125px; margin-bottom: 170px;">
         <div style="position:absolute;right:0;top:60px;">
            <a href="/jaju/saleboard/saleboardWriteForm">
               <span class="bhs_buttonsm yb" style="float:none;">판매물품등록</span>
            </a>
         </div>
      </div>
	</div>
</div>
<input type="hidden" id="pg" value="${param.pg }">
<input type="hidden" id="searchPg" value="1">
<input type="hidden" id="memId" value="${sessionScope.memId }">
<input type="hidden" id="sortinSelected" value="${param.sortinSelect }">

<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/jaju/js/saleboardList.js"></script>
<script type="text/javascript" >
$('.item').click(function(){
	location='/jaju/saleboard/saleboardView';
});
function saleboardPaging(pg){
	var searchText = document.getElementById('searchText').value;
	
	if(searchText == ''){
		location.href = '/jaju/saleboard/saleboardList?pg='+pg+'&sortinSelect='+$('#sortinSelect').val();
		//$('#sortinSelect').trigger('change');		
	} else{
		
		$('#searchPg').val(pg);
		//alert($('#searchPg').val())
		$('#searchBtn').trigger('click','research');
		//location.href = 'boardSearch?pg='+pg+'&select='+$('#select option:selected').val() +'&keyword='+$('#keyword').val();
		//encodeURIComponent('${keyword}');
		//$('#searchPg').val(1);
	}	 	
}

</script>

</body>
</html>
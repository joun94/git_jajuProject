<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/jaju/css/saleStateModifyForm.css" />
</head>
<body>

	<div id="saleboardDiv">
		<div class="head_aticle"></div>

		<div class="xans-board-modify">
			<form class="saleboardModifyForm" id="saleboardModifyForm"
				name="saleboardModifyForm">

				<input type="hidden" name="sale_seq" id="sale_seq"
					value="${param.sale_seq }"> <input type="hidden"
					name="mode" value="add_qna"> <input type="hidden"
					name="itemcd"> <input type="hidden" name="sno" value="0">

				<div class="head_aticle">
					<h2 class="tit">판매 상태 변경</h2>
				</div>
		
				<div class="board-header-container">
					<ul class="list-description">
						<li>판매 게시글의 상태를 변경 후, 판매 완료 시 구매자의 아이디를 등록해주세요.</li>
					</ul>
				</div>

				<table class="saleboardModifyTable" id="saleboardModifyTable">

					<tbody>

						<tr>
							<th class="saleboardModifyTd">제목</th>
							<td>
								<div class="sale_subject" id="sale_subject"></div>
								<div id="sale_subjectDiv"></div> 
								<select class="sale_state"	name="sale_state" id="sale_state">
									<option value="판매중">판매중</option>
									<option value="예약중">예약중</option>
									<option value="판매완료">판매완료</option>
								</select>

								<button name="change_state" id="change_state">판매상태 수정</button>
								<div class="click_to_change_inform">
									판매상태 수정 버튼을 눌러 상태를 변경해보세요! <span><a OnClick="notice_closeWin()"></a></span>
								</div> 
								<br>
							</td>
							<td></td>
						</tr>


						<!--<div><input class="sale_state" type="button" value="구매자찾기" id="saleBuyer"></div>
	<div><input class="saleBuyerBtn" type="button" value="구매자선택완료" id="saleBuyerBtn"></div> -->

						<tr>
							<th class="saleboardModifyTd">구매자 목록</th>
							<td class="saleboardModifyTd" id="buyer_list">
							
							<select class="select_buyer" name="select_buyer" id="select_buyer">
									<option value="구매자선택" >구매자선택</option>
							</select> 
							
							<input class="sale_state" type="button" value="구매자찾기" id="saleBuyer"> 
							<input class="saleBuyerBtn" type="button" value="구매자선택완료" id="saleBuyerBtn">
							<div id="select_buyerDiv"></div></td>
						</tr>

						<tr>
							<th class="saleboardModifyTd">판매가격</th>
							<td class="saleboardModifyTd" colspan="3">
								<span class="sale_price" id="sale_price"></span>
								<span class="sale_nego" id="sale_nego"></span>
								<div id="sale_priceDiv"></div>
							</td>
						</tr>

						<tr>
							<th class="saleboardModifyTd">카테고리</th>
							<td class="saleboardModifyTd">
								<div class="sale_category" id="sale_category"></div> 
								<div id="sale_categoryDiv"></div>
							</td>
						</tr>

						<tr>
							<th class="saleboardModifyTh">상품 상태</th>
							<td class="saleboardModifyTd">
								<div class="sale_condition" id="sale_condition"></div>
								<div id="sale_conditionDiv"></div>
							</td>
						</tr>

						<tr>
							<th class="saleboardModifyTd">배송 방법</th>
							<td class="saleboardModifyTd">
								<div class="sale_method" id="sale_method"></div>
								<div id="sale_conditionDiv"></div>
								<div id="sale_deliveryDiv"></div>
								<div id="sale_onlineTranDiv"></div>
							</td>

						</tr>

						<tr>
							<th class="saleboardModifyTd">내용</th>
							<td class="saleText" name="sale_content" placeholder="내용을 입력해주세요." style="position: relative; width: 100%;">
								<div class="sale_image" id="sale_image"></div>
								<div class="sale_content" id="sale_content"></div>
							</td>
						</tr>
						
						<tr>
							<td colspan="3" align="center">
							<button id="close_btn" onclick="javascript:window.close()">닫기</button></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>

	</div>
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
		
	<script src="/jaju/js/saleStateModify.js"></script>
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link rel="stylesheet" href="/jaju/css/serviceMenu.css" /> 
<link rel="stylesheet" href="/jaju/css/question.css" />    
<form name="frmList">
<div style="width: 1080px; margin: auto;">
<jsp:include page="/serviceCenter/serviceMenu.jsp"/>
<input type="hidden" id="question_seq" name="question_seq" value="${question_seq}">
<input type="hidden" id="pg" name="pg" value="${pg}">	
<div class="page_section">
<div id = "questionTotal" style="margin-bottom: 50px;">
	<div class="head_aticle">
		<h2 class="tit" style="font-size: 31px;">자주하는 질문</h2>
	</div>
					
<div class="xans-element- xans-myshop xans-myshop-couponserial">
	<table width="100%" class="xans-board-listheader">
		<tbody>
			<tr>
				<th width="70px" class="input_txt">번호</th>
				<th width="150px" class="input_txt">카테고리</th>
				<th class="input_txt">제목</th>
			</tr>
		</tbody>
	</table>
	<div style="width: 800px;">

	<table width="100%" class="table_faq" id="faq_7">
		<div id = "sub_table"></div>
	</table>
	
		<!-- <div style="display: none; padding: 30px; border-top: 1px solid rgb(230, 230, 230);">
				<tr valign="top">
					<th style="color: #0000bf; width: 40px; padding-top: 1px;">
						<img src="/jaju/jajuImage/a.png" style="width: 25px; margin-top: -5px;">
					</th>
					<td>
					seq 의 내용
					</td>
				</tr>
		</div> -->
	</div>

	</div>
	<div style="padding: 1px; border-top: 1px solid #e6e6e6"></div>
			
				<div class="layout-pagination">
					<div class="pagediv"></div>
				</div>
				
		<div style="position: relative; float:left; margin-left: 100px;">
			<div style="position: absolute; right: 0; top: 15px;">
				<a href="/jaju/serviceCenter/questionWriteForm">
					<span class="bhs_buttonsm yb" style="float: none;">글쓰기</span>
				</a>
			</div>
		</div>
		
		<table class="xans-board-search xans-board-search2">
			<tbody>
				<tr>
					<td class="input_txt">&nbsp;</td>
					<td>
					<br>
					<div class="search" style="float: right; ">
						<select id="itemcd" name="itemcd" style="width: 100px; text-align: center;" >
							<option value="question_subject" style="text-align: center;">제목</option>
							<option value="questionType" style="text-align: center;">카테고리</option>
						</select>
						<a id="searchBtn" style="cursor: pointer;">
							<img src="/jaju/jajuImage/search.png">
						</a>
					<input type="text" id="keyword" name="keyword" value="${keyword}" style="font-size:14px;">
					</div>
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		</div>
	</div>
</form>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/jaju/js/questionList.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	$('#type2').addClass('on');
});
</script>
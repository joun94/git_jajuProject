<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="/jaju/css/serviceMenu.css" /> 
<link rel="stylesheet" href="/jaju/css/reportList.css" />
<jsp:include page="/serviceCenter/serviceMenu.jsp"/>

<form id="reportList">
<input type="hidden" id="report_seq" name="report_seq" value="${report_seq}">
<input type="hidden" id="pg" name="pg" value="${pg}">	
<input type="hidden" id="report_id" name="report_id" value="${sessionScope.memId}">
<div style="width: 1050px; margin: auto;" >
<div id="total">
	<div class="page_section section_qna">
		<div class="head_aticle">
			<h2 class="tit">신고 게시판</h2>
		</div>
		<div id="table">
			<table class="xans-board-listheader" style="width:100%">
				<tbody>
					<tr class="input_txt">
						<th width="8%">번호</th>
						<th width="20%">카테고리 </th>
						<th>제목</th>
						<th width="12%">작성자</th>
						<th width="12%">작성일</th>
						<th width="12%">처리상태</th>
					</tr>
				</tbody>
			</table>
		</div>
		

	<table class="reportListTable" style="width:100%; border-top: 1px solid #e6e6e6; border-bottom: 1px solid #e6e6e6;"></table>
			
	<div class="no_data" style="padding: 150px 0 148px; border-top: 1px solid #e6e6e6; border-bottom: 1px solid #e6e6e6; font-size: 14px; color: #4c4c4c; text-align: center;">
		신고 내역이 존재하지 않습니다.
	</div>
		
<!-- 	<div style="position:relative;">
		<div style="position:absolute;right:0;top:60px;">
			<a href="/jaju/serviceCenter/reportWriteForm">
				<span class="bhs_buttonsm yb" style="float:none;">글쓰기</span>
			</a>
		</div>
	</div> -->
	
		<div class="layout-pagination">
			<div class="pagediv"></div>
		</div>
		<br><br><br><br><br>
	</div>
</div>
</div>
</form>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/jaju/js/reportList.js"></script>	
<script type="text/javascript">
$(document).ready(function(){
	
	$('#type4').addClass('on');
});
</script>
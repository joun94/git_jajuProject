<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="/jaju/css/reviewboardList.css" />
<jsp:include page="/freeboard/boardMenu.jsp"/>

<input type="hidden" name="id" value="reviewboard">

<div id="formTotal">
<div class="head_aticle" padding-left="10px">
	<h2 class="tit">후기 게시판 </h2>
</div>
<br>
<div id="totalDiv">
	<table id="reviewboardListTable" style="width:100%" class="xans-board-listheader jh" cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<th width="8%">번호</th>
				<th width="12%">사진</th>
				<th>제목</th>
				<th width="12%">글쓴이</th>
				<th width="12%">작성일</th>
				<th width="8%">조회수</th>
			</tr>
			<!-- 테이블 붙이는 곳 -->
			
		</thead>
	<tbody>

</tbody>
</table>
</div>

<br><br>
<div id="totalSearch">
	<table class="xans-board-search xans-board-search2">
	<tbody>
	<tr>
		<td class="input_txt">
		<form name="reviewboardSearchList" id="reviewboardSearchList"> <!-- 이전 form name: frmList -->
			
				<!-- 페이징 -->
	<div id="reviewboardpagingDiv" class="reviewboardpagingDiv" align="center">
					
	</div>
<br>
			<div class="search" align="right">
			
				<select name="searchOption" id="searchOption" width="100px" align="absmiddle">
					<option value="review_subject">&nbsp;&nbsp;&nbsp;&nbsp;제목&nbsp;&nbsp;&nbsp;&nbsp;</option>
					<option value="review_content">&nbsp;&nbsp;&nbsp;&nbsp;내용&nbsp;&nbsp;&nbsp;&nbsp;</option>
					<option value="review_id">&nbsp;&nbsp;&nbsp;&nbsp;작성자&nbsp;&nbsp;&nbsp;&nbsp;</option>
				</select>	
				<input type="text" name="keyword" id="keyword" required="" align="absmiddle">
				
				<input type="image" src="/jaju/jajuImage/search.png" id="reviewboardSearchBtn" align="absmiddle">

			</div>			
			<input type="hidden" name="pg" id="pg" value="${param.pg }">
			<input type="hidden" name="searchPg" id="searchPg" value="1">	
		</form>
		</td>
	</tr>
	</tbody>
	</table>
	<br>

</div>
</div>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/jaju/js/reviewboardList.js"></script>
<script type="text/javascript">

 function reviewboardpaging(pg){
	var reviewboardSearchText = document.getElementById('keyword').value;
	
	if(reviewboardSearchText == ''){
		location.href = '/jaju/reviewboard/reviewboardList?pg='+pg;
	} else{
		$('#searchPg').val(pg);
		$('#reviewboardSearchBtn').trigger('click');
		//location.href = 'boardSearch?pg='+pg+'&select='+$('#select option:selected').val() +'&keyword='+$('#keyword').val();
		//encodeURIComponent('${keyword}');
		$('#searchPg').val(1);
	}	 	
}

</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/jaju/css/serviceMenu.css" /> 
<link rel="stylesheet" href="/jaju/css/noticeWriteForm.css" />    
<jsp:include page="/serviceCenter/serviceMenu.jsp"/>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="page_section section_qna">
<div class="head_aticle">
<h2 class="tit">공지사항 글쓰기</h2>
</div>

<div class="xans-board-write">
<form name="noticeWriteForm" id="noticeWriteForm" method="post"  enctype="multipart/form-data" style="height: 100%;">
<table id="table_after" class="boardWrite2" width="100%">
<colgroup><col width="14%" align="right"></colgroup>
<tbody>
<tr>
	<th class="input_txt">제목</th>
	<td>
		<input type="text" name="notice_subject" id="notice_subject" style="width:100%" label="제목">
		<div id = "notice_subjectDiv" ></div>
	</td>
</tr>
<tr>
<th class="input_txt">내용</th>
<td class="edit_area" style="position: relative;">



<textarea name="notice_content" id = "notice_content" style="width:100%; height:474px; resize: none;" class="editing_area" label="내용"></textarea>
<div id = "notice_contentDiv"></div>
</td>
</tr>
<tr>
<th class="input_txt">이미지</th>
<td>
<table width="95%" id="table" cellpadding="0" cellspacing="0" border="0" style="border:solid 1px #f4f4f4; border-collapse:collapse;">
	<tbody>
	<tr id="tr_0">
		<td width="20" nowrap="" align="center">1</td>
		<td width="100%">
			<input type="file" name="img[]" style="width:50%" class="linebg" multiple size = "50">
			<a href="javascript:add()">
				<img src="/jaju/jajuImage/upadd.jpg">
			</a>
		 </td>
	</tr>
	</tbody>
</table>
<table>
	<tbody>
		<tr>
			<td height="2"></td>
		</tr>
	</tbody>
</table>
	<div width="100%" style="padding:5px;" class="stxt">
	- 파일은 최대 10개까지 업로드가 지원됩니다.<br>
	</div>
	</td>
		</tr>
	</tbody>
</table>

<table width="100%">
	<tbody>
		<tr>
			<td align="right" style="padding-top:5px; border:none;" id="avoidDbl">
				<input type="button"class = "bhs_button yb" id = "noticeWriteBtn" value="저장" style="float:none;">
			</td>
		</tr>
	</tbody>
</table>
</form>
</div>

</div>

</body>
<script type = "text/javascript" src = "http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/jaju/js/noticeWriteForm.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	
	$('#type1').addClass('on');
});
</script>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="/jaju/css/inquireReplyForm.css" />    


<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="page_section section_qna">
<div class="head_aticle">
<h2 class="tit">1:1문의 관리자 답변</h2>
</div>

<div class="xans-board-write">
<form name="fm" id="fm" method="post"  enctype="multipart/form-data" style="height: 100%;">
<input type="hidden" name="pseq" value="${pseq}">
<input type="hidden" name="pg" value="${pg }">
<table id="table_after" class="boardWrite2" width="100%">
<colgroup><col width="14%" align="right"></colgroup>
<tbody>
<tr>
	<th class="input_txt">제목</th>
	<td>
	
		<input type="text" name="inquiry_subject" id = "inquiry_subject" style="width:100%" label="제목">
		<div id = "inquiry_subjectDiv" ></div>
	</td>
</tr>

<tr>
<th class="input_txt">내용</th>
<td class="edit_area" style="position: relative;">



<textarea name="inquiry_contents" id = "inquiry_contents" style="width:100%; height:474px; resize: none;" class="editing_area" label="내용"></textarea>
<div id = "inquiry_contentsDiv"></div>
</td>
</tr>
	</tbody>
</table>

<table width="100%">
	<tbody>
		<tr>
			<td align="right" style="padding-top:5px; border:none;" id="avoidDbl">
				<input type="button"class = "bhs_button yb" id = "inquireReplyBtn" value="저장" style="float:none;">
			</td>
		</tr>
	</tbody>
</table>
</form>
</div>

</div>

</body>
<script type = "text/javascript" src = "http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/jaju/js/inquireReplyForm.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#type3').addClass('on');
});
</script>

</html>
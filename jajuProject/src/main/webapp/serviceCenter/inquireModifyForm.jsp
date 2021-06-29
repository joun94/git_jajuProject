<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/jaju/css/serviceMenu.css" /> 
<link rel="stylesheet" href="/jaju/css/inquireWriteForm.css" />    

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="width: 1050px; margin: auto;" >
<jsp:include page="/serviceCenter/serviceMenu.jsp"/>
<div class="page_section section_qna">
<div class="head_aticle">
<h2 class="tit">1:1 문의</h2>
</div>

<div class="xans-board-write">
<form name="inquireModifyForm"  id="inquireModifyForm" method="post"  enctype="multipart/form-data" style="height: 100%;">
<input type="hidden" name="inquiry_seq" id = "inquiry_seq" value= "${seq}">
<input type="hidden" name="pg" value="${pg}">

<table id="table_after" class="boardWrite2" width="100%">
<colgroup><col width="14%" align="right"></colgroup>
<tbody>
<tr>
	<th class="input_txt">제목</th>
	<td>
		<select name="inquiryType" id = "inquiryType" label="문의 유형">
			<option value="">&nbsp;&nbsp;선택해주세요.&nbsp;&nbsp;</option>
			<option value="회원정보 문의">&nbsp;&nbsp;회원정보 문의&nbsp;&nbsp;</option>
			<option value="거래 관련문의">&nbsp;&nbsp;거래 관련문의&nbsp;&nbsp;</option>
			<option value="시스템 문의">&nbsp;&nbsp;시스템 문의&nbsp;&nbsp;</option>
			<option value="기타문의">&nbsp;&nbsp;기타문의&nbsp;&nbsp;</option>
		</select>
		<br>
		<input type="text" name="inquiry_subject" id = "inquiry_subject" style="width:100%" label="제목">
		<div id = "inquiryTypeDiv"></div>
		<div id = "inquiry_subjectDiv" ></div>
	</td>
</tr>

<tr>
<th class="input_txt">전화번호</th>
	<td>
		<input type="text" name="inquiry_telephone" id = "inquiry_telephone" readonly>
		<span class="noline smalle" style="padding-left:10px"></span>
		<div id = "inquiry_telephoneDiv"></div>
	</td>
</tr>
<tr>
<th class="input_txt">내용</th>
<td class="edit_area" style="position: relative;">



<textarea name="inquiry_contents" id = "inquiry_contents" style="width:100%; height:474px; resize: none;" class="editing_area" label="내용"></textarea>
<div id = "inquiry_contentsDiv"></div>
</td>
</tr>
<tr>
<th class="input_txt">이미지</th>
<td>
<table width="95%" class = "inquireModifyTable" id="inquireModifyTable" cellpadding="0" cellspacing="0" border="0" style="border:solid 1px #f4f4f4; border-collapse:collapse;">
	<tr id="tr_0">
		<td width="20" align="left">1.
		<input type = "hidden" id = "inquiry_image1" name="inquiry_image1"></input></td>
			<td><span class="image1Name" id="image1Name"></span></td>
			<td width="100%">
				<input type="file" name="img1" style="width:50%" class="linebg" multiple size="50">
				<span class="image1Name">
					<input type="checkbox" value="삭제" name="checkbox1">삭제
				</span>
			 </td>
		</tr>
		
		<tr id="tr_0">
		<td width="20" align="left">2.
		<input type = "hidden" id = "inquiry_image2" name="inquiry_image2"></input></td>
			<td><span class="image2Name" id="image2Name"></span></td>
			<td width="100%">
				<input type="file" name="img2" style="width:50%" class="linebg" multiple size="50">
				<span class="image2Name">
					<input type="checkbox" value="삭제" name="checkbox2">삭제
				</span>
			 </td>
		</tr>
		
		<tr id="tr_0">
		<td width="20" align="left">3.
		<input type = "hidden" name = "inquiry_image3" id = "inquiry_image3"></input></td>
			<td><span class="image3Name" id="image3Name"></span></td>
			<td width="100%">
				<input type="file" name="img3" style="width:50%" class="linebg" multiple size="50">
				<span class="image3Name">
					<input type="checkbox" value="삭제" name="checkbox3">삭제
				</span>
			 </td>
		</tr>
		
</table>
<table>
	<tbody>
		<tr>
			<td height="2"></td>
		</tr>
	</tbody>
</table>
	<div width="100%" style="padding:5px;" class="stxt">
	- 파일은 최대 3개까지 업로드가 지원됩니다.<br>
	- 새로운 파일 등록을 원하시면 파일 선택을 눌러 주세요<br>
	- 삭제 선택시 모든 파일이 삭제 됩니다.<br> 
	</div>
	</td>
		</tr>
	</tbody>
</table>

<table width="100%">
	<tbody>
		<tr>
			<td align="right" style="padding-top:5px; border:none;" id="avoidDbl">
				<input type="button"class = "bhs_button yb" id = "inquireModifyBtn" value="저장" style="float:none;">
			</td>
		</tr>
	</tbody>
</table>
</form>
</div>

</div>
</div>
</body>
<script type = "text/javascript" src = "http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/jaju/js/inquireModiyForm.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#type3').addClass('on');
});
</script>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/jaju/css/reportModifyForm.css" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="width: 1050px; margin: auto;" >
<jsp:include page="/serviceCenter/serviceMenu.jsp"/>
<div class="page_section section_qna">
<div class="head_aticle">
<h2 class="tit">신고 게시판</h2>
</div>

<div class="xans-board-write">
<form id="reportModifyForm" name="reportModifyForm" style="height: 100%;">
<input type="hidden" id="pg" name="pg" value="${pg}">
<input type="hidden" id="report_seq" name="report_seq" value="${report_seq}">
<input type="text" id="sale_seq" name="sale_seq" value="${param.sale_seq}">
<table id="table_after" class="boardWrite2" width="100%">
<colgroup><col width="14%" align="right"></colgroup>
<tbody>
<tr>
	<th class="input_txt">제목</th>
	<td>
		<select id="reportType" name="reportType" label="신고 유형">
			<option value="">선택해주세요.</option>
			<option value="욕설, 비방, 차별, 혐오">욕설, 비방, 차별, 혐오</option>
			<option value="홍보, 영리목적">홍보, 영리목적</option>
			<option value="음란, 청소년 유해">음란, 청소년 유해</option>
			<option value="개인 정보 노출">개인 정보 노출</option>
			<option value="유포, 거래">유포, 거래</option>
			<option value="도배, 스팸">도배, 스팸</option>
			<option value="사기 신고">사기 신고</option>
			<option value="기타문의">기타문의</option>
		</select>
		<br>
		<input type="text" id="report_subject" name="report_subject" style="width:100%" label="제목" placeholder="제목 입력">
		 	<div id="itemcdDiv"></div>
		 	<div id="subjectDiv"></div>
	</td>
</tr>
<tr>
<th class="input_txt">신고 아이디</th>
	<td>
		<input type="text" id="sale_id" name="sale_id" style="background-color:#dddddd; color: ##4c4c4c; width:25%" readonly="readonly">
		<div style="position:relative;z-index:1">
			<iframe id="ifm_order" frameborder="0" scrolling="no" style="display:none;position:absolute;width:560px;height:380px;background-color:#fff;position:absolute;left:0;top:0;border:1px solid #000"></iframe>
		</div>
	</td>
</tr>
<tr>
<th class="input_txt">신고글</th>
	<td>
		<input type="text" id="sale_subject" name="sale_subject" style="background-color:#dddddd; color: ##4c4c4c; width:25%" value="광고 홍보성 글이 시부렁댄다." readonly="readonly">
		<div style="position:relative;z-index:1">
			<iframe id="ifm_order" frameborder="0" scrolling="no" style="display:none;position:absolute;width:560px;height:380px;background-color:#fff;position:absolute;left:0;top:0;border:1px solid #000"></iframe>
		</div>
	</td>
</tr>
<tr>
<th class="input_txt">전화번호</th>
	<td>
		<input type="text" id="report_tal" name="report_tal" class="read_only" placeholder="전화번호 입력"> &nbsp; 번호만 입력하거나 -를 넣어서 입력해주세요.
		<span class="noline smalle" style="padding-left:10px"></span>
		<div id="mobileDiv"></div>
	</td>
</tr>
<tr>
<th class="input_txt">내용</th>
<td class="edit_area" style="position: relative;">

<textarea id="report_content" name="report_content" style="width:100%; height:474px; resize: none;" class="editing_area" label="내용" placeholder="내용 입력"></textarea>
<div id="contentsDiv"></div>
</td>
</tr>
<tr>
<th class="input_txt">이미지</th>
<td>
<table width="95%" class="reportModifyTable" id="reportModifyTable" cellpadding="0" cellspacing="0" border="0" style="border:solid 1px #f4f4f4; border-collapse:collapse;">
	<tr id="tr_0">
		<td width="20" align="left">1.
		<input type="hidden" id="report_image1" name="report_image1"></input></td>
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
		<input type="hidden" id="report_image2" name="report_image2"></input></td>
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
		<input type="hidden" name="report_image3" id="report_image3"></input></td>
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
				<input type="button" class="bhs_button yb" id="bhs_button" value="저장" style="float:none;">
			</td>
		</tr>
	</tbody>
</table>
</form>
</div>

</div>
</div>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/jaju/js/reportModify.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	$('#type4').addClass('on');
});
</script>

</html>
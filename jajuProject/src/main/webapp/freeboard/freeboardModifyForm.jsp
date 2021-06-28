<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/jaju/css/freeboardModifyForm.css" />

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id="freeboardDiv">
<div class="head_aticle">
<h2></h2>
</div>
	
<div class="xans-board-write">
<form class="freeboardModifyForm" id="freeboardModifyForm" name="freeboardModifyForm" style="height: 100%; width:100%;">
	<input type="hidden" name="board_seq" id="board_seq" value="${param.board_seq }">
	<input type="hidden" name="mode" value="add_qna">
	<input type="hidden" name="itemcd">
	<input type="hidden" name="sno" value="0">
<table  class="freeboardModifyTable" id="freeboardModifyTable" style="width:100%;">
<colgroup><col width="14%" align="right"></colgroup>
<tbody>

<tr>
	<th class="freeboardModifyTd">제목</th>
	<td>
		<input class="board_subject" type="text" style="width:40%;" name="board_subject" id="board_subject" placeholder="제목을 입력해주세요.">
			<div id="board_subjectDiv"></div>
	</td>
</tr>

<tr>
<th class="freeboardModifyTd">내용</th>
<td class="saleText" name="board_content" placeholder="내용을 입력해주세요." style="position: relative; width:100%;">

<textarea name="board_content" id="board_content" style="width:70%; height:474px; resize: none;" class="editing_area" label="내용"></textarea>
<div id="board_contentDiv"></div>
</td>
</tr>

<tr>
<th class="freeboardModifyTd">이전 이미지</th>
<td class="freeImg" name="img[]" style="position: relative; width:70%;">
<table width="70%" cellpadding="0" cellspacing="0" border="0" style="border:solid 1px #f4f4f4; border-collapse:collapse;">
   <tbody id="imageTd">

   </tbody>
</table>
<table>
   <tbody>
</table>
</td>
</tr>

<tr>
<th class="input_txt">이미지</th>
<td>
<table width="70%" id="table" cellpadding="0" cellspacing="0" border="0" style="border:solid 1px #f4f4f4; border-collapse:collapse;">
   <tbody>
   <tr id="tr_0">
      <td width="20" nowrap="" align="center">1</td>
      <td width="50%">
         <input type="file" name="file[]" style="width:50%" class="linebg">
         <a href="javascript:add()">
            <img src="../image/upadd.jpg" style="width:90px; height:25px;">
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
	
			   <div width="50%" style="padding:5px;" class="stxt">
		- 파일은 최대 3개까지 업로드가 지원됩니다.<br>
		</div>
	</td>
</tr>
</table>

<table width="70%">
	<tbody>
		<tr>
			<td align="right" style="padding-top:5px; border:none;" id="avoidDbl">
				<input type="button" id="freeboardModifyBtn" class="bhs_button yb" value="글수정" style="float:none;">
			</td> 
		</tr>
	</tbody>
</table>
</table>
</form>
</div>

</div>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/jaju/js/freeboardModify.js"></script>
<script type="text/javascript">
function add(){
    var table = document.getElementById('table');
    var reviewFileNum = "3";
    if (table.rows.length>=parseInt(reviewFileNum)){
        alert("업로드는 최대 "+reviewFileNum+"개만 지원합니다");
        return;
    }
    var tr_num = table.rows.length;
    oTr      = table.insertRow( table.rows.length );
    oTr.id   = "tr_"+(tr_num);
    oTd1      = oTr.insertCell(0);
    oTd1.style.textAlign = "center";
    oTd2      = oTr.insertCell(1);
    tmpHTML = "<input type=file name='file[]' style='width:50%' class=line> <a href=\"javascript:del('"+"tr_"+(tr_num)+"')\"><img src='../image/upadd2.jpg' style='width:90px; height:25px;' align=absmiddle></a>";
    oTd2.innerHTML = tmpHTML;
    calcul();
}

function del(index,ncode)
{
    var table = document.getElementById('table');
    for (i=0;i<table.rows.length;i++) if (index==table.rows[i].id) table.deleteRow(i);
    calcul();
}

function calcul()
{
    var table = document.getElementById('table');
    for (var i=0;i<table.rows.length;i++){
        table.rows[i].cells[0].innerHTML = i+1;
    }
}
</script>
</body>
</html>
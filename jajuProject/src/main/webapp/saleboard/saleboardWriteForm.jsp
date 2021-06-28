<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/jaju/css/saleboardWriteForm.css" />

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id="saleboardDiv">
<div class="head_aticle">
</div>
	
<div class="xans-board-write">
<form class="saleboardWriteForm" id="saleboardWriteForm" style="height: 100%; width:100%;">
	<input type="hidden" name="mode" value="add_qna">
	<input type="hidden" name="itemcd">
	<input type="hidden" name="sno" value="0">
	<input type="hidden" id="member_id" name= "member_id" value="${sessionScope.memId }"/>
<table class="saleboardWriteTable" id="saleboardWriteTable" style="width:100%;">
<colgroup><col width="14%" align="right"></colgroup>
<tbody>

<tr>
	<th class="saleboardWriteTd">제목</th>
	<td>
		<select class="sale_state" name="sale_state" id="sale_state" onchange="">
			<option value="판매중">판매중</option>
		</select>
		<br>
		<input class="sale_subject" type="text" style="width:40%;" name="sale_subject" id="sale_subject" placeholder="제목을 입력해주세요.">
			<div id="sale_subjectDiv"></div>
	</td>
</tr>
<tr>
		 	<th class="saleboardWriteTd" align="left">판매가격</th>
		 	<td class="saleboardWriteTd" colspan="2">
		 		<input class="saleboard_input" type="text" 
    oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" style="width:30%;" name="sale_price" id="sale_price" placeholder="가격을 입력해주세요.">
				 <label class="">  	
				  	&nbsp;&nbsp; 
				  	<input type="checkbox" name="sale_nego" id="sale_nego">
		  			<span class="ico"></span>
				  	가격제안허용
		  		</label>
		  		<div id="sale_priceDiv"></div>
			</td>
		</tr>
<tr>
	<th class="saleboardWriteTd" align="left">카테고리</th>
 	<td class="saleboardWriteTd">
 		<select id="sale_category" name="sale_category">
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
         	<div id="sale_categoryDiv"></div>
	</td>
</tr>
 <tr>
 	<th class="saleboardWriteTd" align="left">상품 상태</th>
 	<td class="saleboardWriteTd">
		<label class="">	 	
  		새상품<input type="radio" name="sale_condition" id="sale_condition" value="새상품">
  			<span class="ico"></span>
  		</label>
  		<label class="">
  		거의 새것<input type="radio" name="sale_condition" id="sale_condition" value="거의 새것">
  			<span class="ico"></span>
  		</label>
  		<label class="">
  		사용감있음<input type="radio" name="sale_condition" id="sale_condition" value="사용감있음">
  			<span class="ico"></span>
  		</label>
  		<label class="">
  		사용감많음<input type="radio" name="sale_condition" id="sale_condition" value="사용감많음">
  			<span class="ico"></span>
  		</label>
  			<div id="sale_conditionDiv"></div>
</td>
<tr>
 	<th class="saleboardWriteTd" align="left">배송 방법</th>
 	<td class="saleboardWriteTd">
 			<label class="">  	
		  	직거래<input type="checkbox" id="sale_direct" name="sale_direct">
  			<span class="ico"></span>
  		</label>
  		<label class="">  	
		  	택배거래<input type="checkbox" id="sale_delivery" name="sale_delivery">
  			<span class="ico"></span>
  		</label>
  		<label class="">  	
		  	온라인거래<input type="checkbox" id="sale_onlineTran" name="sale_onlineTran">
  			<span class="ico"></span>
  		</label>
  			<div id="sale_directDiv"></div>
  			<div id="sale_deliveryDiv"></div>
  			<div id="sale_onlineTranDiv"></div>
  	</td>
  	
</tr>
<tr>
<th class="saleboardWriteTd">내용</th>
<td class="saleText" name="sale_content" placeholder="내용을 입력해주세요." style="position: relative; width:100%;">

<textarea name="sale_content" id="sale_content" style="width:70%; height:474px; resize: none;" class="editing_area" label="내용"></textarea>
<div id="sale_contentDiv"></div>
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
         <input type="file" name="file" id="file" class="file" style="width:50%" class="linebg">
         <a href="javascript:add()">
            <img src="../image/upadd.jpg" class="addImage" style="width:90px; height:25px;">
         </a>
       </td>
   </tr>
   
   <tr id="tr_0">
      <td width="20" nowrap="" align="center">2</td>
      <td width="50%">
         <input type="file" name="file" id="file" class="file" style="width:50%" class="linebg">
         <a href="javascript:add()">
            <img src="../image/upadd.jpg" class="addImage" style="width:90px; height:25px;">
         </a>
         <div id="sale_imageDiv"></div>
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
			   - 파일은 최대 10개까지 업로드가 지원됩니다.<br>
			   </div>
		

<table width="70%">
	<tbody>
		<tr>
			<td align="right" style="padding-top:5px; border:none;" id="avoidDbl">
				<input type="button" id="saleboardWriteBtn" class="bhs_button yb" value="판매글등록" style="float:none;">
			</td>
		</tr>
	</tbody>
</table>
</table>

</form>
</div>

</div>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/jaju/js/saleboardWrite.js"></script>
<script type="text/javascript">
function order_open(){
    var divEl = document.getElementById('ifm_order');
    divEl.style.display = "block";
    if( divEl.src == '' ) divEl.src = "mypage_qna_order.php";

    // KM-1483 Amplitude 연동
    KurlyTracker.setScreenName('personal_inquiry_writing_order_history_selection');
}

function order_close(){
    var divEl = document.getElementById('ifm_order');
    divEl.style.display = "none";
}

function order_put( ordno ){
    document.fm.ordno.value = ordno;
    order_close();
}

function add(){
    var table = document.getElementById('table');
    var reviewFileNum = "10";
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
    tmpHTML = "<input type=file name='file' style='width:50%' class=file> <a href=\"javascript:del('"+"tr_"+(tr_num)+"')\"><img src='../image/upadd2.jpg' style='width:90px; height:25px;' align=absmiddle></a>";
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
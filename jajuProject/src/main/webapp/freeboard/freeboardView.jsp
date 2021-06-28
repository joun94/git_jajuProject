<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/jaju/css/freeboardView.css" />
<jsp:include page="/freeboard/boardMenu.jsp"/>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="hidden" id="board_seq" value="${board_seq }">
<input type="hidden" id="pg" value="${param.pg }"/>
<input type="hidden" id="board_id" name= "board_id" value="${sessionScope.memId }"/>
<div id="wrap">
<div id="pos_scroll"></div>
<div id="container" style="float:left; margin-left:40px; margin-top:-50px; width:800px;">

<div class="tit_page" style="width:800px; align:center;">
	<h1 class="tit"></h1>
</div>

<div id="main">
<div id="content">

<div id="qnb" class="quick-navigation" style="top: 70px;"></div>
<div class="layout-wrapper">
	<div class="xans-element- xans-myshop xans-myshop-couponserial ">
	
		<table class="xans-board-listheader" style="width:800px; align:center;" cellpadding="0" cellspacing="0">
			<tbody>
			<tr>
			<td>
			<table style="width:800px;">
			<tbody>
			<tr>
			<td>
		<table class="boardView" style="width:800px; font-size:14px;">
			<tbody>
				<tr>
					<th scope="row" style="border:none;">제목</th>
					<td colspan="5"> <span id="subjectSpan"></span></td>
				</tr>
				<tr  class="board_id" >
					<th scope="row">작성자</th>
					<td colspan="5" ><span id="idSpan"></span></td>
				</tr>
				<tr>
				<th scope="row">작성일</th>
					<td colspan="3"><span id="dateSpan"></span></td>
				<th scope="row">조회수</th>
					<td colspan="1"><span id="hitSpan"></span></td>	
				</tr>
				
			</tbody>
		</table>
			</td>
		</tr>
		<tr>
			<td align="right" class="eng" style="padding:5px;"></td>
		</tr>
			<tr>
			<td style="padding:10px;" height="200" valign="top" id="contents">
			<table width="100%" style="table-layout:fixed">
			
			<tbody>
			<tr>
			<td class="board_view_content" style="word-wrap:break-word;word-break:break-all" id="contents_924" valign="top">
			<div style="font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; line-height: normal; margin: 0px;">
			<font color="#222222" face="Font" style="font-variant-numeric: normal; font-variant-east-asian: normal; font-stretch: normal; line-height: normal; margin: 0px;">
				<pre class="viewPre"><span id="contentSpan"></span></pre>
					<div id="imageDiv">
					
					</div>
			</font>
			</div>
			</td>
			</tr>
		</tbody>
		</table>
		</td>
		</tr>
		<tr><td height="1" bgcolor="#f4f4f4"></td></tr>
		</tbody></table><br>
		
		<table name="bottom-menu" width="100%" style="table-layout:fixed" cellpadding="0" cellspacing="0">
		<tbody><tr>
		<td align="center" style="padding-top:10px;">
		
		<table width="100%">
		<tbody>
		
		<tr>
		<td align="right" colspan="2">
		<a href="/jaju/freeboard/freeboardModifyForm?board_seq=${param.board_seq }"><span class="bhs_button yb" style="float:none;">수정</span></a>
		<span class="bhs_button yb" style="float:none; cursor:pointer; " id="freeboardDeleteBtn">삭제</span>
		</td>
		</tr>
		
		</tbody></table>
		</td>
		</tr>
		</tbody></table>
		
		<br><br><br>
		
		
<!-- 댓글 -->
	<div id="commentDiv">
		<table id="commentTable" >
			<tr style="border-bottom: 1px solid #f5f5f5;">
				<th width="200" style="padding-bottom: 10px;">번호</th>
				<th width="400" style="padding-bottom: 10px;">내용</th>
				<th width="200" style="padding-bottom: 10px;">작성자</th>
				<th width="200" style="padding-bottom: 10px;">작성일</th>
			</tr>

		</table>
		<div id="pagingDiv" style="text-align: center; margin-bottom: 10px;">
		
		</div>
		
		<table border="2" id=c cellspacing="0" cellpadding="10"
         bordercolor="#d6e6f2" align="center" frame="hsides" rules="rows"
         width="1050" id="commentWriteTable">
         <tr >
            <td height="80" width="150" align="center">댓글: &nbsp; <span id="replySpan"></span>
               <input type="text" placeholder="댓글을 입력하세요." size="100" id="commentText" class="commentInput">
               &nbsp;&nbsp;
               <input type="button" value="댓글 입력" class="commentInput" id="commentInputBtn" size="200" >
            </td>
         </tr>
      </table>
	</div>
		<br>
		<div class="xans-element- xans-board xans-board-movement-1002 xans-board-movement xans-board-1002 " style="width: 800px;">
			<ul>
				<li class="prev">
					<strong>이전글</strong>
					<a id="prevBtn">
						<span class="prev" id="prevSpan"></span>
					</a>
				</li>
				<li class="next">
					<strong>다음글</strong>
					<a id="nextBtn">
						<span class="next" id="nextSpan"></span>
					</a>
				</li>
			</ul>
		</div>
		<table width="100%" cellpadding="5" cellspacing="0">
			<colgroup>
				<col width="100" align="right" bgcolor="#f7f7f7" style="padding-right:10px">
				<col style="padding-left:10px">
			</colgroup>
		</table>
		<p>
		<br>
		<textarea id="examC_924" style="display:none;width:100%;height:300px"></textarea>
		</p>
		
		</td>
		</tr>
		</tbody>
		</table>
	</div>
</div>
</div>
</div>
</div>
</div>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/jaju/js/freeboardView.js"></script>
<script src="/jaju/js/freeboardDelete.js"></script>
<script type="text/javascript">
function freeboardCommentPaging(pg){
	$.ajax({
		type: 'post',
		url: '/jaju/freeboard/getFreeboardComment',
		data: {'board_seq': $('#board_seq').val(), 'pg': pg},
		dataType: 'json',
		success: function(data){
			//alert(JSON.stringify(data));
			
			$('#commentTable tr:gt(0)').remove();
			
			$.each(data.list, function(index,items){
				
				$('<tr/>').append($('<td/>',{
					text : items.comment_seq
				})).append($('<td/>',{
					style : 'text-align: left;'
					
					}).append($('<a/>',{
						text : items.comment_content,
						id: 'subjectA',
						class: 'content_'+ items.comment_seq
					}))
				).append($('<td/>',{
					text : items.board_id
				})).append($('<td/>',{
					class: 'trLast',
					text : items.logtime
				})).appendTo($('#commentTable'));
				
				for(var i=1; i<items.comment_lev; i++){
					$('.content_'+items.comment_seq).before('&emsp;');
				}
				if(items.comment_pseq != 0 ){
					$('.content_'+items.comment_seq).before($('<img>',{
						src: '/jaju/jajuImage/reply.gif'
					}));
				}
				
			}); //each
			
			$('.trLast').append($('<img>',{
				src: '/jaju/jajuImage/delete.png',
				id: 'commentDeleteBtn',
				style: 'cursor: pointer; float: right;',
				width: '20px',
				height: '20px'
			})).append($('<img>',{
				src: '/jaju/jajuImage/modify.png',
				id: 'commentModifyBtn',
				style: 'cursor: pointer; float: right;',
				width: '20px',
				height: '20px'
			}));
			
			$('#pagingDiv').html(data.freeboardCommentPaging.pagingHTML);
			
		},error: function(err){
			alert("댓글 불러오기 에러");
			console.log(err);
		}
	});
	
}

</script>


</body>
</html>
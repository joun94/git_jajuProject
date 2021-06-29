<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/jaju/css/reportView.css" />
<jsp:include page="/serviceCenter/serviceMenu.jsp"/>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<form id="reportViewForm" name="reportViewForm">
<input type="hidden" id="report_seq" name="report_seq" value="${report_seq}">


<input type="hidden" id="comment_seq" name="comment_seq" value="${comment_seq}">
<input type="hidden" id="pg" name="pg" value="${pg}">
<div style="width: 1050px; margin: auto;" >
<div id="wrap">
<div id="pos_scroll"></div>
<div id="container" style="float:left; margin-left:40px; margin-top:-50px; width:800px;">

<div class="tit_page" style="width:800px; align:center;">
	<h1 class="tit" >신고 게시판</h1>
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
				<table class="boardView" width="100%;">
				<tbody>
				<tr  style="border-top: 2px solid #47597e;">
					<th scope="row" style="border:none;">제목</th>
					<td><span id="subjectSpan"></span></td>
				</tr>
				
				<tr>
					<th scope="row">작성자</th>
					<td><span id="idSapn"></span></td>
				</tr>
				
				<tr class="etcArea">
					<td colspan="2">
						<ul>
							<li class="tpye ">
								<strong class="th">전화번호</strong>
								<span class="td" id="talSpan"></span>
							</li>
							<li class="date ">
								<strong class="th">작성일</strong> 
								<span class="td" id="dateSpan"></span>
							</li>
						</ul>
					</td>
				</tr>
			</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td align="right" class="eng" style="padding:5px;"></td>
		</tr>
			<tr>
			<td style="margin:0;" height="200" valign="top" id="contents">
				<pre class="viewPre">
					<span id="contentSpan"></span>
				</pre>
				
				<br><br>
				<img id="imageSpan1">
				<span id="imageSpan1"></span>
				<img id="imageSpan2">
				<span id="imageSpan2"></span>
				<img id="imageSpan3">
				<span id="imageSpan3"></span>
			</td>
		</tr>
			<tr>
				<td height="1" bgcolor="#f4f4f4"></td>
			</tr>
		</tbody>
		</table>
		<br>
		
		<table width="100%" style="table-layout:fixed" cellpadding="0" cellspacing="0">
		<tbody>
		<tr>
		<td align="center" style="padding-top:10px;">
		
		<table width="100%">
			<tbody>
				<tr>
					<td align="right">
						<a href="javascript:void(0)" onclick="mode(1)">
							<span class="reportBtn modify" style="float:none;">수정</span>
						</a>
						<a href="javascript:void(0)" onclick="mode(2)">
							<span class="reportBtn delete" style="float:none;">삭제</span>
						</a>
						<a href="/jaju/serviceCenter/reportList">
							<span class="reportBtn list" style="float:none;">목록</span>
						</a>
					</td>
				</tr>
			</tbody>
		</table>
		</td>
		</tr>
		</tbody>
		</table>
		
		<div class="replyRealTotal">
		<div class="replyTotal">
		
		<table class="replyList" style="">
			<!-- <tbody>
				<tr>
					<td class="contnet">
						안녕하세요
						<br><br><br><br><br><br><br><br>
					</td>
				</tr>
				<tr>
					<td class="date" style="text-align:right; border-top: 1px solid #333;">
						2021.06.21
					</td>
				</tr>
				
				<tr>
					<td style="align: right;">
						<a href="javascript:void(0)" onclick="mode(4)">
							<span class="reportBtn comModify" style="float:none;">수정</span>
						</a>
						<a href="javascript:void(0)" onclick="mode(5)">
							<span class="reportBtn comDelete" style="float:none;">삭제</span>
						</a>
					</td>
				</tr>
			</tbody> -->
		</table>
		
		<table class="reply">
			<tbody>
				<tr>
					<td>
					<textarea id="comment_content" name="comment_content" placeholder="댓글을 입력하세요"></textarea>
					<div id="commentDiv"></div>
					</td>
				</tr>
			
				<tr>
					<td align="right">
						<a href="javascript:void(0)" onclick="mode(3)">
							<span class="reportBtn comWrite" style="float:none;">등록</span>
						</a>
					</td>
				</tr>
			</tbody>
		</table>
		
		<table class="replyModify">
			<tbody>
				<tr>
					<td>
					<textarea id="comment_content" name="comment_content" class="comment_content" placeholder="댓글을 입력하세요"></textarea>
					<div id="commentDiv" class="commentDiv"></div>
					</td>
				</tr>
			
				<tr>
					<td align="right">
						<input type="button" class="reportBtn comReWrite" value="저장">
						<%-- <a href="javascript:void(0)" onclick="mode(6,${param.comment_seq})">
							<span class="reportBtn comReWrite" style="float:none;">저장</span>
						</a> --%>
					</td>
				</tr>
			</tbody>
		</table>
		
		</div>
		</div>
		
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
</div>
</form>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/jaju/js/reportView.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	$('#type4').addClass('on');
});
</script>
</body>
</html>
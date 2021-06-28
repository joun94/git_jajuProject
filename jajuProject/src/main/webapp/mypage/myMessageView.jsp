<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/jaju/css/myMessageView.css" />
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<style type="text/css">

</style>
</head>
<body>

<div class="myMessage_div">
<!-- 쪽지 viewer -->
	<form name="myMessageViewForm">
	
			<input type="hidden" name="seq" id="seq" value="${seq}"> 
			<input type="hidden" name="pg" id="pg" value="${pg}">
	
		<div class="head_aticle">
			<h2 class="tit" align="center">쪽지</h2>
		</div>
		
		<table border="1" id="myMessageViewTable" class="myMessageTable" bordercolor="#ddd" cellspacing="0" cellpadding="5" align="center" frame="hsides" rules="rows"
				>
				
			<tr>
				<td align="center" colspan="3" class="title"><span id="subjectSpan"></span></td>
			</tr>
			
			<tr>
				<th>쪽지 번호 : <span id="message_seq_Span"></span></th>
				<th>보낸사람 : <span id="message_writer_Span"></span></th>
				<th>보낸 날짜 : <span id="message_date_Span"></span></th>
			</tr>
			
			<tr>	
				<td class="content" colspan="3" valign="top">
					
					<div class="content_div" align="left">
						<pre style="white-space:pre-line; word-break: break-all;">
							
							<span id="message_content_Span"></span>
						</pre>
					</div>
				</td>
			</tr>
			
			<tr>
				<td colspan="3" align="center" class="moveToListTd">
					<button class="sendBtn" id="sendBtn" ><a href="#">답장</button>
					<button class="backBtn" id="backBtn" ><a href="/jaju/mypage/myMessage?pg=1">쪽지함</a></button>
					<!-- <span class="backBtn" id="backBtn"><a href="/jaju/mypage/myMessage?pg=1">&emsp;쪽지함 이동&emsp;</a></span> -->
				</td>
				
			</tr>
		
		</table>
	</form>
</div>
<!-- 이부분은 js에서 append 나 html로 붙여넣기하기 -->
		
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>	
<script src="/jaju/js/myMessageView.js"></script>

</body>
</html>
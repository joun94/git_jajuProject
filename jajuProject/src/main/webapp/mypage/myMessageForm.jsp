<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/jaju/css/myMessageForm.css" />
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
</head>
<body>


<!-- 쪽지 viewer -->
<form id="myMessageFormForm" name="myMessageFormForm">

	  <input type="hidden" name="sale_seq" id="sale_seq" value="${param.sale_seq}">
      <input type="hidden" name="message_reader" id="message_reader" value="${param.message_reader}">
      <input type="hidden" name=message_writer id="message_writer" value="${param.message_writer}">

	<div class="head_aticle">
		<h2 class="tit" align="center">쪽지 보내기</h2>
	</div>
	
	<table border="1" id="myMessageFormTable" class="myMessageFormTable" bordercolor="#ddd" cellspacing="0" cellpadding="5" align="center" frame="hsides" rules="rows"
			>
			
		<tr>
			<td align="center" colspan="3" class="title">
			<input type="text" id="message_subject" name="message_subject" placeholder="제목 입력" style="width:450px; height:20px;">
			<div id="message_subject_div_check"></div></td>
		</tr>
		
		<tr>
			<th align="left" >받는이 : <span id="message_reader_span"></span></th> 
			<th align="left" >작성자 : <span id="message_writer_Span"></span></th>
			
		</tr>
		
		<tr>	
			<td class="content" colspan="3" valign="top">
				<div class="content_div" align="left">
					<pre><textarea name="message_content_Span" id="message_content_Span" placeholder="내용 입력" style="width:450px; height:260px; 
							border:0; resize:none;"></textarea></pre>
				</div>
				<div id="content_div_check"></div>
			</td>
		</tr>
		
		<tr>
			<td colspan="3" align="center" class="moveToListTd">
				<!--  <button class="sendBtn" id="sendBtn" ><a href="/jaju/mypage/myMessageSend?pg=1">답장</a></button>  -->
				<button class="sendBtn" id="sendBtn" >보내기</button>
				<button class="backBtn" id="backBtn" ><a href="/jaju/mypage/myMessage?pg=1">쪽지함</a></button>
				<button type="reset" id="resetBtn" >다시작성</button>
				<!-- <span class="backBtn" id="backBtn"><a href="/jaju/mypage/myMessage?pg=1">&emsp;쪽지함 이동&emsp;</a></span> -->
			</td>
		</tr>
	
	</table>
</form>
<!-- 이부분은 js에서 append 나 html로 붙여넣기하기 -->
		
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>	
<script src="/jaju/js/myMessageForm.js"></script>

</body>
</html>
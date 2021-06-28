$(function(){
	//messageView 상세 페이지 가져오기
	$.ajax({
		url:'/jaju/mypage/myMessageView',
		type:'post',
		data:{
			'seq':$('#seq').val(),
			'pg':$('#pg').val()
		},
		dataType:'json',
		success:function(data){
		//	console.log(data.messageDTO);
			//messageDTO 로 넘어오니 each문 필요없음
			$('#subjectSpan').html(data.messageDTO.message_subject);
			$('#message_seq_Span').html(data.messageDTO.message_seq);
			$('#message_writer_Span').html(data.messageDTO.message_writer);
			$('#message_date_Span').html(data.messageDTO.message_date);
			$('#message_content_Span').html(data.messageDTO.message_content);
			
			$(document).on("click","#sendBtn",function(){
			//$('#sendBtn').click(function(){
				var seq = data.messageDTO.message_seq; 
			//	alert('글번호'+seq);
			    window.location.href='/jaju/mypage/myMessageReplyForm?seq='+seq;
			});
			
			
			
		},
		error:function(err){
			console.log("err발생"+err);
		}
	});
	//span tag append 하기
	
	//답장버튼 (#sendBtn) 누르면 답장 쓰는 페이로 이동 
});
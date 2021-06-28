/*$(window).on('beforeunload', function(){
    //do something
    return "창을 종료 하시겠습니까?";
});
*/
$(function() {
	//alert('잘넘어왔는지 확인' + $('#seq').val());

	$.ajax({
		url : '/jaju/mypage/myMessageFormLoad',
		type : 'post',
		data : {
			'seq' : $('#seq').val(),
		// 'pg':$('#pg').val()
		},
		dataType : 'json',
		success : function(data) {
			console.log(data.messageDTO);
			$('#message_reader_span').html(data.messageDTO.message_writer);
		},
		error : function(err) {
			console.log("err발생" + err);
		}
	});// ajax

	$('#message_reader_span').text($('#message_reader').val());
	$('#message_writer_Span').text($('#message_writer').val());

$(document).on("click", "#sendBtn", function() {
		if ($('#message_subject').val() == "") {
			alert('제목을 작성해 주세요');
		} else if ($('#message_content_Span').val() == "") {
			alert('내용을 작성해 주세요');
		} else {			
				$.ajax({
					type : 'post',
					url : '/jaju/mypage/replyMyMessage',
					data : {
						'message_reader' : $('#message_reader_span').text(),
						// data.messageDTO.message_writer,
						'message_writer' : $('#id').val(),
						'message_subject' : $('#message_subject').val(),
						'message_content' : $('#message_content_Span').val(),
						'sale_seq' : $('#seq').val()
					},
					success : function() {
						alert('메세지가 보내졌습니다');
						location.href = '/jaju/mypage/myMessage?pg=1';
					},
					error : function(err) {
						console.log(err);
						alert('메세지 보내기 에러');
					}
				});
			}
	}); // send btn

});// function

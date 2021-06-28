$('#freeboardWriteBtn').click(function(){
	var formData = new FormData($('#freeboardWriteForm')[0]); //form 안에 있는 모든 것 다 넘김

	//유효성검사
	$('#board_subjectDiv').empty();
	$('#board_contentDiv').empty();
	
	if($('#board_subject').val() == ''){
		$('#board_subjectDiv').text('제목을 입력하세요');
		$('#board_subjectDiv').css('color', 'red');
        $('#board_subjectDiv').css('font-size', '10pt');
        $('#board_subjectDiv').css('font-weight', 'bold');
	}else if($('#board_content').val() == ''){
        $('#board_contentDiv').text('내용을 입력하세요');
        $('#board_contentDiv').css('color', 'red');
        $('#board_contentDiv').css('font-size', '10pt');
        $('#board_contentDiv').css('font-weight', 'bold');
	}else{
	
		$.ajax({
			type: 'post',
			url: '/jaju/freeboard/freeboardWrite',
			enctype: 'multipart/form-data',
			processData: false,
			contentType: false,
			data: formData,
			success: function(data) {
				alert("게시글 등록 완료");
				location.href='/jaju/freeboard/index';
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});
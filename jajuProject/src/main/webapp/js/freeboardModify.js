//불러오기
$(function(){

	$.ajax({
		type: 'post',
		url: '/jaju/freeboard/getFreeboardView',
		data: {'board_seq': $('#board_seq').val()},
		dataType: 'json',
		success: function(data){
			//alert(JSON.stringify(data)); //자료가 오긴 온다

			$('#board_subject').val(data.freeboardDTO.board_subject);
			$('#board_content').val(data.freeboardDTO.board_content);
			
			let map = new Map();
			
			map.set("image1", data.freeboardDTO.image1+'');
			map.set("image2", data.freeboardDTO.image2+'');
			map.set("image3", data.freeboardDTO.image3+''); //null값 방지
			
			for(var i=1; i<=3; i++){
				if(map.get('image'+i) != 'null'){
					$('#imageTd').append($('<img/>',{
						width:"300",
						height:"300",
						class:"boardImg",
						src: '/jaju/storage/'+map.get('image'+i)
					}));
				}
			}
		},
		error: function(err){
			console.log(err);
		}
		
	});
	
});


$('#freeboardModifyBtn').click(function(){
	
	var formData = new FormData($('#freeboardModifyForm')[0]); //form 안에 있는 모든 것 다 넘김
	
	//유효성 검사
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
			url: '/jaju/freeboard/freeboardModify',
			enctype: 'multipart/form-data',
			processData: false,
			contentType: false,
			data: formData,
			success: function(data) {
				//alert(JSON.stringify(data));
				alert("게시글수정 완료");
				location.href='/jaju/freeboard/freeboardList?pg=1';
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});
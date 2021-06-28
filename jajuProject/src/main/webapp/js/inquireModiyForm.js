//글 불러오기
$(function(){
	$('.image1Name').hide();
	$('.image2Name').hide();
	$('.image3Name').hide();
	
	var formData = new FormData($('#inquireModifyForm')[0])
	$.ajax({
		type : 'post',
		url : '/jaju/serviceCenter/getInquireModify',
		data : 'seq='+$('#inquiry_seq').val(),
		dataType : 'json',
		success : function(data){
			
			$('#inquiryType').val(data.inquireDTO.inquiryType).prop('select', true);
			$('#inquiry_subject').val(data.inquireDTO.inquiry_subject);
			$('#inquiry_telephone').val(data.inquireDTO.inquiry_telephone);
			$('#inquiry_contents').val(data.inquireDTO.inquiry_contents);
			
			if (data.inquireDTO.inquiry_image1 != null) {
				$('#image1Name').text(data.inquireDTO.inquiry_image1)
				$('#inquiry_image1').val(data.inquireDTO.inquiry_image1)
				$('.image1Name').show();
	
			}
			if (data.inquireDTO.inquiry_image2 != null) {
				$('#image2Name').text(data.inquireDTO.inquiry_image2)
				$('#inquiry_image2').val(data.inquireDTO.inquiry_image2)
				$('.image2Name').show();
	
			}

			if (data.inquireDTO.inquiry_image3 != null) {
				$('#image3Name').text(data.inquireDTO.inquiry_image3)
				$('#inquiry_image3').val(data.inquireDTO.inquiry_image3)
				$('.image3Name').show();
				
			}
			
		},
		error : function(err){
			console.log(err);
		}
	});
});

//글 수정하기
$('#inquireModifyBtn').click(function(){
	$('#inquiryTypeDiv').empty();
	$('#inquiry_subjectDiv').empty();
	$('#inquiry_contentsDiv').empty();
	
	if($('#inquiryType').val() == ''){
		$('#inquiryTypeDiv').text('문의 유형을 선택해 주세요');
		$('#inquiryTypeDiv').css('color','red');
		$('#inquiryTypeDiv').css('font-size','8pt');
		$('#inquiryTypeDiv').css('font-weight','bold');
		
	}else if($('#inquiry_subject').val() == ''){
		$('#inquiry_subjectDiv').text('제목을 입력하세요');
		$('#inquiry_subjectDiv').css('color','red');
		$('#inquiry_subjectDiv').css('font-size','8pt');
		$('#inquiry_subjectDiv').css('font-weight','bold');
		
	}else if($('#inquiry_contents').val() == ''){
		$('#inquiry_contentsDiv').text('내용을 입력하세요');
		$('#inquiry_contentsDiv').css('color','red');
		$('#inquiry_contentsDiv').css('font-size','8pt');
		$('#inquiry_contentsDiv').css('font-weight','bold');
	}else{
		var formData = new FormData($('#inquireModifyForm')[0])
		$.ajax({
			type : 'post',
			url : '/jaju/serviceCenter/inquireModify',
			enctype : 'multipart/form-data',
			processData : false,
			contentType : false,
			data : formData,
			success : function(data){
				alert("수정이 완료되었습니다");
				location.href = '/jaju/serviceCenter/inquireList';
			},
			error : function(err){
				console.log(err);
			}
		});
	}
});